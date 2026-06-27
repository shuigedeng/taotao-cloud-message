---
description: 生成 OpenAPI / Swagger 文档
---

# API 文档 — taotao-cloud-message

## 执行步骤

### 1. 启动服务
```bash
gradlew :taotao-cloud-message-assembly:bootRun --args='--spring.profiles.active=dev'
```

### 2. 访问文档
- Knife4j UI: `http://localhost:{port}/doc.html`
- OpenAPI JSON: `http://localhost:{port}/v3/api-docs`

### 3. 检查 API 完整性
- 所有 Controller 是否有 `@Tag` 注解
- 所有接口方法是否有 `@Operation(summary = "...")`
- DTO 字段是否有 `@Schema(description = "...")`
