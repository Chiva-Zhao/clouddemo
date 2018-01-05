package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 注意 EnableZuulProxy 注解能注册到 eureka 服务上，是因为该注解包含了 eureka 客户端的注解，
 * 该 EnableZuulProxy 是一个复合注解。
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulApplication.class, args);
        System.out.println("【【【【【【 GatewayZuul微服务 】】】】】】已启动.");
    }
}
