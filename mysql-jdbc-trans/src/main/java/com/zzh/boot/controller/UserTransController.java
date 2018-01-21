package com.zzh.boot.controller;

import com.zzh.boot.entity.User;
import com.zzh.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-21 12:00
 **/
@RestController
public class UserTransController {
    @Autowired
    private IUserService userService;

    @GetMapping("/simplejdbc/{id}")
    public User findUserById(@PathVariable Long id) {
        return this.userService.findUserById(id);
    }

    @GetMapping("/simplejdbc/list")
    public List<User> findAllUsers() {
        List<User> users = this.userService.findAllUsers();
//        List<User> resultUsers = new ArrayList<>(users.subList(users.size() - 6, users.size()));
        return users;
    }

    @PostMapping("/simplejdbc/insertUser")
    public User insertUser(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "balance", required = false) String balance) {
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);
        int result = userService.insertUser(user);
        if (result > 0) {
            return user;
        }
        user.setId(0L);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }

    @PostMapping("/simplejdbc/addUser")
    public User addUser(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "balance", required = false) String balance) {
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);
        int result = userService.addUser(user);
        if (result > 0) {
            return user;
        }
        user.setId(0L);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }

    @PostMapping("/simplejdbc/replaceUser")
    public User replaceUser(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "balance", required = false) String balance) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);
        int result = 0;
        result = userService.replaceUser(user);
        user.setId((long) 0);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }
}
