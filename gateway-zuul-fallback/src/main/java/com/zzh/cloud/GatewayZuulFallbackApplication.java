package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulFallbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulFallbackApplication.class, args);
        System.out.println("【【【【【【 GatewayZuulFallback微服务 】】】】】】已启动.");
    }
}
