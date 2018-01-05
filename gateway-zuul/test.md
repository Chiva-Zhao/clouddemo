### 一. Zuul 服务 API 网关微服务之代理与反向代理（正常情况测试）：

 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/user/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 * 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
 8. 新起网页页签，然后输入 http://localhost:8150/user-provider-reg/user/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 9. 新起网页页签，然后输入 http://localhost:8150/movie-consumer/movie/4，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 * 总结二：第8. 9步也能正常打印用户信息，说明 API 网关已经生效了，可以通过API服务器地址链接各个微服务的 http://localhost:8150/serviceId/path 这样的路径来访问了；
 