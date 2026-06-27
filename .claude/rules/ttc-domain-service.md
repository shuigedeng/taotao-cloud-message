# 领域服务设计规范 — taotao-cloud-message

## 何时使用领域服务

### 适用场景
1. **跨聚合的业务逻辑** — 涉及多个聚合根的协调操作
2. **无状态的计算服务** — 纯粹的业务计算，不持有状态
3. **外部领域概念** — 不属于任何单一聚合的业务操作

### 不适用场景
1. **应该属于聚合根的行为** — 如果逻辑属于某个聚合，移到聚合内
2. **纯粹的技术性操作** — 属于基础设施层
3. **应用层的用例编排** — 属于 application/service/

## 领域服务在项目中的位置

```
domain/service/         — 接口定义（如 DeptDomainService）
domain/service/impl/    — 实现
```

## 命名规范
- 接口: `{Entity}DomainService` — e.g. `DeptDomainService`
- 实现: `{Entity}DomainServiceImpl` — e.g. `DeptDomainServiceImpl`

## 实现规范

```java
// 接口在 domain/service/
public interface DeptDomainService {
    void create(DeptEntity dept);
    void modify(DeptEntity dept);
    void remove(Long[] ids);
}
```

### 关键约束
1. **无状态设计**: 方法参数包含所有需要的数据，不持有实例状态
2. **业务语义明确**: 方法名表达业务意图
3. **只依赖仓储接口**: 通过 domain/repository/ 接口访问持久化
4. **不依赖框架**: 不在 domain 层使用 Spring 注解
