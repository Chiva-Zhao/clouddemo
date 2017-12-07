package com.zzh.cloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/7 11:02
 */
@Configuration
public class TestConfigOutsideScanPackage {

    @Bean
    public IRule ribbonRule() {
        return new RoundRobinRule();
    }
}
