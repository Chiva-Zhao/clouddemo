package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import com.zzh.cloud.service.IUserService;
import net.sf.ehcache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-21 21:38
 **/
@RestController
public class UserEhcacheController {
    private static final Logger logger = LoggerFactory.getLogger(UserEhcacheController.class);

    @Autowired
    private IUserService iUserService;

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id) {
        return this.iUserService.findUserById(id);
    }

    @GetMapping("/user/list")
    public List<User> findUserList() {
        return this.iUserService.findAllUsers();
    }

    @PostMapping("/user/addUser")
    public User addUser(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "balance", required = false) String balance) {
        User user = new User();

        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);

        int result = iUserService.insertUser(user);
        if (result > 0) {
            return user;
        }

        user.setId(0L);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }

    @GetMapping("/user/ehcache")
    public String ehcache() {
        logger.info("===========  进行Encache缓存测试");

        List<User> allUsers = iUserService.findAllUsers();
        User lastUser = allUsers.get(allUsers.size() - 1);
        String lastUserUsername = lastUser.getUsername();
        String indexString = lastUserUsername.substring(4);

        logger.info("===========  ====生成第一个用户====");
        User user1 = new User();
        //生成第一个用户的唯一标识符 UUID
        user1.setName("user" + (Integer.parseInt(indexString) + 1));
        user1.setUsername(user1.getName());
        user1.setAge(1000);
        user1.setBalance("1000");
        if (iUserService.insertUser(user1) == 0) {
            throw new CacheException("用户对象插入数据库失败");
        }

        allUsers = iUserService.findAllUsers();
        lastUser = allUsers.get(allUsers.size() - 1);
        Long lastUserId = lastUser.getId();

        //第一次查询
        logger.info("===========  第一次查询");
        logger.info("===========  第一次查询结果: {}", iUserService.findUserById(lastUserId));
        //通过缓存查询
        logger.info("===========  通过缓存第 1 次查询");
        logger.info("===========  通过缓存第 1 次查询结果: {}", iUserService.findUserById(lastUserId));
        logger.info("===========  通过缓存第 2 次查询");
        logger.info("===========  通过缓存第 2 次查询结果: {}", iUserService.findUserById(lastUserId));
        logger.info("===========  通过缓存第 3 次查询");
        logger.info("===========  通过缓存第 3 次查询结果: {}", iUserService.findUserById(lastUserId));

        logger.info("===========  ====准备修改数据====");
        User user2 = new User();
        user2.setName(lastUser.getName());
        user2.setUsername(lastUser.getUsername());
        user2.setAge(lastUser.getAge() + 1000);
        user2.setBalance(String.valueOf(user2.getAge()));
        user2.setId(lastUserId);
        try {
            int result = iUserService.updateUser(user2);
            logger.info("===========  ==== 修改数据 == {} ==", (result > 0 ? "成功" : "失败"));
        } catch (CacheException e) {
            e.printStackTrace();
        }

        logger.info("===========  ====修改后再次查询数据");
        User resultObj = iUserService.findUserById(lastUser.getId());
        logger.info("===========  ====修改后再次查询数据结果: {}", resultObj);
        return "success";
    }


}
