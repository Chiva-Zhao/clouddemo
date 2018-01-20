### Rabbitmq 安装步骤（进入 Rabbitmq 官网：http://www.rabbitmq.com）：
 1. 下载 rabbitmq-server-3.6.11.exe. otp_win64_20.0-rc2.exe 两个 windows 安装软件；
 2. 双击安装 otp_win64_20.0-rc2.exe；
 3. 双击安装 rabbitmq-server-3.6.11.exe；
 4. 两个都安装完后会发现服务中多了一个 Rabbitmq 的服务，服务名称为：RabbitMQ；
 5. 如果想查看管理界面的话，执行命令：rabbitmq-plugins enable rabbitmq_management，然后重启 RabbitMQ 服务；
 6. 通过windows命令 netstat -aon|findstr "5672" 查看该端口是否被占用，占用的话，说明安装基本上一切正常；
 7. 通过 http://localhost:15672 地址可以进入服务端的管理页面；
     到此为止，Rabbitmq 已经安装完成
### 一. 配置刷新服务客户端Client应用入口（通过 bus/refresh 实现半自动动态刷新配置服务客户端配置）：
1. 添加注解 RefreshScope，然后添加引用模块 spring-boot-starter-actuator 监控和管理生产环境的模块；
2. 编辑 application.yaml. bootstrap.yaml 文件，添加相关客户端配置；
3. 启动 config-server 模块服务，启动1个端口；
4. 启动 config-client-refresh-bus 模块服务，启动3个端口（8300. 8301. 8302）；
5. 在浏览器输入地址 http://localhost:8300/profile 正常情况下会输出远端服务的配置内容（内容为：profile: profile-refreshbus）；
6. 在浏览器输入地址 http://localhost:8301/profile 正常情况下会输出远端服务的配置内容（内容为：profile: profile-refreshbus）；
7. 在浏览器输入地址 http://localhost:8302/profile 正常情况下会输出远端服务的配置内容（内容为：profile: profile-refreshbus）；
8. 修改 blob/master/foobar-refreshbus.yaml 内容，修改后为 profile: profile-refreshbus-refresh；
9. 打开windows命令窗口，执行命令： >curl.exe -X POST http://localhost:8300/bus/refresh 或者端口选择 8301. 8302 都可以生效；
10. 然后刷新 http://localhost:8300/profile 网页，正常情况下会输出远端服务的配置内容（内容为：profile: profile-refreshbus-refresh）；
11. 然后刷新 http://localhost:8301/profile 网页，正常情况下会输出远端服务的配置内容（内容为：profile: profile-refreshbus-refresh）；
12. 然后刷新 http://localhost:8302/profile 网页，正常情况下会输出远端服务的配置内容（内容为：profile: profile-refreshbus-refresh）；
* 总结：这里通过执行刷新命令，然后将多台 ConfigClient 客户端刷新，来达到获取最新的远端服务器配置。
   但是这里终究还是得靠手动执行一条刷新命令，但总比每台服务器执行刷新命令要好很多； 
### 二. 配置刷新服务客户端Client应用入口（设置 Git 的 WebHooks 属性，通过 Git 提交代码来实现全自动动态刷新配置服务客户端配置）：
 总结：WebHooks 可以设置 POST 的地址，并附上密码，提交代码后动态通知相应服务来实现全自动动态刷新。
### 三. 思考问题：凭什么 8300. 8301. 8302 三台服务器其中一台要承受刷新配置服务的任务？不应该三台服务的角色等级应该相同么？
 基于这种角色等同考虑，可以在 ConfigServer 也配上 Rabbitmq 链接上，然后我们在用命令刷新 ConfigServer 即可，这样就实现了三台 ConfigClient 服务器的角色又等同了；
