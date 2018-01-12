package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulUploadApplication.class, args);
        System.out.println("【【【【【【 GatewayZuulFileUpload微服务 】】】】】】已启动.");
    }
}
