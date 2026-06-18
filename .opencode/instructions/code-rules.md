# 项目编码规范 — taotao-cloud-message

> DDD 架构规范 — 基于六边形架构 + 领域驱动设计

---

## 1. 模块依赖规则

```
api  ←  interfaces  ←  application  →  facade  →  (外部系统)
                          ↓                     ↑
                     domain  ←  infrastructure  |
                     common (被所有模块依赖) ------
```

- `domain`：零外部框架依赖，不依赖 Spring、不依赖数据库
- `application`：依赖 `domain`，可依赖 `facade` 接口，不依赖 `infrastructure`
- `infrastructure`：依赖 `domain` 实现仓储，依赖 `application` 实现事件订阅
- `interfaces`：依赖 `application`，不直接依赖 `infrastructure`
- `api`：纯 DTO + 接口定义，不依赖任何业务模块
- `common`：被所有模块依赖，只放通用工具、枚举、常量
- `facade`：防腐层，对外部系统的适配，依赖 `application` 接口

### 禁止违反的依赖
```java
// ❌ 禁止：Controller 直接调用 JPA Repository
@Autowired private DeptRepository deptRepository;

// ❌ 禁止：Application Service 直接调用 Mapper
@Autowired private DictMapper dictMapper;

// ❌ 禁止：Domain Service 注入 Repository
@Autowired private DeptDomainRepository deptDomainRepository;

// ✅ 正确：Application Service 通过仓储接口操作持久化
private final DeptDomainRepository deptDomainRepository;
```

## 2. Domain 层规范

### 包结构
```
com.taotao.cloud.message.domain.{module}/
├── aggregate/     # 聚合根
├── entity/        # 实体（extends AggregateRoot<Long>）
├── valueobject/   # 值对象（注意：不是 valobj/）
├── event/         # 领域事件
├── repository/    # 仓储接口
├── service/       # 领域服务接口 + 实现
└── adapter/       # 适配器接口
```

### 实体的写法
```java
@Setter
@Getter
@ToString
public class DeptEntity extends AggregateRoot<Long> {

    private String name;
    private Long pid;
    private String path;
    private Integer sort;

    // 业务行为方法
    public void checkName(long count) {
        if (count > 0) {
            throw new BusinessException("部门名称已存在，请重新填写");
        }
    }

    public void checkIdAndPid() {
        if (id.equals(pid)) {
            throw new BusinessException("上级部门不能为当前部门");
        }
    }
}
```

### 值对象的写法
```java
public class Money {
    private final BigDecimal amount;
    private final String currency;

    // 构造时自验证
    public Money(BigDecimal amount, String currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("金额不能为负数");
        }
        this.amount = amount;
        this.currency = currency;
    }

    // 只有 getter，无 setter
    // 覆写 equals/hashCode（基于所有属性）
}
```

## 3. Application 层规范

### 包结构
```
com.taotao.cloud.message.application/
├── service/                   # 应用服务
│   └── impl/
├── dto/
│   ├── own/                   # 自有领域
│   │   └── {entity}/
│   │       ├── cmmond/        # Command（*Cmd）
│   │       ├── query/         # Query（*Qry, *PageQry）
│   │       └── clientobject/  # Client Object（*CO）
│   └── external/              # 外部领域（同上结构）
├── handler/                   # 处理器
├── event/                     # 事件 + 监听器
├── factory/
├── context/
├── configuration/             # 应用配置
│   └── aop/{email,message,execl}/
└── assembler/
```

### DTO 命名规范
```java
// Command - 写操作入参，后缀 Cmd
public class DictSaveCmd implements Serializable { ... }
public class DictUpdateCmd implements Serializable { ... }

// Query - 查询入参，后缀 Qry / PageQry
public class DictQry implements Serializable { ... }
public class DictPageQry implements Serializable { ... }

// Client Object - 返回对象，后缀 CO
public class DictQueryCO implements Serializable { ... }
```

### 应用服务规范
```java
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AlipayConfigServiceImpl implements AlipayConfigService {
    // 1. 构建领域对象
    // 2. 调用领域服务（如果需要跨聚合逻辑）
    // 3. 保存聚合
    // 4. 发布领域事件
    // 5. 返回 DTO
}
```

## 4. Interfaces 层规范

