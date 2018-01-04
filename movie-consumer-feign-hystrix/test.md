### 定制Feign，一个Feign功能禁用Hystrix，另一个Feign功能启用Hystrix（禁用其中一个 Feign 的断路器功能）：

 1. 编写：. FeignClientFallback, . FeignClientFallback2 断路器回退客户端类；
 2. 编写 EurekaAuthConfiguration 配置，加入禁用 Hystrix 模块功能的代码，表示禁用该配置的客户端模块；
 3. 启动 discovery-eureka 模块服务，启动1个端口；
 4. 启动 user-provider-reg 模块服务，启动1个端口；
 5. 启动 movie-consumer-feign-hystrix 模块服务；
 6. 在浏览器输入地址 http://localhost:8110/movie/1 可以看到信息成功的被打印出来，表明正常情况下一切正常；
 7. 在浏览器输入地址 http://localhost:8110/user-provider-reg 可以看到信息成功的被打印出来，表明正常情况下一切正常；
 8. 关闭 user-provider-reg 模块服务；
 9. 在浏览器输入地址 http://localhost:8110/movie/1 可以看到用户ID = 0 的用户信息被打印出来，表明该模块的Hystrix断路器模块起作用了；
 10. 再关闭 discovery-eureka 模块服务；
 11. 再在浏览器输入地址 http://localhost:8110/appname-user-provider-reg 可以看到网页已经打印出链接不上服务的错误页面了，表明该模块禁用Hystrix断路器模块也起作用了；