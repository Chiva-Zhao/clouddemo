### 一. 电影Ribbon 微服务集成 Hystrix 断路器实现失败快速响应，达到熔断效果：

 1. 注解：EnableCircuitBreaker. HystrixCommand 的编写；
 2. 启动 springms-provider-user 模块服务，启动1个端口；
 3. 启动 movie-consumer-ribbon-hystrix 模块服务；
 4. 在浏览器输入地址http://localhost:8070/movie/1，然后页面的信息是否有打印出来用户的Id=0的情况，正常情况下是没有用户Id=0的情况信息打印的；
 5. 杀死 springms-provider-user 模块服务，停止提供服务；
 6. 在浏览器输入地址http://localhost:8070/movie/1，然后页面的信息是否有打印出来用户的Id=0的情况，等了1秒中后有用户Id=0的情况信息打印出来；
 7. 等一会儿在启动 user-provider-reg 模块服务，启动1个端口；
 8. 在浏览器输入地址http://localhost:8070/movie/1，然后页面的信息又有Id!=0的用户信息打印出来；

 * 总结：当远端微服务宕机或者不可用时，Hystrix已经达到快速响应快速失败，起到了熔断机制的效果。