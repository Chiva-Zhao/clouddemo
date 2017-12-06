package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserProviderRegApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderRegApplication.class, args);
    }
}
