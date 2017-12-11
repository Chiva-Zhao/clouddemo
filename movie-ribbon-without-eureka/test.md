### 一. 电影Ribbon微服务，脱离Eureka使用配置listOfServers进行客户端负载均衡调度（正常使用服务测试）：
 1. application.yml 配置：ribbon.eureka.enabled: false
 2. application.yml 配置：user-provider-reg.ribbon.listOfServers: localhost:7900
 3. 启动user-provider-reg模块服务，启动3个端口（7900. 7899. 7898）；
 4. 启动user-provider-reg模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为user-provider-reg再启动而已）；
 5. 启动 springms-consumer-movie-ribbon-properties-without-eureka 模块服务；
 6. 在浏览器输入地址 http://localhost:8040/movie/1，连续刷新9次，然后看看 user-provider-reg,user-provider-reg2的这几个端口的服务打印日志情况，正常情况下只会有7999该端口才会有9条用户信息日志打印出来；
 * 总结：之所以只有用户微服务7999端口打印日志，首先禁用了eureka的使用，如果没禁用的话，3个端口按道理都会打印日志；其次配置 listOfServers 仅仅只选择了7999一个端口的微服务；
### 二. 电影Ribbon微服务，脱离Eureka使用配置listOfServers进行客户端负载均衡调度（负载均衡调度）：
  1. application.yml 配置：ribbon.eureka.enabled: false
  2. application.yml 配置：user-provider-reg.ribbon.listOfServers: localhost:7898,localhost:7899,localhost:7900
  3. 启动user-provider-reg模块服务，启动3个端口（7900. 7899. 7898）；
  4. 启动user-provider-reg模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为user-provider-reg2再启动而已）；
  5. 启动 springms-consumer-movie-ribbon-properties-without-eureka 模块服务；
  6. 在浏览器输入地址 http://localhost:8040/movie/1，连续刷新9次，然后看看 user-provider-reg,user-provider-reg2的这几个端口的服务打印日志情况，正常情况下user-provider-reg的3个端口都会打印日志，而且是轮询打印；
  * 总结：之所以7900. 7899. 7898三个端口轮询打印，是因为没有配置任何调度算法，默认的调度算法是轮询，所以3个端口当然会轮询打印用户信息；