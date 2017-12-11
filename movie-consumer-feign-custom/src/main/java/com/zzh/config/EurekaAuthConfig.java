package com.zzh.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 17:14
 * 认证配置，由于 UserFeignCustomSecondClient 访问 http://localhost:8761/ 需要密码登录，所以才有了此配置的出现。
 */
@Configuration
public class EurekaAuthConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin");
    }
}
