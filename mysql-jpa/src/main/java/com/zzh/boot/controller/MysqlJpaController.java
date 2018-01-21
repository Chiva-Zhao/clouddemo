package com.zzh.boot.controller;

import com.zzh.boot.entity.User;
import com.zzh.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-21 10:58
 **/
@RestController
public class MysqlJpaController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/simple/{id}")
    public User findUserById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

    @GetMapping("/simple/list")
    public List<User> findUserList() {
        return this.userRepository.findAll();
    }

    /**
     * 添加一个student,使用postMapping接收post请求
     * <p>
     * http://localhost:8310/simple/addUser?username=user11&age=11&balance=11
     *
     * @return
     */
    @PostMapping("/simple/addUser")
    public User addUser(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "age", required = false) Integer age,
                        @RequestParam(value = "balance", required = false) String balance) {
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);
        return userRepository.save(user);
    }
}
