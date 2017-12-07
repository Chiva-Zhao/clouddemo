 ### 一. RoundRobinRule
 1. application.yml配置轮询算法：user-provider-regribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RoundRobinRule；
 2. 启动 provider-user-reg 模块服务，启动3个端口（7900. 7899. 7898）；
 3. 启动 provider-user-reg2 模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为 provider-user-reg2 再启动而已）；
 4. 启动 springms-consumer-movie-ribbon-properties 模块服务，启动1个端口；
 5. 在浏览器输入地址http://localhost:8030/choose，然后看看 user-provider-reg provider-user-reg2 的各个对应的端口的服务打印的信息是否均匀，正常情况下应该是轮询分配打印的；
 * 总结：provider-user-reg（之所以轮询是因为配置文件采用 RoundRobinRule 轮询调度算法）. provider-user-reg2（之所以轮询是因为没有任何配置，默认调度算法就是轮询算法）
 
 ### 二. RandomRule
  1. application.yml配置随机算法：user-provider-regribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule；
  2. 启动 provider-user-reg 模块服务，启动3个端口（7900. 7899. 7898）；
  3. 启动 provider-user-reg2 模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为 provider-user-reg2 再启动而已）；
  4. 启动 springms-consumer-movie-ribbon-properties 模块服务，启动1个端口；
  5. 在浏览器输入地址http://localhost:8030/choose，然后看看 provider-user-reg. provider-user-reg2 的各个对应的端口的服务打印的信息是否均匀，正常情况下应该是轮询分配打印的；
  * 总结：provider-user-reg（之所以随机是因为配置文件采用 RandomRule 随机调度算法）. provider-user-reg2（之所以轮询是因为没有任何配置，默认调度算法就是轮询算法）
  