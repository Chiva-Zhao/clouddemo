package com.zzh.boot.controller;

import com.zzh.boot.entity.Account;
import com.zzh.boot.entity.User;
import com.zzh.boot.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-21 15:32
 **/
@RestController
public class MovieTranController {
    @Autowired
    private IMovieService moiveService;

    @PostMapping("/movie/addMovie")
    public User addMovie(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "balance", required = false) String balance) {
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);

        Account account = new Account();
        account.setName(username);
        account.setMoney(Double.parseDouble(balance));

        int result = moiveService.addMovie(user, account);
        if (result > 0) {
            return user;
        }

        user.setId(0L);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }

    @PostMapping("/movie/insertMovie")
    public User insertMovie(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "balance", required = false) String balance) throws Exception {
        User user = new User();

        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);

        Account account = new Account();
        account.setName(username);
        account.setMoney(Double.parseDouble(balance));

        int result = moiveService.insertMovie(user, account);
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
