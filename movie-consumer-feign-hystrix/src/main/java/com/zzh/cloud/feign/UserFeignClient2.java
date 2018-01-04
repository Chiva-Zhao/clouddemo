package com.zzh.cloud.feign;

import com.zzh.config.EurekaAuthConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 11:30
 **/
@FeignClient(name = "xxx", url = "http://localhost:8761", configuration = EurekaAuthConfiguration.class, fallback = FeignClientFallback2.class)
public interface UserFeignClient2 {
    @RequestMapping(value = "/eureka/apps/{serviceName}")
    public String findEurekaInfo(@PathVariable("serviceName") String serviceName);
}