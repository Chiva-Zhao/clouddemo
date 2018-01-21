package com.zzh.boot.repository;

import com.zzh.boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-21 10:55
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
