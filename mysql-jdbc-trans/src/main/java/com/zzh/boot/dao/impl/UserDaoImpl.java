package com.zzh.boot.dao.impl;

import com.zzh.boot.dao.IUserDao;
import com.zzh.boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUserById(Long id) {
        // 1. 定义一个sql语句
        String querySQL = "select * from user where id = ?";
        // 2. 定义一个RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        // 3. 执行查询方法
        //List<User> list = jdbcTemplate.query("select * from account where id = ?", new Object[]{id}, new BeanPropertyRowMapper(User.class));
        User user = jdbcTemplate.queryForObject(querySQL, new Object[]{id}, rowMapper);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        // 1. 定义一个sql语句
        String querySQL = "select * from user";
        // 2. 定义一个RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        // 3. 执行查询方法
        List<User> users = jdbcTemplate.query(querySQL, new Object[]{}, rowMapper);
        return users;
    }

    @Override
    public int insertUser(User user) {
        // 1. 定义一个sql语句
        String execSQL = "INSERT into user (username, name, age, balance) values (?, ?, ?, ?)";
        // 2. 执行查询方法
        return jdbcTemplate.update(execSQL,
                new Object[]{user.getUsername(), user.getName(), user.getAge(), user.getBalance()});
    }

    @Override
    public int addUser(User user) {
        // 1. 定义一个sql语句
        String execSQL = "INSERT into user (username, name, age, balance) values (?, ?, ?, ?)";
        // 2. 执行查询方法
        int result = jdbcTemplate.update(execSQL,
                new Object[]{user.getUsername(), user.getName(), user.getAge(), user.getBalance()});
        return result;
    }

    @Override
    public int replaceUser(User user) {
        // 1. 定义一个sql语句
        String execSQL = "INSERT into user (username, name, age, balance) values (?, ?, ?, ?)";
        // 2. 执行查询方法
        int result = jdbcTemplate.update(execSQL,
                new Object[]{user.getUsername(), user.getName(), user.getAge(), user.getBalance()});
        return result;
    }
}