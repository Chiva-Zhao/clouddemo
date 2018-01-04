package com.zzh.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-3 18:00
 * Hystrix 客户端回退机制类。
 **/
@Configuration
public class FeignConfig {
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    public Logger.Level feignLoglevel() {
        return Logger.Level.FULL;
    }
}
