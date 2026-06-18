---
name: crud-generator
description: 按 DDD 分层生成标准 CRUD 代码（Domain → Application → Infrastructure → Interfaces）
triggers:
  - "生成CRUD"
  - "创建增删改查"
  - "新建模块"
---

# CRUD 代码生成器 — taotao-cloud-message DDD 版

## 生成结构

按 DDD 分层生成，遵循依赖方向：

### Domain 层
```
domain/entity/{Entity}Entity.java          — 聚合根/实体
domain/valueobject/{Entity}Status.java      — 值对象枚举
domain/event/{Entity}CreatedEvent.java      — 领域事件
domain/repository/{Entity}DomainRepository.java  — 仓储接口
domain/service/{Entity}DomainService.java   — 领域服务接口
```

### Application 层
```
application/dto/own/{entity}/cmmond/{Entity}SaveCmd.java       — 创建命令
application/dto/own/{entity}/cmmond/{Entity}UpdateCmd.java     — 更新命令
application/dto/own/{entity}/query/{Entity}Qry.java            — 查询
application/dto/own/{entity}/query/{Entity}PageQry.java        — 分页查询
application/dto/own/{entity}/clientobject/{Entity}CO.java      — 返回对象
application/service/{Entity}CommandService.java                — 应用服务接口
application/service/impl/{Entity}CommandServiceImpl.java       — 应用服务实现
```

### Infrastructure 层
```
infrastructure/persistent/persistence/{Entity}PO.java           — 持久化对象
infrastructure/repository/{Entity}DomainRepositoryImpl.java     — 仓储实现
```

### Interfaces 层
```
interfaces/controller/buyer/{Entity}BuyerController.java       — 买家端 API
interfaces/controller/manager/{Entity}ManagerController.java   — 管理端 API
```

## 代码模板
参考 CLAUDE.md 和 `.claude/rules/` 下的规范文件。
