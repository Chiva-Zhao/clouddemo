package com.zzh.cloud.mapper;

import com.zzh.cloud.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserMapper {

    @Select("select * from user where id = #{id}")
    User findUserById(Long id);

    @Select("select * from user")
    List<User> findAllUsers();

    @Insert("INSERT INTO user(username, name, age, balance) VALUES(#{username}, #{name}, #{age}, #{balance})")
    int insertUser(User user);
}