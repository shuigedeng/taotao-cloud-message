# PROJECT KNOWLEDGE BASE

**Generated:** 2026-06-18
**Commit:** (tracked in git)
**Project:** taotao-cloud-message

## OVERVIEW

消息领域 DDD 单体服务，基于 Spring Boot 4.1.0 / JDK 25 / Gradle 9.5。
严格遵循六边形架构 + 领域驱动设计。项目属于 taotao-cloud-project 生态的消息模块。

## STRUCTURE

```
.opencode/
├── commands/        # 工作流命令（9个）
├── instructions/    # 编码规范
├── skills/          # 技能脚本
├── AGENTS.md        # 代理知识库
└── opencode.json    # OpenCode 配置
```

```
taotao-cloud-message/
├── api/               # RPC/gRPC 接口 + DTO（纯接口定义，无实现）
├── application/       # 应用层：编排、事务、DTO转换、事件监听
├── assembly/          # 启动器（Spring Boot Application main class）
├── common/            # 公共工具、枚举、常量、属性配置
├── domain/            # ★ 领域层（零外部框架依赖）
├── facade/            # 防腐层（ACL — 对外部系统的适配）
├── infrastructure/    # 持久化、消息通道、事件发布、配置
└── interfaces/        # Controller / RPC / gRPC 实现
```

## REAL MODULE STRUCTURE

### `taotao-cloud-message-api` — 接口定义层
```
com.taotao.cloud.message.api/
├── rpc/               # Dubbo/gRPC RPC 接口
│   ├── request/       # RPC 请求 DTO
│   └── response/      # RPC 响应 DTO
└── inner/             # 内部 API 接口
    ├── request/       # 内部请求 DTO
    └── response/      # 内部响应 DTO
```

### `taotao-cloud-message-application` — 应用层
```
com.taotao.cloud.message.application/
├── service/                   # 应用服务接口
│   └── impl/                  # 应用服务实现
├── dto/
│   ├── own/                   # 自有领域 DTO
│   │   └── {entity}/
│   │       ├── cmmond/        # Command 命令（如 DictSaveCmd, DictUpdateCmd）
│   │       ├── query/         # Query 查询（如 DictQry, DictPageQry）
│   │       └── clientobject/  # Client Object 返回对象（如 DictQueryCO）
│   └── external/              # 外部领域 DTO（同上结构）
├── handler/                   # 业务处理器（如 DictHandler, DeptHandler）
├── event/                     # 应用事件 + 监听器
├── factory/                   # 工厂类
├── context/                   # 上下文
├── configuration/             # 应用配置（AOP、properties、aware 等）
│   ├── aop/{email,message,execl}/
│   ├── aware/
│   ├── properties/
│   ├── stream/
│   ├── gen/
│   └── redisson/
└── assembler/                 # Assembler
```

### `taotao-cloud-message-domain` — 领域层
```
com.taotao.cloud.message.domain/
├── aggregate/       # 聚合根
├── entity/          # 实体（extends AggregateRoot<Long>）
├── valueobject/     # 值对象（注意：不是 valobj/）
├── event/           # 领域事件
├── repository/      # 仓储接口
├── service/         # 领域服务接口 + 实现
├── adapter/         # 适配器接口
```

### `taotao-cloud-message-infrastructure` — 基础设施层
```
com.taotao.cloud.message.infrastructure/
├── persistent/
│   ├── persistence/      # PO 持久化对象
│   ├── repository/       # JPA/MyBatis Repository 接口
│   └── mapper/           # MyBatis Mapper 接口
├── repository/           # 领域仓储实现（*DomainRepositoryImpl）
├── event/                # 事件发布器
├── configuration/        # 基础设施配置（RocketMQ, Redis, Logbook 等）
├── channels/             # 消息通道
│   ├── websockt/stomp/   # WebSocket STOMP 实现（含 entity/repository/service/controller/listener）
│   ├── websockt/spring/  # WebSocket Spring 实现（含 push/recipient/service/model）
│   ├── netty/            # Netty 实现（含 server/client/handler/codec）
│   └── dingtalk/         # 钉钉通知
├── converter/            # 转换器
├── assembler/            # Assembler（与 application/assembler 不同）
├── factory/              # 工厂
├── utils/                # 工具类
├── properties/           # 配置属性
├── dataobject/           # 数据对象（*DO）
└── dataparam/            # 数据参数
```

