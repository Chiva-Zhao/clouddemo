package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class MovieCosumerFeignHystrixFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCosumerFeignHystrixFactoryApplication.class, args);
        System.out.println("【【【【【【 电影Feign-HystrixFactory微服务 】】】】】】已启动.");
    }
}
