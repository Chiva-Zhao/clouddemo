package com.zzh.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("quartz.xml")
public class SimpleQuartzClusterApplication {
    private static final Logger logger = LoggerFactory.getLogger(SimpleQuartzClusterApplication.class);

    public static void main(String[] args) {
        logger.info("简单Quartz-Cluster,编码-" + System.getProperty("file.encoding"));
        SpringApplication.run(SimpleQuartzClusterApplication.class, args);
    }
}
