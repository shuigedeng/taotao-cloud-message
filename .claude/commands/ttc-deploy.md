---
description: 部署应用到指定环境（dev/test/pre/pro）
parameters:
  - name: environment
    type: string
    enum: [dev, test, pre, pro]
    required: true
---

# 部署 — taotao-cloud-message

## 部署流程

### 1. 运行测试
```bash
gradlew test
```
测试失败则中止部署。

### 2. 打包
```bash
gradlew :taotao-cloud-message-assembly:bootJar
```

### 3. 启动（指定环境）
```bash
java --enable-preview \
  -jar taotao-cloud-message-assembly/build/libs/taotao-cloud-message-assembly-*.jar \
  --spring.profiles.active={environment}
```

### 4. 健康检查
```bash
curl -f http://localhost:{port}/actuator/health
```
