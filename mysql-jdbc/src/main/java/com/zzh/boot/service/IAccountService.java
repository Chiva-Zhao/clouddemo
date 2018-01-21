package com.zzh.boot.service;

import com.zzh.boot.entity.Account;

import java.util.List;

public interface IAccountService {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();

}