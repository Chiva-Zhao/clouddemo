package com.zzh.cloud;

import com.netflix.zuul.ZuulFilter;
import com.zzh.cloud.filter.PreZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulFilterApplication.class, args);
        System.out.println("【【【【【【 GatewayZuulFilter微服务 】】】】】】已启动.");
    }

    @Bean
    public PreZuulFilter zuulFilter() {
        return new PreZuulFilter();
    }
}
