package com.zzh.cloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.zzh.cloud.ExcludeFromComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/7 11:02
 */
@Configuration
@ExcludeFromComponentScan
public class TestConfigInside2ScanPackage {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
