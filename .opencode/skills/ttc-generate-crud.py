#!/usr/bin/env python3
"""
taotao-cloud-message DDD CRUD 代码生成器

生成 DDD 分层架构的标准 CRUD 代码，包括:
- domain: 聚合根、值对象、仓储接口、领域事件
- application: 命令/查询服务、DTO
- infrastructure: 持久化 PO、Assembler、仓储实现
- interfaces: Controller

用法: python generate-crud.py --entity=Order --module=order
"""

import argparse
import os
import re
from pathlib import Path


def to_camel(snake_str):
    """snake_case -> PascalCase"""
    return "".join(x.capitalize() for x in snake_str.lower().split("_"))


def to_snake(pascal_str):
    """PascalCase -> snake_case"""
    return re.sub(r"(?<!^)(?=[A-Z])", "_", pascal_str).lower()


TEMPLATES = {
    # === DOMAIN 层 ===
    "domain/aggregate/{Entity}Agg.java": """package com.taotao.cloud.message.domain.{module}.aggregate;

import java.util.Objects;

public class {Entity}Agg {{

    private Long id;

    public static {Entity}Agg create() {{
        {Entity}Agg agg = new {Entity}Agg();
        return agg;
    }}

    public Long getId() {{ return id; }}
}}
""",

    "domain/entity/{Entity}Entity.java": """package com.taotao.cloud.message.domain.{module}.entity;

import com.taotao.boot.ddd.model.domain.AggregateRoot;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@ToString
@Schema(name = "{Entity}Entity", description = "{Entity}实体")
public class {Entity}Entity extends AggregateRoot<Long> {{
}}
""",

    "domain/valueobject/{Entity}Status.java": """package com.taotao.cloud.message.domain.{module}.valueobject;

public enum {Entity}Status {{
    PENDING("待处理"),
    PROCESSED("已处理"),
    CLOSED("已关闭");

    private final String description;

    {Entity}Status(String description) {{
        this.description = description;
    }}

    public String getDescription() {{ return description; }}
}}
""",

    "domain/event/{Entity}CreatedEvent.java": """package com.taotao.cloud.message.domain.{module}.event;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class {Entity}CreatedEvent {{
    private Long {entity}Id;
    private LocalDateTime occurredAt;
}}
""",

    "domain/repository/{Entity}DomainRepository.java": """package com.taotao.cloud.message.domain.{module}.repository;

import com.taotao.cloud.message.domain.{module}.entity.{Entity}Entity;
import java.util.Optional;

public interface {Entity}DomainRepository {{
    Optional<{Entity}Entity> findById(Long id);
    {Entity}Entity save({Entity}Entity {entity}Entity);
    void deleteById(Long id);
}}
""",

    "domain/service/{Entity}DomainService.java": """package com.taotao.cloud.message.domain.{module}.service;

import com.taotao.cloud.message.domain.{module}.entity.{Entity}Entity;

public interface {Entity}DomainService {{
    void create({Entity}Entity {entity}Entity);
    void modify({Entity}Entity {entity}Entity);
    void remove(Long[] ids);
}}
""",

    # === APPLICATION 层 ===
    "application/dto/own/{entity}/cmmond/{Entity}SaveCmd.java": """package com.taotao.cloud.message.application.dto.own.{entity}.cmmond;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "创建{Entity}命令")
public class {Entity}SaveCmd implements Serializable {{
    @Serial private static final long serialVersionUID = 1L;
}}
""",

    "application/dto/own/{entity}/query/{Entity}Qry.java": """package com.taotao.cloud.message.application.dto.own.{entity}.query;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class {Entity}Qry implements Serializable {{
    @Serial private static final long serialVersionUID = 1L;
}}
""",

    "application/dto/own/{entity}/clientobject/{Entity}CO.java": """package com.taotao.cloud.message.application.dto.own.{entity}.clientobject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "{Entity}查询对象")
public class {Entity}CO implements Serializable {{
    @Serial private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}}
""",

    "application/service/{Entity}CommandService.java": """package com.taotao.cloud.message.application.service;

public interface {Entity}CommandService {{
    void create({Entity}SaveCmd cmd);
    void update({Entity}UpdateCmd cmd);
    void delete(Long id);
}}
""",

    "application/service/impl/{Entity}CommandServiceImpl.java": """package com.taotao.cloud.message.application.service.impl;

import com.taotao.cloud.message.application.service.{Entity}CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class {Entity}CommandServiceImpl implements {Entity}CommandService {{
    // 注入 {Entity}DomainRepository
}}
""",

    # === INFRASTRUCTURE 层 ===
    "infrastructure/persistent/persistence/{Entity}PO.java": """package com.taotao.cloud.message.infrastructure.persistent.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BasePO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "{table_name}")
@TableName("{table_name}")
public class {Entity}PO extends BasePO<{Entity}PO> {{
    public static final String TABLE_NAME = "{table_name}";

    // fields...
}}
""",

    "infrastructure/repository/{Entity}DomainRepositoryImpl.java": """package com.taotao.cloud.message.infrastructure.repository;

import com.taotao.cloud.message.domain.{module}.entity.{Entity}Entity;
import com.taotao.cloud.message.domain.{module}.repository.{Entity}DomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class {Entity}DomainRepositoryImpl implements {Entity}DomainRepository {{
    // 注入 JPA Repository
}}
""",

    # === INTERFACES 层 ===
    "interfaces/controller/buyer/{Entity}BuyerController.java": """package com.taotao.cloud.message.interfaces.controller.buyer;

import com.taotao.boot.common.model.Result;
import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/buyer/{entity}")
@Tag(name = "买家端-{Entity}API")
public class {Entity}BuyerController extends BusinessController {{
    // private final {Entity}CommandService {entity}CommandService;
}}
""",
}


def generate(args):
    entity_pascal = to_camel(args.entity)
    entity_snake = to_snake(entity_pascal)
    module = args.module
    table_name = f"{module}_{entity_snake}"
    base_dir = Path(args.output) if args.output else Path(".")

    created = []
    for template_path, template_content in TEMPLATES.items():
        # 替换变量
        content = template_content.format(
            Entity=entity_pascal,
            entity=entity_snake,
            module=module,
            table_name=table_name,
        )

        # 生成目标路径
        rel_path = template_path.replace("{Entity}", entity_pascal).replace("{module}", module).replace("{entity}", entity_snake)
        abs_path = base_dir / rel_path

        # 创建目录
        abs_path.parent.mkdir(parents=True, exist_ok=True)

        # 写入（不覆盖已有文件）
        if abs_path.exists():
            print(f"  ⏭️  已存在: {rel_path}")
            continue

        abs_path.write_text(content.lstrip("\n"), encoding="utf-8")
        print(f"  ✅ 生成: {rel_path}")
        created.append(rel_path)

    print(f"\n📊 共生成 {len(created)} 个文件")
    return created


def main():
    parser = argparse.ArgumentParser(description="DDD CRUD 代码生成器")
    parser.add_argument("--entity", required=True, help="实体名（PascalCase，如 Order）")
    parser.add_argument("--module", default="order", help="子模块名（如 order）")
    parser.add_argument("--output", default=None, help="输出目录（默认当前目录）")
    args = parser.parse_args()

    print(f"🔨 生成 {args.entity} CRUD 代码 (module: {args.module})")
    print("=" * 40)
    generate(args)
    print("=" * 40)
    print("✅ 生成完成")


if __name__ == "__main__":
    main()
