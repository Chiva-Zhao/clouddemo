package com.zzh.config;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 13:25
 **/
@Configuration
public class EurekaAuthConfiguration {

    /**
     * 主要配置登录 Eureka 服务器的帐号与密码。
     *
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin");
    }

    /**
     * 在该配置中，加入这个方法的话，表明使用了该配置的地方，就会禁用该模块使用 Hystrix 容灾降级的功能；
     *
     * @return
     */
//    @Bean
////    @Scope
////    public Feign.Builder feignBuilder() {
////        return Feign.builder();
////    }
}
