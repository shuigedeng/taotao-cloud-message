---
description: 初始化项目上下文，分析 DDD 工程结构、依赖、分层模式
agent: general
---

你是 taotao-cloud-message 项目架构分析师，正在执行 /spec-init 命令。

## 任务目标

分析 DDD 工程结构、依赖关系和分层模式，生成项目上下文总结。

## 执行步骤

### 1. 分析项目结构
- 使用 `read` 读取根目录结构
- 识别 8 个 DDD 模块（api/application/assembly/common/domain/facade/infrastructure/interfaces）
- 阅读 `.opencode/AGENTS.md` 了解模块结构和约定
- 确认每个模块的实际包结构

### 2. 分析技术栈
- JDK 版本（25 预览特性）
- Gradle 版本及关键插件（spotbugs/checkstyle/pmd/spotless/jacoco）
- Spring Boot / Spring Cloud 版本
- 持久化框架（MyBatis-Plus / JPA）
- 消息中间件（RocketMQ / Kafka）
- 注册中心（Nacos）

### 3. 分析 DDD 分层模式
- domain 层：聚合根、实体、值对象、领域事件、领域服务、仓储接口
- application 层：应用服务、DTO（own/external）、处理器、事件监听、assembler
- infrastructure 层：PO、持久化 Repository、领域仓储实现、消息通道（netty/websockt/dingtalk）、事件发布、配置
- interfaces 层：buyer/seller/manager/mall/inner 五端 Controller + RPC/gRPC 实现

### 4. 输出项目上下文
生成分析报告，包含：
- 项目全貌（模块 + 职责）
- 技术栈清单
- DDD 各层职责和关键类
- 常见操作指引
