package com.zzh.cloud.feign;

import com.zzh.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 15:03
 **/
@Component
public class HystrixClientFallbackFactory implements FallbackFactory<UserFeignHystrixFactoryClient> {
    private static final Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

    @Override
    public UserFeignHystrixFactoryClient create(Throwable e) {
        logger.info("fallback; reason was: {}", e.getMessage());
        System.out.println("======== UserFeignHystrixFactoryClient.create "
                + Thread.currentThread().getThreadGroup() + " - "
                + Thread.currentThread().getId() + " - "
                + Thread.currentThread().getName());
        return new UserFeignWithFallBackFactoryClient() {
            @Override
            public User findById(Long id) {
                System.out.println("======== findById FallBackFactory "
                        + Thread.currentThread().getThreadGroup() + " - "
                        + Thread.currentThread().getId() + " - "
                        + Thread.currentThread().getName());
                User tmpUser = new User();
                tmpUser.setId(-1L);
                return tmpUser;
            }
        };
    }
}
