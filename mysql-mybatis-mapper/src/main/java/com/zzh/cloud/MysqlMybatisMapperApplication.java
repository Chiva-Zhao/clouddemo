package com.zzh.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzh.cloud.mapper.**")
public class MysqlMybatisMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlMybatisMapperApplication.class, args);
    }
}
