package com.zzh.boot.service.impl;

import com.zzh.boot.dao.IUserDao;
import com.zzh.boot.entity.User;
import com.zzh.boot.exception.BusinessExtendsException;
import com.zzh.boot.exception.BusinessSubExtendsException;
import com.zzh.boot.exception.RollbackExceptionExtendsRuntimeException;
import com.zzh.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public int insertUser(User user) {
        int result = userDao.insertUser(user);
        if (result > 0) {
            // throw new RuntimeException("抛出 RuntimeException 异常，测试 rollbackFor = Exception.class 是否有效？");
            throw new RollbackExceptionExtendsRuntimeException("抛出 RollbackExceptionExtendsRuntimeException 异常，测试 rollbackFor = Exception.class 是否有效？");
        }
        return result;
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * 由于 RollbackExceptionExtendsException 是 Exception 的子类，不是 BusinessExtendsException 的子类，所以抛出该异常，会回滚数据；<br/>
     * <p>
     * <li>注意：如果要使得 noRollbackFor 属性生效，注解中 @Transactional 必须得只有 noRollbackFor 属性，然后 noRollbackFor 的异常必须得是自己定义的异常，然后抛 RuntimeException 异常，这样我们才可以测出 noRollbackFor 回滚与不回滚的场景出来；</li>
     *
     * @param user
     * @return
     */
    @Transactional(noRollbackFor = BusinessExtendsException.class)
    @Override
    public int replaceUser(User user) throws Exception {
        int result = userDao.insertUser(user);
        if (result > 0) {
            // throw new NullPointerException("抛出 NullPointerException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
            // throw new RollbackExceptionExtendsException("抛出 RollbackExceptionExtendsException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
             throw new BusinessSubExtendsException("抛出 BusinessSubExtendsException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
//            throw new RuntimeException("抛出 RuntimeException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
        }
        return result;
    }
}