package com.zzh.cloud.dao.impl;

import com.zzh.cloud.dao.IUserDao;
import com.zzh.cloud.entity.User;
import com.zzh.cloud.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private IUserMapper iUserMapper;

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

    @Override
    public int updateUser(User user) {
        return iUserMapper.updateUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        return iUserMapper.deleteUser(id);
    }
}