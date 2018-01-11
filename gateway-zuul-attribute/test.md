### 一. Zuul 网关微服务的一些属性应用测试（自定义路径配置，给 user-provider-reg 微服务添加 path. serviceId 属性前缀地址，反向代理用户微服务）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
  测试一，自定义路径配置，给 user-provider-reg 微服务添加 path. serviceId 属性前缀地址，反向代理用户微服务
    zuul:
        routes:
            hmily:
                path: /custom-path/**
                serviceId: user-provider-reg # 注意这个名称是注册在eureka服务中的名称
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul-attribute 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/simple/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
 8. 新起网页页签，然后输入 http://localhost:8155/user-provider-reg/simple/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 9. 新起网页页签，然后输入 http://localhost:8155/movie-consumer/movie/4，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结二：第8. 9步也能正常打印用户信息，说明 API 网关已经生效了，可以通过API服务器地址链接各个微服务的 http://localhost:8150/serviceId/path 这样的路径来访问了；
 10. 新起网页页签，然后输入 http://localhost:8155/custom-path/simple/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见【用户微服务】的地址被改变生效了，同时被 API 网关反向代理了，也就是说 http 的请求 /custom-path 将被发送到【用户微服务】；
 11. 新起网页页签，然后输入 http://localhost:8155/custom-path/movie/4，正常情况下访问不通，理应访问不通的；
 总结三：path. serviceId 属性仅仅只是为了给 user-provider-reg 微服务添加了 custom-path 前缀，所以电影微服务加 custom-path 前缀当然访问不通的；

 ### 二. Zuul 网关微服务的一些属性应用测试（自定义路径配置，给 user-provider-reg 微服务添加 path. serviceId 属性前缀地址，反向代理用户微服务）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
 测试二，自定义路径配置，给 user-provider-reg 微服务添加 path. serviceId 属性前缀地址，反向代理用户微服务
    zuul:
        routes:
            hmily:
                path: /custom-path/**
                serviceId: http://localhost:7900/ # 注意这个名称是 url 地址
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul-attribute 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/simple/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
 8. 新起网页页签，然后输入 http://localhost:8155/user-provider-reg/simple/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 9. 新起网页页签，然后输入 http://localhost:8155/movie-consumer/movie/4，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结二：第8. 9步也能正常打印用户信息，说明 API 网关已经生效了，可以通过API服务器地址链接各个微服务的 http://localhost:8150/serviceId/path 这样的路径来访问了；
 10. 新起网页页签，然后输入 http://localhost:8155/custom-path/simple/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见【用户微服务】的地址被改变生效了，同时被 API 网关反向代理了，也就是说 http 的请求 /custom-path 将被发送到【用户微服务】；
 11. 新起网页页签，然后输入 http://localhost:8155/custom-path/movie/4，正常情况下访问不通，理应访问不通的；
 总结三：path. serviceId 属性仅仅只是为了给 user-provider-reg 微服务添加了 custom-path 前缀，所以电影微服务加 custom-path 前缀当然访问不通的；
 注意：测试一. 测试二的区别在于 serviceId 的不同，一个是注入eureka中的服务名称，一个是url地址，但是两者效果都是一样的；

### 三. Zuul 网关微服务的一些属性应用测试（自定义路径配置，给微服务添加 prefix 属性前缀地址，反向代理所有微服务）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
 测试三，自定义路径配置，给微服务添加 prefix 属性前缀地址，反向代理所有微服务
    zuul:
        prefix: /api
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul-attribute 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/simple/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
 8. 新起网页页签，然后输入 http://localhost:8155/user-provider-reg/simple/3，正常情况下访问不通，理应访问不通的；
 9. 新起网页页签，然后输入 http://localhost:8155/movie-consumer/movie/4，正常情况下访问不通，理应访问不通的；
 总结二：第8. 9步访问不通，说明 prefix 属性生效了，通过 http://localhost:8150/serviceId/path 这样的路径来访问已经行不通了；
 10. 新起网页页签，然后输入 http://localhost:8155/api/simple/3，正常情况下访问不通，理应访问不通的；
 11. 新起网页页签，然后输入 http://localhost:8155/api/movie/4，正常情况下访问不通，理应访问不通的；
 总结三：第10. 11步访问不通，说明 prefix 属性生效了，通过 http://localhost:8150/api/path 这样的路径来访问也行不通了；
 12. 新起网页页签，然后输入 http://localhost:8155/api/user-provider-reg/simple/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见 prefix 属性添加前缀地址被改变生效了；
 13. 新起网页页签，然后输入 http://localhost:8155/api/movie-consumer/movie/4，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见 prefix 属性添加前缀地址被改变生效了；
 总结四：zuul.prefix 属性给所有路径添加了一个前缀，即需要通过 http://localhost:8150/api/serviceId/path 这样的地址才可以访问成功；

### 四. Zuul 网关微服务的一些属性应用测试（自定义路径配置，给微服务添加 strip-prefix 属性前缀地址，反向代理所有微服务）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
 测试三，自定义路径配置，给微服务添加 prefix 属性前缀地址，反向代理所有微服务
    zuul:
        prefix: /api
        strip-prefix: false
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul-attribute 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/simple/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
 8. 新起网页页签，然后输入 http://localhost:8155/user-provider-reg/simple/3，正常情况下访问不通，理应访问不通的；
 9. 新起网页页签，然后输入 http://localhost:8155/movie-consumer/movie/4，正常情况下访问不通，理应访问不通的；
 总结二：第8. 9步访问不通，说明 prefix 属性生效了，通过 http://localhost:8150/serviceId/path 这样的路径来访问已经行不通了；
 10. 新起网页页签，然后输入 http://localhost:8155/api/simple/3，正常情况下访问不通，理应访问不通的；
 11. 新起网页页签，然后输入 http://localhost:8155/api/movie/4，正常情况下访问不通，理应访问不通的；
 总结三：第10. 11步访问不通，说明 prefix 属性生效了，通过 http://localhost:8150/api/path 这样的路径来访问也行不通了；
 12. 新起网页页签，然后输入 http://localhost:8155/api/user-provider-reg/simple/3，正常情况下访问不通，理应访问不通的；
 13. 新起网页页签，然后输入 http://localhost:8155/api/movie-consumer/movie/4，正常情况下访问不通，理应访问不通的；
 14. 那么问题来了，这种配置为什么访问不通呢？由于查看日志，发现这种请求的被打印出来的路径为：
    http://localhost:8155/api/user-provider-reg/simple/3
    user-provider-reg using LB returned Server: 192.168.3.101:7900 for request /api/simple/3
    http://localhost:8155/api/movie-consumer/movie/4
    movie-consumer using LB returned Server: 192.168.3.101:7901 for request /api/movie/4
 15. 试想，要么去掉 /api 前缀，如果这样的话，那么就没有设置 prefix 的必要了，然后做了这样的测试如下：
 16. 新起网页页签，然后输入 http://localhost:8155/api/user-provider-reg/3，正常情况下访问不通，理应访问不通的；
 17. 新起网页页签，然后输入 http://localhost:8155/api/movie-consumer/4，正常情况下访问不通，理应访问不通的；
 18. 这种配置为什么访问不通呢？由于查看日志，发现这种请求的被打印出来的路径为：
    http://localhost:8155/api/user-provider-reg/3
    user-provider-reg using LB returned Server: 192.168.3.101:7900 for request /api/3
    http://localhost:8155/api/movie-consumer/4
    movie-consumer using LB returned Server: 192.168.3.101:7901 for request /api/4
 19. 由步骤14. 18的日志可以看出来，请求的地址非常有规律，那我们试想，如果将 api 的值改掉呢？是否可以成功呢？请看下面测试五！！！

### 五. Zuul 网关微服务的一些属性应用测试（自定义路径配置，针对测试四，再次修改 prefix 属性前缀地址，反向代理用户微服务）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
    测试四，自定义路径配置，针对测试四，再次修改 prefix 属性前缀地址，反向代理用户微服务
    zuul:
        prefix: /simple
        strip-prefix: false
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul-attribute 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/simple/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常
 8. 新起网页页签，然后输入 http://localhost:8155/simple/user-provider-reg/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见 prefix 属性添加前缀地址被改变生效了；
 9. 新起网页页签，然后输入 http://localhost:8155/user/movie-consumer/4，正常情况下访问不通，理应访问不通的；
 总结二：之所以 movie-consumer 访问不通，因为修改 prefix 只是这个修改的值正好和 user-provider-reg 的接口前缀恰好一致而已；