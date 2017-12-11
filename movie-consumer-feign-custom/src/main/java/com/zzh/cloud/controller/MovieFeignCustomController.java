package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.feign.UserFeignCustomAuthClient;
import com.zzh.cloud.feign.UserFeignCustomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 17:18
 */
@RestController
public class MovieFeignCustomController {
    @Autowired
    private UserFeignCustomClient userFeignCustomClient;

    @Autowired
    private UserFeignCustomAuthClient userFeignCustomAuthClient;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignCustomClient.findById(id);
    }

    @GetMapping("/{serviceName}")
    public String findEurekaInfo(@PathVariable String serviceName){
        return userFeignCustomAuthClient.findEurekaInfo(serviceName);
    }
}
