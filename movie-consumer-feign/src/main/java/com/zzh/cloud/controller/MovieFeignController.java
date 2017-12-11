package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 16:33
 */
@RestController
public class MovieFeignController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/movie/user")
    public User postUser(User user) {
        Random random = new Random();
        User tmpUser = new User();
        long id = (long) random.nextInt(100);
        tmpUser.setId(id);
        tmpUser.setName("TempUser" + id);
        tmpUser.setAge((short) id);
        return userFeignClient.postUser(tmpUser);
    }
}