### `taotao-cloud-message-interfaces` — 接口实现层
```
com.taotao.cloud.message.interfaces/
├── controller/
│   ├── buyer/       # 买家端 Controller
│   ├── seller/      # 卖家端 Controller
│   ├── manager/     # 管理端 Controller
│   ├── mall/        # 商城端 Controller
│   └── inner/       # 内部 API 实现
├── rpc/             # RPC 服务实现
└── grpc/            # gRPC 服务实现
```

### `taotao-cloud-message-common` — 公共层
```
com.taotao.cloud.message.common/
├── enums/           # 枚举
├── constant/        # 常量
├── properties/      # 配置属性
├── utils/           # 工具类
├── data/
│   ├── dataobj/     # 数据对象
│   └── dataparam/   # 数据参数
├── info/            # 信息类
├── execption/       # 异常类
├── script/          # 脚本
├── helper/          # 辅助类
└── group/           # 分组
```

### `taotao-cloud-message-facade` — 防腐层
```
com.taotao.cloud.message.facade/
└── acl/             # Anti-Corruption Layer
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| 新增业务功能 | `application/service/` + `application/dto/{own|external}/{entity}/` |
| 修改领域模型 | `domain/aggregate/` 或 `domain/entity/` |
| 值对象 | `domain/valueobject/` — not `valobj/` |
| 领域事件 | `domain/event/` 或 `application/event/` |
| 领域仓储接口 | `domain/repository/` |
| 领域仓储实现 | `infrastructure/repository/` |
| 持久化 Repository | `infrastructure/persistent/repository/` |
| PO 定义 | `infrastructure/persistent/persistence/` |
| MyBatis Mapper | `infrastructure/persistent/mapper/` |
| API 接口定义 | `api/rpc/` 或 `api/inner/` |
| API 接口实现 | `interfaces/controller/inner/` 或 `interfaces/rpc/` |
| Controller | `interfaces/controller/{buyer,seller,manager,mall}/` |
| 外部接口适配 | `facade/acl/` |
| 消息监听/通道 | `infrastructure/channels/{netty,websockt,dingtalk}/` |
| 基础设施配置 | `infrastructure/configuration/` |
| 定时任务 | `infrastructure/job/` |
| 事件发布 | `infrastructure/event/` |
| 启动类 | `assembly/src/main/java/com/taotao/cloud/message/TaoTaoCloudMessageApplication.java` |

## CONVENTIONS

### 分层依赖
```
interfaces → application → domain ← infrastructure
api (独立，被 interfaces 和 application 依赖)
facade (独立，被 application 依赖)
common (被所有模块依赖)
```

### 包路径规则
- 基础包: `com.taotao.cloud.message.{module}`
- domain entity: `com.taotao.cloud.message.domain.entity`
- application dto: `com.taotao.cloud.message.application.dto.{own|external}.{entity}.{cmmond|query|clientobject}`
- infrastructure po: `com.taotao.cloud.message.infrastructure.persistent.persistence`
- controller: `com.taotao.cloud.message.interfaces.controller.{buyer|seller|manager|mall|inner}`

### 命名规范
- Command 类后缀: `*Cmd`（如 `DictSaveCmd`），包名 `cmmond/`
- Query 类后缀: `*Qry`（如 `DictQry`, `DictPageQry`），包名 `query/`
- Client Object 类后缀: `*CO`（如 `DictQueryCO`），包名 `clientobject/`
- PO 后缀: `*PO`（如 `MessagePO`）
- DO 后缀: `*DO`（如 `DeptDO`, `DictDO`）
- Entity 后缀: `*Entity`（如 `DeptEntity`）
- 仓储接口命名: `{Entity}DomainRepository`（domain 层）
- 仓储实现命名: `{Entity}DomainRepositoryImpl`（infrastructure 层）
- Controller 命名: `{Role}{Entity}Controller`

### 类继承关系
- Domain Entity: `extends AggregateRoot<Long>`（来自 `taotao.boot.ddd.model.domain`）
- PO: `extends BasePO<Self>`（来自 `taotao.boot.webagg.entity`）
- Controller: `extends BusinessController`（来自 `taotao.boot.webagg.controller`）
- Service: 实现 `CommandService` 接口（来自 `taotao.boot.ddd.model.application.service`）

### 常用注解
- `@Setter @Getter @ToString` — Lombok
- `@Schema` — Swagger/OpenAPI 文档
- `@RequiredArgsConstructor` — 构造器注入
- `@Tag @Operation` — 接口文档
- `@Validated` — 参数校验
- `@Service` / `@RestController` / `@Component`
- `@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)`
- `@TableName` (MyBatis-Plus) + `@Table` (JPA) 双注解

### DTO 两层分类
- `own/` — 自有领域 DTO（本 bounded context 内部使用）
- `external/` — 外部领域 DTO（跨 bounded context 交互使用）

## ANTI-PATTERNS (THIS PROJECT)

- Controller 中写业务逻辑判断
- 聚合根中注入 Repository 或 Domain Service
- 值对象中包含业务行为以外的逻辑
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- 在 domain 层使用 Spring 注解（如 `@Autowired`, `@Service`）
- Controller 直接调用 Repository / Mapper
- Application Service 直接调用 Mapper

## UNIQUE STYLES

- **API/Interfaces 分离**：`api/` 模块只放接口定义和 DTO，`interfaces/` 模块放实现
- **Controller 按端分层**：buyer / seller / manager / mall / inner 五个角色，各端 API 完全隔离
- **DTO 两层模型**：`own/` 和 `external/` 区分内部和外部交互
- **Infrastructure 通道化**：`channels/` 按通信协议（netty, websockt, dingtalk）组织
- **JPA + MyBatis-Plus 双持**：PO 同时标注 `@Entity` + `@TableName`
- **事件驱动**：`domain/event/` 定义领域事件，`infrastructure/event/` 发布
- **Assembler 分层**：`application/assembler/` 和 `infrastructure/assembler/` 各司其职
- **防腐层独立为模块**：`facade/` 作为独立 gradle module，而非 application 的子包

## BUILD COMMANDS

```bash
gradlew build                              # 全量编译
gradlew test                               # 运行所有测试
gradlew :taotao-cloud-message-assembly:bootRun --args='--spring.profiles.active=dev'  # 启动dev
gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain          # 质量检查
gradlew publishToMavenLocal                # 发布到本地
gradlew :taotao-cloud-message-domain:test  # 指定模块测试
```

## TECHNOLOGY STACK

| Component | Version |
|-----------|---------|
| JDK | 25 (预览特性，--enable-preview) |
| Gradle | 9.6.0 |
| Spring Boot | 4.1.0 |
| Spring Cloud | 2025.1.1 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| Spring Security | 7.1.0 |
| MyBatis-Plus | 3.5.16 |
| JPA | 3.2.0 (jakarta) |
| MapStruct | 1.6.3 |
| Lombok | 1.18.46 |
| Record Builder | 52 |
| QueryDSL | 5.1.0 |
| Knife4j (Swagger) | 4.5.0 |
| Netty | 4.2.12.Final |
| RocketMQ | 5.2.4 |
| Sentinel | 1.8.9 |

## NOTES

- JDK 25 预览特性，所有 task 配置了 `--enable-preview` + 大量 `--add-exports`
- `taotao-cloud-dependencies:2026.07` BOM 未开源，外部构建需要私有仓库凭据
- 代码质量门禁：Checkstyle 13.5.0 + SpotBugs + PMD + Spotless 8.6.0 + OWASP
- Spotless 使用 `google-java-format` AOSP 风格
- 四个环境配置：dev / test / pre / pro
- Runner: Windows (PowerShell)
- 所有 Java 文件带 Apache 2.0 License header
- 多 Maven 仓库：阿里云、腾讯云、华为云、Spring、Sonatype
