package com.zzh.cloud.service.impl;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.mapper.IUserMapper;
import com.zzh.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserMapper iUserMapper;

    @Override
    public User findUserById(Long id) {
        return iUserMapper.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return iUserMapper.findAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return iUserMapper.insertUser(user);
    }
}