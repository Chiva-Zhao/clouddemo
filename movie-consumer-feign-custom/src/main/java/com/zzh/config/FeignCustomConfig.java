package com.zzh.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 17:05
 */
@Configuration
public class FeignCustomConfig {
    @Bean
    public feign.Contract feignContract() {
        return new Contract.Default();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
