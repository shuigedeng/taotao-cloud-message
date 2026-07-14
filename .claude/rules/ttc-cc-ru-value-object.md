# 值对象设计规范 — taotao-cloud-message

## 核心特性

### 1. 不可变性
```java
public final class Money {
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("金额不能为负数");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0
            && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
```

### 2. 位置
项目中的值对象位于 `domain/valueobject/`（注意：不是 `valobj/`）

### 3. 枚举值对象（在 common 模块）
```java
// 枚举定义在 common/enums/
public enum MessageStatusEnum {
    PENDING("待发送"),
    SENT("已发送"),
    FAILED("发送失败"),
    READ("已读");

    private final String description;
    // getter...
}
```

## 设计要点
1. **构造时自验证**: 传入数据不合法则抛出异常
2. **无 setter**: 所有字段 final，通过构造器赋值
3. **覆写 equals/hashCode**: 基于所有属性值判断相等性
4. **无标识**: 值对象没有唯一标识（ID）
5. **行为内聚**: 值对象可以包含业务行为方法
