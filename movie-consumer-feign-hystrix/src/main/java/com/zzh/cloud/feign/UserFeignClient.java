package com.zzh.cloud.feign;

import com.zzh.cloud.entity.User;
import com.zzh.config.FeignConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-3 17:57
 **/
@FeignClient(name = "user-provider-reg", configuration = FeignConfig.class, fallback = FeignClientFallback.class)
public interface UserFeignClient {
    /**
     * 这里的注解 RequestLine、Param 是 Feign 的配置新的注解，
     * 参考链接：https://github.com/OpenFeign/feign
     *
     * @param id
     * @return
     */
    @RequestLine("GET /user/{id}")
    public User findById(@Param("id") Long id);
}