### 包结构
```
com.taotao.cloud.message.interfaces/
├── controller/
│   ├── buyer/       # 买家端 API
│   ├── seller/      # 卖家端 API
│   ├── manager/     # 管理端 API
│   ├── mall/        # 商城端 API
│   └── inner/       # 内部 API
├── rpc/             # RPC 实现
└── grpc/            # gRPC 实现
```

### Controller 规范
```java
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/{role}/dict")
@Tag(name = "买家端-字典API", description = "买家端-字典API")
public class BuyerDictController extends BusinessController {
    // HTTP 解析 + 参数校验 + Result 封装
    // 禁止业务逻辑

    private final DictCommandService dictCommandService;

    @GetMapping("/page")
    public Result<PageResult<DictCO>> page(DictPageQry qry) {
        return Result.success(dictQueryService.pageQuery(qry));
    }

    @PostMapping
    public Result<Boolean> save(@Valid @RequestBody DictSaveCmd cmd) {
        return Result.success(dictCommandService.save(cmd));
    }
}
```

## 5. Infrastructure 层规范

### 包结构
```
com.taotao.cloud.message.infrastructure/
├── persistent/
│   ├── persistence/      # PO 持久化对象
│   ├── repository/       # JPA Repository 接口
│   └── mapper/           # MyBatis Mapper 接口
├── repository/           # 领域仓储实现
├── event/                # 事件发布
├── configuration/        # 基础设施配置
├── channels/             # 消息通道
│   ├── netty/
│   ├── websockt/stomp/
│   ├── websockt/spring/
│   └── dingtalk/
├── converter/
├── assembler/
├── factory/
└── utils/
```

### PO 写法
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

@Entity
@Table(name = MessagePO.TABLE_NAME)
@TableName(MessagePO.TABLE_NAME)  // MyBatis-Plus 兼容
@org.springframework.data.relational.core.mapping.Table(name = MessagePO.TABLE_NAME)
public class MessagePO extends BasePO<MessagePO> {

    public static final String TABLE_NAME = "ttc_app";

    private String name;
    private String code;
    private String icon;
    private Integer sort;
}
```

### 领域仓储实现
```java
@Service
@AllArgsConstructor
public class DeptDomainRepositoryImpl implements DeptDomainRepository {

    @Override
    public void create(DeptEntity dept) {
        // 通过 JPA Repository 或 MyBatis Mapper 持久化
    }
}
```

## 6. API 层规范

### 包结构
```
com.taotao.cloud.message.api/
├── rpc/               # 对外 RPC 接口
├── inner/             # 内部 API 接口
```

### 命名规范
- RPC 接口：`MessageRpcService`, `DictRpcServiceImpl`
- 内部 API：`NoticeMessageApi`, `StoreMessageApi`, `DictApiImpl`
- 请求 DTO：`MessageQueryRpcRequest`
- 响应 DTO：`MessageRpcResponse`, `MessageApiResponse`

## 7. 枚举规范

```java
// 在 common 模块定义
public enum MessageStatusEnum {
    PENDING("待发送"),
    SENT("已发送"),
    FAILED("发送失败"),
    READ("已读");

    private final String description;
    // ...
}
```

## 8. 领域事件规范

```java
// 事件定义在 domain/event/
@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DeptCreateEvent {
    private String name;
}

// 事件在 infrastructure/event/ 发布
@Component
@Slf4j
public class DomainEventPublisher {
    public void publish(ApplicationEvent event) { ... }
}
```

## 9. 数据库规范

### 表必备字段
```sql
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
`create_by` bigint DEFAULT NULL COMMENT '创建人ID',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_by` bigint DEFAULT NULL COMMENT '更新人ID',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
`tenant_id` bigint DEFAULT 0 COMMENT '租户ID',
`version` int DEFAULT 0 COMMENT '乐观锁'
```

### 禁止
- 循环中查询数据库（N+1 问题）
- `SELECT *`
- 在 Java 代码中拼接 SQL
- 跨聚合直接操作其他聚合的数据表

## 10. 构建与测试

```bash
# 全量构建
gradlew build

# 运行所有测试
gradlew test

# 运行指定模块测试
gradlew :taotao-cloud-message-domain:test

# 代码质量
gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 本地启动（dev 环境）
gradlew :taotao-cloud-message-assembly:bootRun --args='--spring.profiles.active=dev'

# 生成 JaCoCo 覆盖率报告
gradlew jacocoTestReport
```
