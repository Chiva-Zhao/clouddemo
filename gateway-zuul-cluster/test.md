### 一. Zuul 网关模块添加 listOfServers 属性，达到客户端负载均衡的能力：
 1. 编写 application.yml 文件，添加应用程序的注解 EnableZuulProxy 配置；
     测试一，API网关模块发现应用入口（添加 listOfServers 属性，测试 zuul 的负载均衡功能）
     zuul:
         routes:
             hmily:
                 path: /user-serviceId/**
                 serviceId: user-provider-reg

     注意，这里在运行的时候有个坑，如果以树形展开写法的话，那么就会出错了，所以这个配置还是避免用树形写法
     ribbon.eureka.enabled: false
     user-provider-reg: # 这里是 ribbon 要请求的微服务的 service-id 值
         ribbon:
            listOfServers: http://localhost:7900,http://localhost:7899,http://localhost:7898
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 user-provider-reg 模块服务，启动1个端口（application.yml 文件中的 appname 属性不去掉的话，测试一是无法测试通过的）；
 4. 启动 springms-gateway-zuul-cluster 模块服务；
 5. 新起网页页签，输入 http://localhost:7900/simple/5 正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
    * 总结一：第5步正常，说明 user-provider-reg 服务目前正常；
 6. 新起网页页签，然后输入 http://localhost:8165/user-provider-reg/simple/6，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
    * 总结二：第6步也能正常打印用户信息，说明 API 网关已经生效了，可以通过API服务器地址链接各个微服务的 http://localhost:8150/serviceId/path 这样的路径来访问了；
 7. 新起网页页签，然后输入 http://localhost:8165/user-serviceId/simple/1，正常情况下是能看到 ID != 0 一堆用户信息被打印出来；
    * 总结三：path. serviceId 设置的反向代理路径也通了；
 8. 清除 user-provider-reg 模块控制台的所有的日志，然后刷新 9 次该地址 http://localhost:8165/user-serviceId/simple/1 的网页，然后会发现 3 个用户服务都各打印了3次，再多刷新几次，会发现该负载均衡的调度的算法是轮论调，依次轮询调用每个用户服务微服务；
 总结四：listOfServers 属性也生效了，从而说明添加 listOfServers 也可以达到 zuul 负载均衡的能力；