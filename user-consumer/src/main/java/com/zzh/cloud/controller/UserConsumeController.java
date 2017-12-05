package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserConsumeController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @GetMapping("/consume/{id}")
    public User findById(@PathVariable Long id) {
        User user = this.restTemplate.getForObject(userServiceUrl + id, User.class);
        System.out.println(user);
        return user;
    }
}
