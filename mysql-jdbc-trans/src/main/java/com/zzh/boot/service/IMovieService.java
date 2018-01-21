package com.zzh.boot.service;

import com.zzh.boot.entity.Account;
import com.zzh.boot.entity.User;

public interface IMovieService {

    int addMovie(User user, Account account);

    int insertMovie(User user, Account account) throws Exception;
}