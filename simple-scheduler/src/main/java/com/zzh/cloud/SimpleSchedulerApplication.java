package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSchedulerApplication.class, args);
    }
}
