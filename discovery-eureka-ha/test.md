### 测试一：EurekaServer集群高可用注册中心以及简单的安全认证（正常测试）：
 1. 注解：EnableEurekaClient
 2. 按照上面依次运行启动 EurekaServerHaApplication_8401_peer1. EurekaServerHaApplication_8402_peer2. EurekaServerHaApplication_8403_peer3 模块服务，启动3个端口；
 3. 在浏览器输入地址 http://localhost:8401 并输入用户名密码 admin/admin 进入 8401 端口这台服务正常启动；
 4. 在浏览器输入地址 http://localhost:8402 并输入用户名密码 admin/admin 进入 8402 端口这台服务正常启动；
 5. 在浏览器输入地址 http://localhost:8403 并输入用户名密码 admin/admin 进入 8403 端口这台服务正常启动；
 6. 注意一下，当前已经注册的实例信息里面，都有三台微服务，说明小小的高可用集群已经呈现在大家的眼前了；
### 测试二：用户微服务接口测试（采用 user-provider-reg 给 discovery-eureka-ha 模块做测试，测试EurekaClient客户端注册进EurekaServer高可用集群中）：
 1. 注解：EnableEurekaClient
 2. 修改 defaultZone 的接入地址值如下：
 defaultZone: http://admin:admin@peer1:8401/eureka,http://admin:admin@peer2:8402/eureka,http://admin:admin@peer3:8403/eureka
 3. 启动 discovery-eureka-ha 模块服务，启动3个端口；
 4. 启动 user-provider-reg 模块服务，启动1个端口；
 5. 在浏览器输入地址http://localhost:7900/user/1 可以看到信息成功的被打印出来，说明用户微服务正常；
 6. 在浏览器输入地址 http://localhost:8401 并输入用户名密码 admin/admin 进入Eureka微服务显示在网页中，说明用户微服务确实注册到了 eureka 服务中；
 7. 在浏览器输入地址 http://localhost:8401/eureka/apps/user-provider-reg 可以看到自定义的 <metadata>信息以及用户微服务的相关信息成功的被展示出来了；
 8. 在浏览器输入地址 http://localhost:8402/eureka/apps/user-provider-reg 可以看到自定义的 <metadata>信息以及用户微服务的相关信息成功的被展示出来了；
 9. 在浏览器输入地址 http://localhost:8403/eureka/apps/user-provider-reg 可以看到自定义的 <metadata>信息以及用户微服务的相关信息成功的被展示出来了；
 