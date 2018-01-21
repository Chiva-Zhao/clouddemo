package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SimpleAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAsyncApplication.class, args);
    }
}
