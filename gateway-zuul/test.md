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
 
 ### 二. Zuul 服务 API 网关微服务之代理与反向代理（自定义路径配置，给 user-provider-reg 微服务添加前缀地址，反向代理用户微服务）：
  1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
      测试二，自定义路径配置，给 user-provider-reg 微服务添加前缀地址，反向代理用户微服务
      "zuul: 
         routes: 
             user-provider-reg: /user/**"
  2. 启动 discovery-eureka 模块服务，启动1个端口；
  3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
  4. 启动 movie-consumer 模块服务，启动1个端口；
  5. 启动 gateway-zuul 模块服务；
  6. 新起网页页签，输入 http://localhost:7900/user/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
  7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来； 
      总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
  8. 新起网页页签，然后输入 http://localhost:8150/user-provider-reg/user/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
  9. 新起网页页签，然后输入 http://localhost:8150/movie-consumer/movie/4，正常情况下是能看到 ID != 0 一堆用户信息被打印出来； 
      总结二：第8. 9步也能正常打印用户信息，说明 API 网关已经生效了，可以通过API服务器地址链接各个微服务的 http://localhost:8150/serviceId/path 这样的路径来访问了；
  10. 新起网页页签，然后输入 http://localhost:8150/user/user/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见【用户微服务】的地址被改变生效了，同时被 API 网关反向代理了，也就是说 http 的请求 /user 将被发送到【用户微服务】；
  11. 新起网页页签，然后输入 http://localhost:8150/user/movie/4，正常情况下访问不通，理应访问不通的； 
      总结三：zuul.routes 属性仅仅只是为了给 user-provider-reg 微服务添加了 user 前缀，所以电影微服务加 user 前缀当然访问不通的；
      
### 三. Zuul 服务 API 网关微服务之代理与反向代理（自定义路径配置，给 user-provider-reg 微服务添加前缀地址，反向代理用户微服务，其它代理路径一律失效）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
    测试三，自定义路径配置，给User微服务添加前缀地址，反向代理User微服务，不反向代理电影微服务
     zuul:
        ignoredServices: '*'
        routes:
            user-provider-reg: /user/**
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/user/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
 8. 新起网页页签，然后输入 http://localhost:8150/user-provider-reg/user/3，正常情况下不能被代理了，访问页面不存在，出现404错误码；
 9. 新起网页页签，然后输入 http://localhost:8150/movie-consumer/movie/4，正常情况下不能被代理了，访问页面不存在，出现404错误码；
 总结二：第8. 9步访问出现404错误码，说明通过 http://localhost:8150/serviceId/path 代理路径访问 API 网关已经失效了；
 10. 新起网页页签，然后输入 http://localhost:8150/user/user/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见【用户微服务】的地址被改变生效了，同时被 API 网关反向代理了，也就是说 http 的请求 /user 将被发送到【用户微服务】；
 11. 新起网页页签，然后输入 http://localhost:8150/user/movie/4，正常情况下访问不通，理应访问不通的；
 总结三：zuul.routes ignoredServices 忽略禁用了所有代理路径，但仅仅只是为了给 user-provider-reg 微服务添加了 user 前缀供反向代理路径访问，所以电影微服务加 user 前缀当然访问不通的；

### 四. Zuul 服务 API 网关微服务之代理与反向代理（自定义路径配置，给 user-provider-reg 微服务添加前缀地址，代理. 反向代理用户微服务，忽略禁用 movie-consumer 代理. 反向代理路径）：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
     测试四，自定义路径配置，给 user-provider-reg 微服务添加前缀地址，代理. 反向代理用户微服务，忽略禁用 movie-consumer 代理. 反向代理路径
     zuul:
         ignoredServices: movie-consumer
         routes:
            user-provider-reg: /user/**
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 movie-consumer 模块服务，启动1个端口；
 5. 启动 gateway-zuul 模块服务；
 6. 新起网页页签，输入 http://localhost:7900/user/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 7. 新起网页页签，输入 http://localhost:7901/movie/3 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
 总结一：第6. 7步正常，说明 user-provider-reg. movie-consumer 两个服务目前正常；
 8. 新起网页页签，然后输入 http://localhost:8150/user-provider-reg/user/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见【用户微服务】的地址被改变生效了，同时被 API 网关反向代理了，也就是说 http 的请求 /user 将被发送到【用户微服务】；
 9. 新起网页页签，然后输入 http://localhost:8150/movie-consumer/movie/4，正常情况下不能被代理了，访问页面不存在，出现404错误码；
 总结二：zuul.routes ignoredServices 忽略禁用了 movie-consumer 【电影微服务】的代理路径，所以电影微服务的代理路径当然访问不通的；
 10. 新起网页页签，然后输入 http://localhost:8150/user/user/3，正常情况下是能看到 ID != 0 一堆用户信息被打印出来，可见【用户微服务】的地址被改变生效了，同时被 API 网关反向代理了，也就是说 http 的请求 /user 将被发送到【用户微服务】；
 11. 新起网页页签，然后输入 http://localhost:8150/user/movie/4，正常情况下访问不通，理应访问不通的；
 总结三：zuul.routes ignoredServices 忽略禁用了 movie-consumer 【电影微服务】的代理路径，所以电影微服务的代理路径当然访问不通的；
 
 注意：测试三. 测试四的区别在于，ignoredServices 属性的设置，影响的是 movie-consumer 微服务的代理路径是否可以访问；