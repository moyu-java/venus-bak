# sentinel dashboard

> `sentinel-dashboard-1.8.3.jar` 是添加了自定义数据源持久化的启动包，使用 Nacos 作为规则持久化数据源。

[项目源码](https://github.com/moyu-jun/Sentinel/tree/release-1.8)

## 配置方式

Sentinel 提供如下的配置方式：

* JVM -D 参数方式（推荐）
* properties 文件方式（需要修改源代码，并重新打包）

如果环境确定的话，也可以使用多环境 properties 配置的方式。

**优先级顺序**：JVM -D 参数的优先级最高。若 properties 和 JVM 参数中有相同项的配置，以 JVM 参数配置的为准。

## 配置项列表

在此仅列出部分常用的配置项，完成配置项请见[启动配置项官方文档](https://github.com/alibaba/Sentinel/wiki/%E5%90%AF%E5%8A%A8%E9%85%8D%E7%BD%AE%E9%A1%B9)

**sentinel 配置项**

| 名称                              | 含义                                           | 类型     | 默认值      | 是否必需 |
|---------------------------------|----------------------------------------------|--------|----------|------|
| `server.port`                   | 控制台端口                                        | int    | 8080     | 否    |
| `csp.sentinel.dashboard.server` | 控制台的地址，指定控制台后客户端会自动向该地址发送心跳包。地址格式为：`ip:port` | String | null     | 是    |
| `project.name`                  | 指定应用的名称                                      | String | null     | 否    |
| `auth.username`                 | 控制台用户名                                       | String | sentinel | 是    |
| `auth.password`                 | 控制台密码                                        | String | sentinel | 是    |

**自定义新增配置项**

| 名称                                      | 含义                | 类型     | 默认值            | 是否必需 |
|-----------------------------------------|-------------------|--------|----------------|------|
| `sentinel.nacos.serverAddress           | nacos 地址（ip:port） | String | localhost:8848 | 是    |
| `sentinel.nacos.username`               | nacos 用户名         | String | nacos          | 否    |
| `sentinel.nacos.password`               | nacos 密码          | String | nacos          | 否    |
| `sentinel.nacos.namespace`              | 命名空间              | String | null           | 是    |
| `sentinel.nacos.groupId`                | group id          | String | SENTINEL_GROUP | 否    |
| `sentinel.nacos.flowRulePostfix`        | 限流规则后缀            | String | -flow-rules    | 否    |
| `sentinel.nacos.degradeRulePostfix`     | 降级规则后缀            | String | -degrade-rules | 否    |
| `sentinel.nacos.authorityRulePostfix`   | 授权规则后缀            | String | -auth-rules    | 否    |
| `sentinel.nacos.paramFlowRulePostfix`   | 热点规则后缀            | String | -param-rules   | 否    |
| `sentinel.nacos.gatewayFlowRulePostfix` | 网关限流规则后缀          | String | -gw-flow-rules | 否    |
| `sentinel.nacos.gatewayApiRulePostfix`  | 网关Api限流规则后缀       | String | -gw-api-rules  | 否    |
| `sentinel.nacos.systemRulePostfix`      | 系统规则后缀            | String | -system-rules  | 否    |