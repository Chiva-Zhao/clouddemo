### 一. Zuul 网关微服务的 regexmapper 属性测试, 类似测试 zuul 的自定义路径规则一样：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
 2. 在 MsGatewayZuulRegExpApplication 类中添加 serviceRouteMapper 方法；
 3. 启动 discovery-eureka 模块服务，启动1个端口；
 4. 启动 user-provider-reg-version 模块服务，启动1个端口；
 5. 启动 gateway-zuul-regexp 模块服务；
 6. 新起网页页签，输入 http://localhost:8185/version/user-provider-reg/user/4 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 * 总结：user-provider-reg-version 通过名字和版本被切割后，利用路径拼接规则，通过 http://localhost:8185/version/user-provider-reg/user/4 也可以访问用户微服务；
