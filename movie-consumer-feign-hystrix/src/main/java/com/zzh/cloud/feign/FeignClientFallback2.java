package com.zzh.cloud.feign;

import org.springframework.stereotype.Component;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 11:30
 **/
@Component
public class FeignClientFallback2 implements UserFeignClient2 {
    @Override
    public String findEurekaInfo(String serviceName) {
        System.out.println("========== findEurekaInfo Fallback "
                + Thread.currentThread().getThreadGroup()
                + " - " + Thread.currentThread().getId()
                + " - " + Thread.currentThread().getName());
        return "user-provider-reg";
    }
}
