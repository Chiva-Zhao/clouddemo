package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 电影Ribbon微服务集成Hystrix增加隔离策略控制线程数或请求数来达到熔断降级的作用。
 * 传播安全上下文或使用，通过增加 HystrixCommand 的 commandProperties 属性，
 * 来增加相关的配置来达到执行隔离策略，控制线程数或者控制并发请求数来达到熔断降级的作用。
 * Hystrix 断路器实现失败快速响应，达到熔断效果；
 * 注解 EnableCircuitBreaker 表明需要集成断路器模块；
 * 如果你想把本地线程上下文传播到@HystrixCommand，默认的声明将不可用因为它是在一个线程池中被启动的。
 * 你可以选择让Hystrix使用同一个线程，通过一些配置，或直接写在注解上，通过使用isolation strategy属性；
 **/
@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
public class MovieConsumerRibbonHystrixPropApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieConsumerRibbonHystrixPropApplication.class, args);
    }
}
