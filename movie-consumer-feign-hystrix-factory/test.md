### 一. 电影微服务接入Feign，添加 fallbackFactory 属性来触发请求进行容灾降级（测试正常接入功能）：
 1. 注解：EnableFeignClients；
 2. 编写类 HystrixClientFallbackFactory 回退处理机制类，并给该类加上注解 Component ；加入 FeignClient 注解
 	// @FeignClient(name = "user-provider-reg", fallback = HystrixClientFallback.class  )
 3. 启动 discovery-eureka 模块服务，启动1个端口；
 4. 启动 user-provider-reg 模块服务，启动1个端口；
 5. 启动 movie-consumer-feign-hystrix-factory 模块服务；
 6. 在浏览器输入地址 http://localhost:8115/movie/1 可以看到具体的用户信息（即用户ID != 0 的用户）成功的被打印出来；
 
### 二. 电影HystrixFactory微服务接入 HystrixFactory 功能模块（测试断路器功能）：
  1. 注解：EnableFeignClients；
  2. 编写类 HystrixClientFallbackFactory 回退处理机制类，并给该类加上注解 Component，UserFeignHystrixFactoryClient 加上 fallbackFactory 属性；
  	// @FeignClient(name = "user-provider-reg", fallback = HystrixClientFallback.class, fallbackFactory = HystrixClientFallbackFactory.class )
  3. 启动 discovery-eureka 模块服务，启动1个端口；
  4. 启动 user-provider-reg 模块服务，启动1个端口；
  5. 启动 movie-consumer-feign-hystrix-factory 模块服务；
  6. 在浏览器输入地址 http://localhost:8115/movie/1 可以看到具体的用户信息（即用户ID != 0 的用户）成功的被打印出来；
  7. 停止 user-provider-reg 模块服务；
  8. 在浏览器输入地址http://localhost:8115/movie/1 可以看到用户信息ID = 0 的用户成功的被打印出来，但随着问题也来了；
  9. HystrixClientFallbackFactory 截获的异常却没有被打印出来，本来用户微服务停止的话，请求链接就已经链接超时了，但是为啥异常没有打印出来呢？请看下面第三中测试方法。
  
### 三. 电影FeignHystrix-HystrixFactory微服务接入 HystrixFactory 功能模块（测试断路器功能）：
 1. 注解：EnableFeignClients；
 2. 编写类 HystrixClientFallbackFactory 回退处理机制类，并给该类加上注解 Component，UserFeignHystrixFactoryClient 去掉 fallback 属性，然后加上 fallbackfactory 属性；
 	// @FeignClient(name = "user-provider-reg", fallbackFactory = HystrixClientFallbackFactory.class )
 3. 启动 discovery-eureka 模块服务，启动1个端口；
 4. 启动 user-provider-reg 模块服务，启动1个端口；
 5. 启动 movie-consumer-feign-hystrix-factory 模块服务；
 6. 在浏览器输入地址 http://localhost:8115/movie/1 可以看到具体的用户信息（即用户ID != 0 的用户）成功的被打印出来；
 7. 停止 user-provider-reg 模块服务；
 8. 在浏览器输入地址http://localhost:8115/movie/1 可以看到用户信息ID = -1 的用户成功的被打印出来，而且异常信息日志也被打印出来了，这就正常了；
 * 注意：第2步骤：UserFeignHystrixFactoryClient 去掉 fallback 属性，然后加上 fallbackfactory 属性；
    fallback 和 fallbackfactory 属性会有冲突，所以只要其一就行了；