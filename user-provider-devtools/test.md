### 一. 简单用户微服务接口测试：
 1. 添加 spring-boot-devtools 热部署依赖；
 2. pom.xml 添加 `<fork>true</fork>` 属性；
 3. 设置 idea 属性：“File” -> “Settings” -> “Build,Execution,Deplyment” -> “Compiler”，选中打勾 “Build project automatically” ；
 4. 设置 idea 属性：组合键：“Shift+Ctrl+Alt+/” ，选择 “Registry” ，选中打勾 “compiler.automake.allow.when.app.running” ；
 5. 设置缓存配置 spring.thymeleaf.cache=false；
 6. 设置 chrome 属性：F12或者“Ctrl+Shift+I”，打开开发者工具，“Network” 选项卡下 选中打勾 “Disable Cache(while DevTools is open)” ；
 7. 启动 user-provider-devtools 模块服务，启动1个端口；
 8. 在浏览器输入地址 http://localhost:8305/simple 可以看到信息成功的被打印出来；
 9. 改动 Controller 某个方法的返回值，或者新增方法，然后会看到控制台自动重启该应用，进行热部署；
 10. 再在浏览器请求刚刚改动的链接，发现链接返回的内容确实动态改变过来了，这就是热部署；