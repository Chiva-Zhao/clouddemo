package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MovieConsumerFeignHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieConsumerFeignHystrixApplication.class, args);
        System.out.println("【【【【【【 MovieConsumerFeignHystrix 】】】】】】已启动.");

    }
}
