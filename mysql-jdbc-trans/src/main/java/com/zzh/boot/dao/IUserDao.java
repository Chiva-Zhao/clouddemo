package com.zzh.boot.dao;

import com.zzh.boot.entity.User;

import java.util.List;

public interface IUserDao {

    User findUserById(Long id);

    List<User> findAllUsers();

    int insertUser(User user);

    int addUser(User user);

    int replaceUser(User user);
}