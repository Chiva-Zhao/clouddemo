### 一. 配置服务服务端Server应用入口（正常测试）：

 1. 注解：EnableConfigServer
 2. 编辑 application.yml 文件，配置登录密码；
 3. 启动 config-server-auth 模块服务，启动1个端口；
 4. 在浏览器输入地址 http://localhost:8275/abc-default.properties 输入用户名密码，正常情况下会输出配置文件的内容；
 5. 在浏览器输入地址 http://localhost:8275/abc-default.yml 正常情况下会输出配置文件的内容；
 6. 在浏览器输入地址 http://localhost:8275/abc-hehui.yml 正常情况下会输出配置文件的内容；
 7. 在浏览器输入地址 http://localhost:8275/aaa-bbb.yml 正常情况下会输出配置文件的内容；
 8. 在浏览器输入地址 http://localhost:8275/aaa-bbb.properties 正常情况下会输出配置文件的内容；
 9. 在浏览器输入地址 http://localhost:8275/master/abc-default.properties 正常情况下会输出配置文件的内容；
 10. 在浏览器输入地址 http://localhost:8275/master/abc-default.yml 正常情况下会输出配置文件的内容；
 11. 在浏览器输入地址 http://localhost:8275/master/abc-hehui.yml 正常情况下会输出配置文件的内容；
 12. 在浏览器输入地址 http://localhost:8275/master/aaa-bbb.yml 正常情况下会输出配置文件的内容；
 13. 在浏览器输入地址 http://localhost:8275/master/aaa-bbb.properties 正常情况下会输出配置文件的内容；
 14. 在浏览器输入地址 http://localhost:8275/config-server-dev.yml 正常情况下会输出配置文件的内容；
     * 总结：按照配置服务的路径规则配置，基本上都可以访问得到结果数据。