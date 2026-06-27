# 测试规范 — taotao-cloud-message

## 测试类型

| 层级 | 测试类型 | 框架 | 是否需 Spring 上下文 |
|------|----------|------|---------------------|
| Domain | 单元测试 | JUnit 5 + Mockito | 否（纯 POJO） |
| Application | 集成测试 | @SpringBootTest | 是 |
| Infrastructure | 集成测试 | @SpringBootTest + Testcontainers | 是 |
| Interfaces | Web 测试 | @WebMvcTest | 是 |

## 命名规范
- 测试类: `{Class}Test` — e.g. `DeptEntityTest`, `OrderApplicationServiceTest`
- 测试方法: `should{ExpectedBehavior}_when{Condition}` — e.g. `shouldThrowException_whenNameExists`

## 命令

```bash
gradlew test                                    # 全部测试
gradlew :taotao-cloud-message-domain:test       # 指定模块
gradlew test --tests "*DeptEntityTest"          # 指定测试类
gradlew jacocoTestReport                        # 覆盖率报告
```

## 禁止
- 使用 `@DirtiesContext`（影响测试性能）
- 测试中直接操作数据库（使用 Repository 抽象）
- 在 domain 层测试中加载 Spring 上下文
