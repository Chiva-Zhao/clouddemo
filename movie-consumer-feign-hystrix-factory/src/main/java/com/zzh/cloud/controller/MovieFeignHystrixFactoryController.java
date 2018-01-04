package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.feign.UserFeignHystrixFactoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-4 15:29
 **/
@RestController
public class MovieFeignHystrixFactoryController {
    @Autowired
    private UserFeignHystrixFactoryClient client;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        System.out.println("======== findById Controller "
                + Thread.currentThread().getThreadGroup()
                + " - " + Thread.currentThread().getId()
                + " - " + Thread.currentThread().getName());
        return client.findById(id);
    }
}
