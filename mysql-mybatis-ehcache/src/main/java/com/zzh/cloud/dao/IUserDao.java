package com.zzh.cloud.dao;

import com.zzh.cloud.entity.User;

import java.util.List;

public interface IUserDao {

    User findUserById(Long id);

    List<User> findAllUsers();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);
}