package com.zzh.cloud.service;

import com.zzh.cloud.entity.User;

import java.util.List;

public interface IUserService {

    User findUserById(Long id);

    List<User> findAllUsers();

    int insertUser(User user);
}