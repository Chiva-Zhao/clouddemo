package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul 网关微服务的一些属性应用测试；
 * API网关模块发现应用入口（自定义路径配置添加 serviceId 属性，给User微服务添加前缀地址，反向代理所有服务器）。
 * 注意 EnableZuulProxy 注解能注册到 eureka 服务上，是因为该注解包含了 eureka 客户端的注解，
 * 该 EnableZuulProxy 是一个复合注解。
 * http://localhost:8155/routes 地址可以查看该zuul微服务网关代理了多少微服务的serviceId。
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulAttributeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulAttributeApplication.class, args);
        System.out.println("【【【【【【 GatewayZuulAttribute微服务 】】】】】】已启动.");
    }
}
