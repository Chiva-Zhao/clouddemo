package com.zzh.boot.service.impl;

import com.zzh.boot.dao.IAccountDao;
import com.zzh.boot.dao.IUserDao;
import com.zzh.boot.entity.Account;
import com.zzh.boot.entity.User;
import com.zzh.boot.exception.BusinessExtendsException;
import com.zzh.boot.exception.BusinessSubExtendsException;
import com.zzh.boot.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IAccountDao accountDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public int addMovie(User user, Account account) {
        int result1 = userDao.addUser(user);
        int result2 = accountDao.add(account);

        if (result1 > 0 || result2 > 0) {
            throw new RuntimeException("抛出 RuntimeException 异常，测试 rollbackFor = Exception.class 是否有效？");
        }
        return 0;
    }

    @Transactional(noRollbackFor = BusinessExtendsException.class)
    @Override
    public int insertMovie(User user, Account account) throws Exception {
        int result1 = userDao.insertUser(user);
        int result2 = accountDao.add(account);
        if (result1 > 0 || result2 > 0) {
            // throw new NullPointerException("抛出 NullPointerException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
            // throw new RollbackExceptionExtendsException("抛出 RollbackExceptionExtendsException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
            throw new BusinessSubExtendsException("抛出 BusinessSubExtendsException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
            // throw new RuntimeException("抛出 RuntimeException 异常，测试 noRollbackFor = RuntimeException.class 是否有效？");
        }
        return 0;
    }
}