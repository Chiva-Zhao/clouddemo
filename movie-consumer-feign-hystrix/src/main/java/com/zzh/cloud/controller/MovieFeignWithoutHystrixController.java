package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.feign.UserFeignClient;
import com.zzh.cloud.feign.UserFeignClient2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 13:28
 **/
@RestController
public class MovieFeignWithoutHystrixController {
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private UserFeignClient2 userFeignClient2;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        System.out.println("======== findById Controller " + Thread.currentThread().getThreadGroup() + " - " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
        return userFeignClient.findById(id);
    }

    @GetMapping("/{serviceName}")
    public String findEurekaInfo(@PathVariable String serviceName) {
        System.out.println("======== findEurekaInfo Controller " + Thread.currentThread().getThreadGroup() + " - " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
        return userFeignClient2.findEurekaInfo(serviceName);
    }
}
