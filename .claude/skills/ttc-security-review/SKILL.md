---
name: security-review
description: 安全审查 — 敏感信息、SQL 注入、权限控制
triggers:
  - "安全审查"
  - "安全检查"
---

# 安全审查 — taotao-cloud-message

## 检查内容
1. **敏感信息泄露**: 搜索 `password`, `secret`, `token`, `jwt-secret`, `access-key`
2. **SQL 注入**: 检查 `@Query` 和原生 SQL 的参数绑定
3. **权限控制**: 检查 `@PreAuthorize` 或自定义权限注解
4. **跨聚合访问**: domain 层是否直接操作持久化
5. **XSS**: 用户输入是否过滤

## 执行
```bash
# 使用安全扫描脚本
bash .opencode/skills/security-scan.sh
```
