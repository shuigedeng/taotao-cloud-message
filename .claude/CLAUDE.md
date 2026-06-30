# taotao-cloud-message — DDD 单体服务

## 技术栈

| 组件 | 版本 |
|------|------|
| JDK | 25（预览特性，`--enable-preview`） |
| Gradle | 9.6.0 |
| Spring Boot | 4.1.0 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| Spring Security | 7.1.0 |
| MyBatis-Plus | 3.5.16 |
| JPA (Jakarta) | 3.2.0 |
| MapStruct | 1.6.3 |
| Lombok | 1.18.46 |
| Record Builder | 53 |
| QueryDSL | 5.1.0 |
| Knife4j (Swagger) | 4.5.0 |
| Netty | 4.2.12.Final |
| RocketMQ | 5.2.4 |
| Sentinel | 1.8.9 |

## 项目结构（8 个 Gradle Module）

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

## 基础包路径

```
com.taotao.cloud.message
  .{module}           # api / application / domain / infrastructure / interfaces / common / facade
    .{subpackage}     # 按 DDD 分层
```

## 关键包位置

| 需求 | 位置 |
|------|------|
| 聚合根/实体 | `domain/entity/` — extends `AggregateRoot<Long>` |
| 值对象 | `domain/valueobject/`（不是 valobj/） |
| 领域事件 | `domain/event/` + `infrastructure/event/DomainEventPublisher` |
| 领域仓储接口 | `domain/repository/` |
| 领域仓储实现 | `infrastructure/repository/` |
| JPA Repository | `infrastructure/persistent/repository/` |
| PO 持久化对象 | `infrastructure/persistent/persistence/` — extends `BasePO<Self>` |
| MyBatis Mapper | `infrastructure/persistent/mapper/` |
| 应用服务 | `application/service/` + `application/service/impl/` |
| DTO (自有) | `application/dto/own/{entity}/{cmmond\|query\|clientobject}/` |
| DTO (外部) | `application/dto/external/{entity}/{cmmond\|query\|clientobject}/` |
| Controller | `interfaces/controller/{buyer\|seller\|manager\|mall\|inner}/` |
| 消息通道 | `infrastructure/channels/{netty\|websockt\|dingtalk}/` |
| 防腐层 | `facade/acl/` |
| RPC 接口定义 | `api/rpc/` + `api/inner/` |
| RPC 实现 | `interfaces/rpc/` + `interfaces/grpc/` |

## DTO 命名规范

| 分类 | 后缀 | 包名 | 示例 |
|------|------|------|------|
| Command（写操作入参） | `*Cmd` | `cmmond/` | `DictSaveCmd`、`DictUpdateCmd` |
| Query（查询入参） | `*Qry`、`*PageQry` | `query/` | `DictQry`、`DictPageQry` |
| Client Object（返回对象） | `*CO` | `clientobject/` | `DictQueryCO` |

## 分层依赖规则

```
interfaces → application → domain ← infrastructure
api（独立，被 interfaces/application 依赖）
facade（独立，被 application 依赖）
common（被所有模块依赖）
```

- **Controller** 禁止写业务逻辑
- **Application Service** 仅编排，不含业务规则
- **Domain** 层零 Spring 注解，纯业务
- 跨聚合通过 **ID 引用**，非对象引用
- 事务边界仅开在 **application/service/** 层

## 常用类继承

| 类型 | 基类 | 来源 |
|------|------|------|
| Domain Entity | `AggregateRoot<Long>` | `taotao.boot.ddd.model.domain` |
| PO | `BasePO<Self>` | `taotao.boot.webagg.entity` |
| Controller | `BusinessController` | `taotao.boot.webagg.controller` |
| Application Service 接口 | `CommandService` | `taotao.boot.ddd.model.application.service` |

## 常用注解

- `@Setter @Getter @ToString` — Lombok
- `@Schema` — Swagger/OpenAPI
- `@RequiredArgsConstructor` — 构造器注入
- `@Validated` — 参数校验
- `@Tag @Operation` — 接口文档
- `@TableName` (MyBatis-Plus) + `@Table` (JPA) 双注解
- `@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)`

## 构建命令

```bash
gradlew build                              # 全量编译
gradlew test                               # 运行所有测试
gradlew :taotao-cloud-message-domain:test  # 指定模块测试
gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain  # 质量检查
gradlew :taotao-cloud-message-assembly:bootRun --args='--spring.profiles.active=dev'  # 启动
gradlew publishToMavenLocal                # 发布到本地
```

## 禁止项（ANTI-PATTERNS）

- Controller 中写业务逻辑判断
- 聚合根中注入 Repository 或 Domain Service
- 值对象中包含业务行为以外的逻辑
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- 在 domain 层使用 Spring 注解（`@Autowired`、`@Service` 等）
- Controller 直接调用 Repository / Mapper
- Application Service 直接调用 Mapper

## 代码质量

- Checkstyle 13.5.0 + SpotBugs + PMD + Spotless 8.7.0 (google-java-format AOSP) + OWASP
- Spotless 使用 `google-java-format` AOSP 风格，4 空格缩进
- JDK 25 预览特性：所有 task 配置 `--enable-preview` + `--add-exports`
- 四个环境配置：dev / test / pre / pro
- 所有子模块应用 jacoco / checkstyle / spotless / spotbugs / pmd 质量插件

## 协作约定

- 响应语言：**中文**（问题描述、代码评审）
- 代码注释用**英文**
- 提交信息格式：`type(scope): description`（e.g. `feat(dict): add batch delete`）
- 优先编写 domain 层单元测试
- 编码前先确认当前分支，避免交叉提交
