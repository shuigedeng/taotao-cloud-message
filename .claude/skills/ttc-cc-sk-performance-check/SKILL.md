---
name: performance-check
description: 性能检查 — SQL 分析、缓存策略、并发性能
triggers:
  - "性能检查"
  - "SQL优化"
  - "慢查询"
---

# 性能检查 — taotao-cloud-message

## 检查项
1. **N+1 查询**: 检查 JPA 关联查询
2. **SELECT \***: 避免全字段查询
3. **批量操作**: 使用批处理方法
4. **缓存策略**: Redis 缓存使用是否合理
5. **索引使用**: JOIN 字段是否有索引
6. **连接池**: 连接池配置是否合理

## 参考
- `infrastructure/configuration/cache/` — 缓存配置
- `gradle/spotbugs.gradle` — 静态分析配置
