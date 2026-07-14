---
name: event-storming
description: 事件风暴工作流，识别领域事件、命令和聚合
triggers:
  - "事件风暴"
  - "领域建模"
  - "识别聚合"
---

# 事件风暴工作流 — taotao-cloud-message

## 步骤1：识别领域事件
从业务需求中提取关键事件：
- 消息发送、接收、读取、失败

## 步骤2：识别命令
每个事件对应一个用户/系统命令。

## 步骤3：识别聚合
根据事件和命令确定聚合根和边界。

## 步骤4：映射到 DDD 分层
- **domain/** — 聚合根、值对象、领域事件
- **application/** — 应用服务（用例编排）
- **infrastructure/** — 仓储实现、消息通道

## 输出
- `domain/event/` — 领域事件类
- `domain/entity/` — 聚合根/实体
- `domain/valueobject/` — 值对象
