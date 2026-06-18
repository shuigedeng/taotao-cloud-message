---
description: DDD 代码审查 — 检查领域模型、架构合规、代码质量
parameters:
  - name: scope
    type: string
    enum: [domain, application, infrastructure, interfaces, all]
    default: all
---

# 代码审查 — taotao-cloud-message

## 审查维度

### 1. 领域模型合规
- [ ] 聚合根是否维护了内部不变量（业务规则在聚合内，而非在 Service）
- [ ] 值对象是否不可变（final 字段、无 setter、构造时自验证）
- [ ] 跨聚合是否通过 ID 引用而非对象引用
- [ ] Domain 层是否零 Spring 注解

### 2. 架构合规
- [ ] 依赖方向: `interfaces → application → domain ← infrastructure`
- [ ] 事务边界是否仅开在 `application/service/` 层
- [ ] Controller 是否不含业务逻辑（仅参数校验 + 响应封装）
- [ ] Application Service 是否不包含业务规则判断

### 3. 代码风格
- [ ] DTO 命名: `*Cmd` / `*Qry` / `*CO`
- [ ] 包路径: 按 DDD 分层（domain/entity, domain/valueobject 等）
- [ ] Controller 按角色分包（buyer/seller/manager/mall/inner）

### 4. 项目特定禁止项
- [ ] 聚合根中注入 Repository 或 Domain Service
- [ ] Controller 中直接调用 Repository
- [ ] Application Service 中包含业务规则判断
- [ ] 值对象中包含业务行为以外的逻辑
