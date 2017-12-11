package com.zzh.cloud.feign;

import com.zzh.cloud.entity.User;
import com.zzh.config.FeignCustomConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 17:04
 */
@FeignClient(name = "user-provider-reg", configuration = FeignCustomConfig.class)
public interface UserFeignCustomClient {
    @RequestLine("GET /user/{id}")
    public User findById(@Param("id") Long id);
}
