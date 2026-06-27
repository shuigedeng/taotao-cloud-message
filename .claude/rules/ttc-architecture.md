# 架构规范 — taotao-cloud-message

## 六边形架构分层职责

### Domain 层（零外部框架依赖）
- **聚合根/实体**: 充血模型，包含业务行为方法，extends `AggregateRoot<Long>`
- **值对象**: 不可变，所有字段 final，无 setter，构造时自验证
- **领域事件**: 定义事件类，由基础设施层发布
- **仓储接口**: 纯接口定义，不依赖任何 ORM 框架
- **领域服务**: 协调跨聚合的业务逻辑

### Application 层（编排层）
- **应用服务**: 事务边界、编排领域对象、DTO 转换
- **DTO 分类**: `own/`（自有领域）、`external/`（外部领域）
- **禁止**: 业务规则判断、直接调用 Mapper/Repository

### Infrastructure 层（技术实现）
- **仓储实现**: 实现 domain 层仓储接口，包含 PO ↔ Entity 映射
- **消息通道**: `channels/{netty|websockt|dingtalk}/` 按通信协议组织
- **事件发布**: `event/DomainEventPublisher`
- **PO 定义**: `persistent/persistence/`，双重注解 `@Entity` + `@TableName`

### Interfaces 层（API 实现）
- **Controller**: 按角色分包 buyer/seller/manager/mall/inner
- 继承 `BusinessController`，只做 HTTP 解析和 Result 封装
- **RPC 实现**: `interfaces/rpc/` + `interfaces/grpc/`

### API 层（纯接口定义）
- 只放 `rpc/` 和 `inner/` 接口定义 + DTO
- 不依赖任何业务模块

## 依赖方向

```
interfaces → application → domain ← infrastructure
api（被 interfaces 和 application 依赖）
facade（被 application 依赖，适配外部系统）
common（被所有模块依赖）
```

## 禁止违反的依赖

```java
// ❌ 禁止：Controller 调用 JPA Repository
@Autowired private DeptRepository deptRepository;

// ❌ 禁止：Application Service 调用 Mapper
@Autowired private DictMapper dictMapper;

// ❌ 禁止：Domain 层使用 Spring 注解
@Autowired private DeptDomainRepository deptDomainRepository;
```
