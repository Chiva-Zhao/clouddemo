package com.zzh.cloud.service.impl;

import com.zzh.cloud.dao.IUserDao;
import com.zzh.cloud.entity.User;
import com.zzh.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <li>注意：CACHE_KEY、CACHE_NAME_B 的单引号不能少，否则会报错，被识别是一个对象。</li>
 * <li>value 指的是 ehcache.xml 中的缓存策略空间；key 指的是缓存的标识</li>
 **/
@Service
public class UserServiceImpl implements IUserService {
    private static final String CACHE_KEY = "'user'";
    private static final String CACHE_NAME_B = "cache-b";

    //* @Cacheable : Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
    //* @CacheEvict : 清除缓存。
    //* @CachePut : @CachePut也可以声明一个方法支持缓存功能。使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。

    @Autowired
    IUserDao iUserDao;

    /**
     * 查找用户数据
     *
     * @param id
     * @return
     */
    @Cacheable(value = CACHE_NAME_B, key = "'user_'+#id")
    @Override
    public User findUserById(Long id) {
        return iUserDao.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return iUserDao.findAllUsers();
    }

    /**
     * 保存用户数据
     *
     * @param user
     * @return
     */
    @CacheEvict(value = CACHE_NAME_B, key = CACHE_KEY)
    @Override
    public int insertUser(User user) {
        return iUserDao.insertUser(user);
    }

    /**
     * 更新用户数据
     *
     * @param user
     * @return
     */
    @CachePut(value = CACHE_NAME_B, key = "'user_'+#user.id")
    @Override
    public int updateUser(User user) {
        return iUserDao.updateUser(user);
    }

    /**
     * 删除用户数据
     *
     * @param id
     * @return
     */
    @CacheEvict(value = CACHE_NAME_B, key = "'user_' + #id") //这是清除缓存
    @Override
    public int deleteUser(Long id) {
        return iUserDao.deleteUser(id);
    }
}