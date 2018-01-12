package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulRegexpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulRegexpApplication.class, args);
        System.out.println("【【【【【【 GatewayZuulRegExp微服务 】】】】】】已启动.");
    }

    /**
     * 使用regexmapper提供serviceId和routes之间的绑定. 它使用正则表达式组来从serviceId提取变量,
     * 然后注入到路由表达式中。
     * <p>
     * 这个意思是说"user-provider-reg-version"将会匹配路由"/version/user-provider-reg/**". 任何正则表达式都可以, 但是所有组必须存在于servicePattern和routePattern之中.
     *
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
    }
}
