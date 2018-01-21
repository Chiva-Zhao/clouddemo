package com.zzh.boot.service.impl;

import com.zzh.boot.dao.IUserDao;
import com.zzh.boot.entity.User;
import com.zzh.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}