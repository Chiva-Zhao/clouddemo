package com.zzh.cloud.feign;

import com.zzh.config.EurekaAuthConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 17:04
 */
@FeignClient(name = "xxx", url = "http://localhost:8761/", configuration = EurekaAuthConfig.class)
public interface UserFeignCustomAuthClient {
    @RequestMapping("/eureka/apps/{serviceName}")
    public String findEurekaInfo(@PathVariable("serviceName") String serviceName);
}
