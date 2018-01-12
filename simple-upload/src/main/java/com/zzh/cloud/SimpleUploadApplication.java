package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SimpleUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleUploadApplication.class, args);
        System.out.println("【【【【【【 FileUpload微服务 】】】】】】已启动.");
    }
}
