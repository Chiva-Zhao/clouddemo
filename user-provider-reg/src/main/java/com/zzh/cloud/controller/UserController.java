package com.zzh.cloud.controller;

import com.google.common.collect.Lists;
import com.zzh.cloud.entity.User;
import com.zzh.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        System.out.println("@GetMapping(\"user\") 接收参数对象 user: " + user);
        return user;
    }

    @GetMapping("listAll")
    public List<User> listAll() {
        ArrayList<User> list = Lists.newArrayList();
        User user1 = new User(1L, "user1");
        User user2 = new User(1L, "user2");
        User user3 = new User(1L, "user3");
        User user4 = new User(1L, "user4");
        User user5 = new User(1L, "user5");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        return list;
    }

}
