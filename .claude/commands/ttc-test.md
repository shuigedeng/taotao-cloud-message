---
description: 运行测试并生成 JaCoCo 覆盖率报告
parameters:
  - name: module
    type: string
    description: 测试模块（domain/application/infrastructure/interfaces）
    required: false
---

# 测试执行 — taotao-cloud-message

## 执行步骤

### 1. 运行测试
```bash
# 全部测试
gradlew test

# 指定模块
gradlew :taotao-cloud-message-{module}:test

# 指定测试类
gradlew test --tests "*{TestClass}*"
```

### 2. 生成覆盖率报告
```bash
gradlew jacocoTestReport
# 报告位置: build/reports/jacoco/test/html/index.html
```

### 3. 质量检查
```bash
gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain
```

## 输出

测试完成后报告：总数、通过数、失败数、失败详情（类名 + 方法名 + 错误信息）。
