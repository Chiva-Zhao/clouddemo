package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 注解 LoadBalanced 会进行负载均衡将请求分配到不同的【用户微服务】上
 * Ribbon的负载均衡，主要通过LoadBalancerClient来实现的，而LoadBalancerClient具体交给了ILoadBalancer来处理，
 * ILoadBalancer通过配置IRule、IPing等信息，并向EurekaClient获取注册列表的信息，并默认10秒一次向EurekaClient发送“ping”,
 * 进而检查是否更新服务列表，最后，得到注册列表后，ILoadBalancer根据IRule的策略进行负载均衡。
 * RestTemplate 被@LoadBalance注解后，能过用负载均衡，主要是维护了一个被@LoadBalance注解的RestTemplate列表，
 * 并给列表中的RestTemplate添加拦截器，进而交给负载均衡器去处理。
 */
@SpringBootApplication
@EnableEurekaClient
public class MovieConsumerRibbonApplication {

    /**
     * RestTemplate 被@LoadBalance注解后，主要是维护了一个被@LoadBalance注解的RestTemplate列表，
     * 并给列表中的RestTemplate添加拦截器，进而交给负载均衡器去处理。
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieConsumerRibbonApplication.class, args);
    }
}
