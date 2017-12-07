package com.zzh.cloud;

import com.zzh.cloud.config.TestConfigInside2ScanPackage;
import com.zzh.cloud.config.TestConfigInsideScanPackage;
import com.zzh.cloud.config.TestConfigOutsideScanPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * 电影微服务，使用定制化 Ribbon 在客户端进行负载均衡，使用 RibbonClient 不同服务不同配置策略
 * LoadBalanced：该负载均衡注解，已经整合了 Ribbon；
 * Ribbon 的默认负载均衡的算法为：轮询；
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = ExcludeFromComponentScan.class)})
//@RibbonClient(name = "user-provider-reg", configuration = TestConfigOutsideScanPackage.class)
@RibbonClient(name = "user-provider-reg", configuration = TestConfigInsideScanPackage.class)
//@RibbonClient(name = "user-provider-reg", configuration = TestConfigInside2ScanPackage.class)
public class MovieCumtomApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCumtomApplication.class, args);
    }
}
