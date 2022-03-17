# venus 核心 starter

对常用功能模块封装，包括：

* 全局配置
* 全局异常
* 全局统一请求返回对象
* 全局工具类
* 全局常量
* 其他

## Jackson

`JacksonConfig` 配置了 http 请求时各种情况下的对不同时间类型数据的处理。主要针对 `GET` 请求时携带时间类型时的序列化策略。

另外对于`Long`类型的数据，在序列化时会自动转化为`String`传递，避免前端 JS 接收数据时出现精度丢失问题。

设置`Jackson`默认时区为：Asia/Shanghai。

针对时间参数在`GET` 请求下传递的测试结果如下：

**正常外部接口请求**

| 时间类型          | 序列化格式               | 是否异常 |
|---------------|---------------------|------|
| LocalDate     | yyyy-MM-dd          | 正常   |
| LocalTime     | HH:mm:ss            | 正常   |
| LocalDateTime | yyyy-MM-dd HH:mm:ss | 正常   |
| Date          | yyyy-MM-dd HH:mm:ss | 正常   |

**Feign请求**

| 时间类型          | 序列化格式               | 是否异常 |
|---------------|---------------------|------|
| LocalDate     | yyyy-MM-dd          | 正常   |
| LocalTime     | HH:mm:ss            | 正常   |
| LocalDateTime | yyyy-MM-dd HH:mm:ss | 异常   |
| Date          | yyyy-MM-dd HH:mm:ss | 正常   |

> 结论：针对测试结果，建议项目全局使用`Date`类型或`Long`类型的时间戳进行时间参数的传递。

Feign 在处理 `GET` 请求时，不允许使用 `Request Body`，且`URL拼接`或`@RequestParam("value")`会直接调用对象的`toString()`
方法转为字符串进行参数传递。而`LocalDateTime`的`toString()`方法如下：

```java
class LocalDateTime {
    @Override
    public String toString() {
        return date.toString() + 'T' + time.toString();
    }
}
```

所以会导致 Jackson 解析错误。测试代码在 `venus-example-jackson` 模块内。