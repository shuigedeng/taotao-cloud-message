# 代码风格规范 — taotao-cloud-message

## 格式化规则
- 缩进: 4 个空格（不使用 Tab）
- 行宽: 120 字符
- 大括号: K&R 风格（左括号不换行）
- 编码: UTF-8

## 命名规范

| 元素 | 规范 | 示例 |
|------|------|------|
| 包名 | 全小写 | `com.taotao.cloud.message.domain.entity` |
| 类名 | PascalCase | `DeptEntity`, `DictSaveCmd` |
| 接口 | PascalCase（无 I 前缀） | `DeptDomainRepository`, `AlipayConfigService` |
| 方法 | 小驼峰 | `checkName()`, `findById()` |
| 常量 | UPPER_SNAKE | `TABLE_NAME`, `DEFAULT_PAGE_SIZE` |
| 枚举 | UPPER_SNAKE | `PENDING`, `PROCESSED` |

## DTO 命名后缀
- Command: `*Cmd` — `DictSaveCmd`, `DictUpdateCmd`
- Query: `*Qry`, `*PageQry` — `DictQry`, `DictPageQry`
- Client Object: `*CO` — `DictQueryCO`, `DeptTreeCO`
- PO: `*PO` — `MessagePO`
- DO: `*DO` — `DeptDO`, `DictDO`
- Entity: `*Entity` — `DeptEntity`

## 导入顺序
1. Java 标准库 (`java.*`, `javax.*`, `jakarta.*`)
2. 第三方库 (`org.*`, `com.*`)
3. Spring 框架 (`org.springframework.*`)
4. taotao-boot 框架 (`com.taotao.boot.*`)
5. 项目内部包 (`com.taotao.cloud.message.*`)
6. 静态导入

## Lombok 使用规范

```java
@Setter @Getter @ToString       // 标准 POJO（最常用）
@RequiredArgsConstructor         // 构造器注入（Controller/Service）
@AllArgsConstructor @NoArgsConstructor  // PO / Command
@EqualsAndHashCode               // DTO / 值对象
@Slf4j                           // 日志（可选，倾向于 lombok.extern.slf4j）
@SuperBuilder                    // 领域事件
@Accessors(chain = true)         // 链式调用（可选）
```

## 包路径规范

```
domain:         com.taotao.cloud.message.domain.{entity|valueobject|event|repository|service}
application:    com.taotao.cloud.message.application.{service|dto/{own|external}/{entity}/{cmmond|query|clientobject}|event|handler}
infrastructure: com.taotao.cloud.message.infrastructure.{persistent/{persistence|repository|mapper}|repository|channels|event|configuration}
interfaces:     com.taotao.cloud.message.interfaces.controller.{buyer|seller|manager|mall|inner}
api:            com.taotao.cloud.message.api.{rpc|inner}
```
