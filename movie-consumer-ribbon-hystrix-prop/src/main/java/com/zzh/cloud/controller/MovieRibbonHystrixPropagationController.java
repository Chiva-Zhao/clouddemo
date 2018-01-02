package com.zzh.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zzh.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-2 13:58
 **/
@RestController
public class MovieRibbonHystrixPropagationController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("movie/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback",
            commandProperties = @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"))
    public User findById(@PathVariable Long id) {
        System.out.println("======================== findById "
                + Thread.currentThread().getThreadGroup()
                + " - " + Thread.currentThread().getId()
                + " - " + Thread.currentThread().getName());
        return restTemplate.getForObject("http://user-provider-reg/user/" + id, User.class);
    }

    public User findByIdFallback(Long id) {
        System.out.println("======================== findByIdFallback "
                + Thread.currentThread().getThreadGroup() + " - "
                + Thread.currentThread().getId() + " - "
                + Thread.currentThread().getName());
        User user = new User();
        user.setId(0L);
        return user;
    }
}
