电影微服务，使用定制化 Ribbon 在客户端进行负载均衡，使用 RibbonClient 不同服务不同配置策略
### 一. TestConfigurationOutsideScanPackage && RoundRobinRule
 1. 使用注解：@SpringBootApplication. @EnableEurekaClient；
 2. 使用注解：@RibbonClient(name = "provider-user-reg", configuration = TestConfigurationOutsideScanPackage.class)
 3. TestConfigurationInsideScanPackage 类中采用 RoundRobinRule 轮询调度算法；
 4. 启动 provider-user-reg 模块服务，启动3个端口（7900. 7899. 7898）；
 5. 启动 movie-consumer-ribbon-cumtom 模块服务，启动1个端口；
 6. 在浏览器输入地址http://localhost:8020/movie/2，然后看看 provider-user-reg 的三个端口的服务打印的信息是否均匀，正常情况下应该是轮询打印；
 * 总结：客户端之所以会轮询调用各个微服务，是因为在 TestConfigurationOutsideScanPackage 类中配置了负载均衡调度算法：轮询 RoundRobinRule 策略算法；````
 
 ### 二. TestConfigurationInsideScanPackage && RoundRobinRule
  1. 使用注解：@SpringBootApplication. @EnableEurekaClient；
  2. 使用注解：@RibbonClient(name = "provider-user-reg", configuration = TestConfigurationInsideScanPackage.class)
  3. 使用注解：@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
  4. TestConfigurationInsideScanPackage 类中采用 RoundRobinRule 轮询调度算法；
  5. 启动 provider-user-reg 模块服务，启动3个端口（7900. 7899. 7898）；
  6. 启动 movie-consumer-ribbon-cumtom 模块服务，启动1个端口；
  7. 在浏览器输入地址http://localhost:8020/movie/2，然后看看 provider-user-reg 的三个端口的服务打印的信息是否均匀，正常情况下应该是轮询打印；
 
  * 总结一：客户端之所以会轮询分配调用各个微服务，是因为在注解方面采用了注解 ComponentScan 使配置文件 TestConfigurationOutsideScanPackage 不被扫描到，然后再结合 TestConfigurationOutsideScanPackage 类中配置了负载均衡调度算法：轮询 RoundRobinRule 策略算法；
  * 总结二：可以发现规律，当使用 “restTemplate.getForObject("http://provider-user-reg/simple/" + id, User.class)” 这种方式负载均衡调用各个微服务跟配置文件 TestConfigurationOutsideScanPackage 在哪里没有关系；
  
### 三. TestConfigurationOutsideScanPackage && RandomRule
  
   1. 使用注解：@SpringBootApplication. @EnableEurekaClient；
   2. 使用注解：@RibbonClient(name = "user-provider-reg", configuration = TestConfigurationOutsideScanPackage.class)
   3. TestConfigurationInsideScanPackage 类中采用 RandomRule 随机调度算法；
   4. 启动 user-provider-reg 模块服务，启动3个端口（7900. 7899. 7898）；
   5. 启动 movie-consumer-ribbon-cumtom 模块服务，启动1个端口；
   6. 在浏览器输入地址http://localhost:8020/movie/2，然后看看 user-provider-reg 的三个端口的服务打印的信息是否均匀，正常情况下应该是随机打印；
   * 总结：客户端之所以会随机调用各个微服务，是因为在 TestConfigurationOutsideScanPackage 类中配置了负载均衡调度算法：随机 RandomRule 策略算法；
   
### 四. TestConfigurationInsideScanPackage && RandomRule   
1. 使用注解：@SpringBootApplication. @EnableEurekaClient；
2. 使用注解：@RibbonClient(name = "user-provider-reg", configuration = TestConfigurationInsideScanPackage.class)
3. 使用注解：@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
4. TestConfigurationInsideScanPackage 类中采用 RandomRule 随机调度算法；
5. 启动 user-provider-reg 模块服务，启动3个端口（7900. 7899. 7898）；
6. 启动 movie-consumer-ribbon-cumtom 模块服务，启动1个端口；
7. 在浏览器输入地址http://localhost:8020/movie/2，然后看看 user-provider-reg 的三个端口的服务打印的信息是否均匀，正常情况下应该是随机打印；

* 总结一：客户端之所以会轮询分配调用各个微服务，是因为在注解方面采用了注解 ComponentScan 使配置文件 TestConfigurationOutsideScanPackage 不被扫描到，然后再结合 TestConfigurationOutsideScanPackage 类中配置了负载均衡调度算法：随机 RandomRule 策略算法；
* 总结二：可以发现规律，当使用 “restTemplate.getForObject("http://user-provider-reg/simple/" + id, User.class)” 这种方式负载均衡调用各个微服务跟配置文件 TestConfigurationOutsideScanPackage 在哪里没有关系；
* 总结三：由（测试一. 测试二）和（测试三. 测试四）对比可知，当使用 “restTemplate.getForObject("http://user-provider-reg/simple/" + id, User.class)” 这种方式负载均衡调用各个微服务不需要考虑配置文件的放在哪个包下面。

