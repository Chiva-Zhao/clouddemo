package com.zzh.cloud.feign;

import com.zzh.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 15:02
 **/
@FeignClient(name = "user-provider-reg"/*, fallback = HystrixClientFallback.class*/
        , fallbackFactory = HystrixClientFallbackFactory.class)
public interface UserFeignHystrixFactoryClient {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
