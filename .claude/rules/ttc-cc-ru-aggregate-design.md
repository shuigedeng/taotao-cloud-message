# 聚合设计规范 — taotao-cloud-message

## 聚合识别原则

### 1. 事务边界
聚合内修改必须在一个事务中完成，聚合间使用最终一致性。

### 2. 一致性规则
- **强一致性**: 聚合内保证
- **最终一致性**: 聚合间通过领域事件保证

### 3. 聚合大小
- **小聚合原则**: 一个聚合根通常只包含 1-3 个实体
- **跨聚合引用**: 通过 ID 引用，而非对象引用

## 聚合根写法

```java
@Setter
@Getter
@ToString
public class DeptEntity extends AggregateRoot<Long> {

    private String name;
    private Long pid;
    private String path;
    private Integer sort;

    // 业务行为方法（命令方法）
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

## 聚合根设计要点
1. **行为方法**: 用业务语义命名（`submit()`, `cancel()`, `pay()`），而非 setter
2. **不变性维护**: 聚合根负责保证内部不变量
3. **无框架注解**: 不依赖 Spring、JPA 等框架注解
4. **领域事件**: 聚合内业务方法完成后注册事件

## 仓储接口设计

```java
// 接口在 domain/repository/
public interface DeptDomainRepository {
    void create(DeptEntity dept);
    void modify(DeptEntity dept);
    void remove(Long[] ids);
}

// 实现在 infrastructure/repository/
@Service
@AllArgsConstructor
public class DeptDomainRepositoryImpl implements DeptDomainRepository {
    @Override
    public void create(DeptEntity dept) {
        // 通过 JPA Repository 或 MyBatis Mapper 持久化
    }
}
```