### 五. TestConfigurationInside2ScanPackage && RoundRobinRule
 1. 使用注解：@SpringBootApplication. @EnableEurekaClient；
 2. 使用注解：@RibbonClient(name = "user-provider-reg", configuration = TestConfigurationInside2ScanPackage.class)
 3. 使用注解：@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
 3. TestConfigurationInside2ScanPackage 类中采用 RoundRobinRule 轮询调度算法；
 4. 在 MovieCustomRibbonController 里面添加 test 方法来做测试；
 5. 启动 user-provider-reg 模块服务，启动3个端口（7900. 7899. 7898）；
 6. 启动 user-provider-reg2 模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为 user-provider-reg2 再启动而已）；
 7. 启动 movie-consumer-ribbon-cumtom 模块服务；
 8. 在浏览器输入地址http://localhost:8020/choose，然后看看 user-provider-reg. user-provider-reg2 的各个对应的端口的服务打印的信息是否均匀，正常情况下应该是轮询分配打印的；
 * 总结：user-provider-reg（之所以轮询是因为使用了 RibbonClient 配置采用 RoundRobinRule 轮询调度算法）. user-provider-reg2（之所以轮询是因为没有任何配置，默认调度算法就是轮询算法）；

### 六. TestConfigurationInside2ScanPackage && RandomRule
 1. 使用注解：@SpringBootApplication. @EnableEurekaClient；
 2. 使用注解：@RibbonClient(name = "user-provider-reg", configuration = TestConfigurationInside2ScanPackage.class)
 3. 使用注解：@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
 3. TestConfigurationInside2ScanPackage 类中采用 RandomRule 随机调度算法；
 4. 在 MovieCustomRibbonController 里面添加 test 方法来做测试；
 5. 启动 user-provider-reg 模块服务，启动3个端口（7900. 7899. 7898）；
 6. 启动 user-provider-reg2 模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为 user-provider-reg2 再启动而已）；
 7. 启动 movie-consumer-ribbon-cumtom 模块服务；
 8. 在浏览器输入地址http://localhost:8020/choose，然后看看 user-provider-reg. user-provider-reg2 的各个对应的服务打印的信息是否均匀，正常情况下应该是 user-provider-reg 随机分配，user-provider-reg2 轮询分配；
 * 总结：user-provider-reg（之所以随机是因为使用了 RibbonClient 配置采用 RandomRule 随机调度算法）. user-provider-reg2（之所以轮询是因为没有任何配置，默认调度算法就是轮询算法）；
 
 ### 七. TestConfigurationInsideScanPackage && RoundRobinRule
  1. 注解：@RibbonClient(name = "user-provider-reg", configuration = TestConfigurationInsideScanPackage.class)
  2. 注解：@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
  3. TestConfigurationInsideScanPackage 类中采用 RoundRobinRule 轮询调度算法；
  4. 在 MovieCustomRibbonController 里面添加 test 方法来做测试；
  5. 启动 user-provider-reg 模块服务，启动3个端口（7900. 7899. 7898）；
  6. 启动 user-provider-reg2 模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为 user-provider-reg2 再启动而已）；
  7. 启动 movie-consumer-ribbon-cumtom 模块服务；
  8. 在浏览器输入地址http://localhost:8020/choose，然后看看 user-provider-reg. user-provider-reg2 的两个端口的服务打印的信息是否均匀，正常情况下都是轮询打印；
  * 总结：user-provider-reg（之所以随机是因为使用了 RibbonClient 配置采用 RoundRobinRule 轮询调度算法）. user-provider-reg2（之所以轮询是因为没有任何配置，默认调度算法就是轮询算法）；
  
### 八. TestConfigurationInsideScanPackage && RandomRule
 1. 注解：@RibbonClient(name = "user-provider-reg", configuration = TestConfigurationInsideScanPackage.class)
 2. 注解：@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
 3. TestConfigurationInsideScanPackage 类中采用 RandomRule 随机调度算法；
 4. 在 MovieCustomRibbonController 里面添加 test 方法来做测试；
 5. 启动 user-provider-reg 模块服务，启动3个端口（7900. 7899. 7898）；
 6. 启动 user-provider-reg2 模块服务，启动2个端口（7997. 7996）（直接将用户微服务 spring.application.name 改了个名字为 user-provider-reg2 再启动而已）；
 7. 启动 movie-consumer-ribbon-cumtom 模块服务；
 8. 在浏览器输入地址http://localhost:8020/choose，然后看看 user-provider-reg. user-provider-reg2 的两个端口的服务打印的信息是否均匀，正常情况下应该是 user-provider-reg 随机分配，user-provider-reg2 轮询分配；
 * 总结：user-provider-reg（之所以随机是因为使用了 RibbonClient 配置采用 RandomRule 随机调度算法）. user-provider-reg2（之所以轮询是因为没有任何配置，默认调度算法就是轮询算法）；  