# API 设计规范 — taotao-cloud-message

## Controller 分包

按角色分包，五端隔离：

```
interfaces/controller/
├── buyer/       # 买家端
├── seller/      # 卖家端
├── manager/     # 管理端
├── mall/        # 商城端
└── inner/       # 内部 API
```

## 路由规范

```
/{role}/{entity}/{action}
```

示例：
- `/sys/buyer/dict/page` — 买家端字典分页查询
- `/sys/seller/dict/add` — 卖家端字典新增
- `/app/page` — 管理端应用分页

## Controller 写法

```java
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/buyer/dict")
@Tag(name = "买家端-字典API")
public class BuyerDictController extends BusinessController {

    // private final DictCommandService dictCommandService;

    @GetMapping("/page")
    @Operation(summary = "分页查询字典")
    public Result<PageResult<DictCO>> page(DictPageQry qry) {
        return Result.success(dictQueryService.pageQuery(qry));
    }

    @PostMapping
    @Operation(summary = "新增字典")
    public Result<Boolean> save(@Valid @RequestBody DictSaveCmd cmd) {
        return Result.success(dictCommandService.save(cmd));
    }
}
```

## 响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": { }
}
```

## 参数校验
- 使用 `jakarta.validation` 注解（`@NotBlank`, `@Size`, `@NotNull` 等）
- Controller 类上加 `@Validated`
- Command 入参上加 `@Valid`

## API 文档
- Controller 类加 `@Tag` 注解
- 方法加 `@Operation(summary = "...")` 
- DTO 字段加 `@Schema(description = "...")`
