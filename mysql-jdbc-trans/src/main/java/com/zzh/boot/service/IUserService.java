package com.zzh.boot.service;

import com.zzh.boot.entity.User;

import java.util.List;

public interface IUserService {

    User findUserById(Long id);

    List<User> findAllUsers();

    int insertUser(User user);

    int addUser(User user);

    int replaceUser(User user) throws Exception;
}