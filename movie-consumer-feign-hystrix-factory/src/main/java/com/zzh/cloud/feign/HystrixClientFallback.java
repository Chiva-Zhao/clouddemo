package com.zzh.cloud.feign;

import com.zzh.cloud.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 15:14
 **/
@Component
public class HystrixClientFallback implements UserFeignHystrixFactoryClient {
    @Override
    public User findById(Long id) {
        System.out.println("======== findById Fallback "
                + Thread.currentThread().getThreadGroup()
                + " - " + Thread.currentThread().getId()
                + " - " + Thread.currentThread().getName());
        User tmpUser = new User();
        tmpUser.setId(0L);
        return tmpUser;
    }
}
