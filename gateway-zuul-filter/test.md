### 一. Zuul 的过滤器 ZuulFilter 的使用（正常情况测试）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
 2. 修改 PreZuulFilter 的 shouldFilter 方法返回 true 即可，表明要使用过滤功能；
 3. 启动 discovery-eureka 模块服务，启动1个端口；
 4. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 5. 启动 gateway-zuul-filter 模块服务；
 6. 新起网页页签，输入 http://localhost:8215/routes 正常情况下是能看到zuul需要代理的各个服务列表；
 7. 新起网页页签，然后输入 http://localhost:8215/user-provider-reg/user/1 正常情况下是能看到 ID != 0 一堆用户信息被打印出来，并且该zuul的微服务日志控制台会打印一堆 PreZuulFilter 打印的日志内容；
 8. 然后会看到 PreZuulFilter.run 方法中的日志被打印出来，说名确实进入了过滤的方法里面，过滤起作用了；
 总结：过滤确实起了作用，那是因为过滤器的配置中 shouldFilter 设置的 true，需要过滤，所以当然会过滤啦，直接 run 方法中的打印信息即可；

### 二. Zuul 的过滤器 ZuulFilter 的使用（使用过滤器，但是使得过滤的run方法失效）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
 2. 修改 PreZuulFilter 的 shouldFilter 方法返回 false 即可，表明不需要使用过滤器；
 3. 启动 discovery-eureka 模块服务，启动1个端口；
 4. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 5. 启动 gateway-zuul-filter 模块服务；
 6. 新起网页页签，输入 http://localhost:8215/routes 正常情况下是能看到zuul需要代理的各个服务列表；
 7. 新起网页页签，然后输入 http://localhost:8215/user-provider-reg/user/1 正常情况下是能看到 ID != 0 一堆用户信息被打印出来，但是该zuul的微服务日志控制台并不会打印一堆 PreZuulFilter 打印的日志内容；
 8. 然后再看，PreZuulFilter.run 方法中的日志不见了，没有被打印出来，过滤的run方法失效了；
 总结：由此可见，PreZuulFilter 的 shouldFilter 设置为 false，过滤器就已经失去效果了；