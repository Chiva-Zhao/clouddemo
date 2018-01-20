### 一. 配置刷新服务客户端Client应用入口（单点手动动态刷新配置服务客户端配置）：
 1. 添加注解 RefreshScope，然后添加引用模块 spring-boot-starter-actuator 监控和管理生产环境的模块；
 2. 编辑 application.yml 文件，添加相关客户端配置；
     ```spring:
         cloud:
             config:
                 uri: http://localhost:8220
                 profile: refresh
                 label: master #当 ConfigServer 的后端存储的是 Git 的时候，默认就是 master
         application:
            name: foobar  #取 foobar-refresh.yml 这个文件的 application 名字，即为 foobar 名称
      ```
 3. 启动 config-server 模块服务，启动1个端口；
 4. 启动 config-client-refresh 模块服务，启动1个端口；
 5. 在浏览器输入地址 http://localhost:8295/profile 正常情况下会输出远端服务的配置内容（内容为：profile: profile-refresh）；
 6. 修改 blob/master/foobar-refresh.yml 内容，修改后为 profile: profile-refresh-refresh；
 7. 打开windows命令窗口，执行命令： >curl.exe -X POST http://localhost:8295/refresh
 8. 然后刷新 http://localhost:8295/profile 网页，正常情况下会输出远端服务的配置内容（内容为：profile: profile-refresh-refresh）；
    * 总结：这里通过执行刷新命令才得以将远端配置内容刷新到配置服务客户端。