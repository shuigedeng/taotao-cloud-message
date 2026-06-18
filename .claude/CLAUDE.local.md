# 个人开发配置 — taotao-cloud-message

## 开发环境
- **IDE**: IntelliJ IDEA Ultimate 2026.1
- **JDK**: graalvm-jdk-25（--enable-preview）
- **OS**: Windows 11
- **Shell**: PowerShell 5.1

## 常用命令别名
```bash
gradlew build -x test                    # 快速编译（跳过测试）
gradlew :taotao-cloud-message-assembly:bootRun --args='--spring.profiles.active=dev'  # 启动
gradlew test --tests "*{TestClass}*"     # 运行指定测试
```

## 个人偏好
- 先写 domain 层单元测试
- 使用 `@Setter @Getter @ToString` + `@RequiredArgsConstructor`
- 代码提交前运行 `gradlew checkstyleMain spotlessCheck`
