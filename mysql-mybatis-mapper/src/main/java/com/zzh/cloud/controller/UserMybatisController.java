package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-21 18:18
 **/
@RestController
public class UserMybatisController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/simple/{id}")
    public User findUserById(@PathVariable Long id) {
        return this.iUserService.findUserById(id);
    }

    @GetMapping("/simple/list")
    public List<User> findUserList() {
        return this.iUserService.findAllUsers();
    }

    @PostMapping("/simple/addUser")
    public User addUser(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "balance", required = false) String balance) {
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);
        int result = iUserService.insertUser(user);
        if (result > 0) {
            return user;
        }
        user.setId(0L);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }
}
