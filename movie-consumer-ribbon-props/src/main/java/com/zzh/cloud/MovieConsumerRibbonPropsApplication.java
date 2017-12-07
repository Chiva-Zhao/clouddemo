package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * *Ribbon 的默认负载均衡的算法为：轮询；
 * 配置文件优先级最高，Java代码设置的配置其次，默认的配置优先级最低；
 */
@SpringBootApplication
@EnableEurekaClient
public class MovieConsumerRibbonPropsApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieConsumerRibbonPropsApplication.class, args);
    }
}
