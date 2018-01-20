package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-21 1:01
 **/
@RestController
public class UserDevtoolsController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/simple/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

    @GetMapping("simple")
    public String simple() {
        return "simple-2018-01";
    }

    @GetMapping("simple2")
    public String simple2() {
        return "simple2-2019";
    }
}
