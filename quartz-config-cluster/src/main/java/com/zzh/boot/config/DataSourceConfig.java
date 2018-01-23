package com.zzh.boot.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class DataSourceConfig {

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DataSource configDataSource = new DataSource();
        configDataSource.setUrl(env.getProperty("spring.datasource.url"));
        configDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        configDataSource.setUsername(env.getProperty("spring.datasource.username"));
        configDataSource.setPassword(env.getProperty("spring.datasource.password"));
        configDataSource.setInitialSize(5);
        configDataSource.setDefaultAutoCommit(true);

        if (configDataSource.getUrl().startsWith("jdbc:mysql://ip")) {
            throw new RuntimeException("请配置数据源地址");
        }
        if (configDataSource.getUsername().startsWith("username")) {
            throw new RuntimeException("请配置数据源用户名");
        }
        if (configDataSource.getPassword().startsWith("password")) {
            throw new RuntimeException("请配置数据源密码");
        }
        //configDataSource.setInitialSize();
        //configDataSource.setMinEvictableIdleTimeMillis();
        //configDataSource.setNumTestsPerEvictionRun();
        //configDataSource.setTestWhileIdle();
        //configDataSource.setMaxActive();
        //configDataSource.setMaxIdle();
        //configDataSource.setMinIdle();
        //configDataSource.setMaxWait();
        //configDataSource.setRemoveAbandoned();
        //configDataSource.setRemoveAbandonedTimeout();
        //configDataSource.setValidationQuery();
        //configDataSource.setValidationQueryTimeout();
        //configDataSource.setValidationInterval();
        //configDataSource.setTimeBetweenEvictionRunsMillis();
        return configDataSource;
    }
}