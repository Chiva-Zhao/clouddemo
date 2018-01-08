package com.zzh.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@SpringBootApplication
public class FtpdemoApplication {
    private static Logger logger = LoggerFactory.getLogger(FtpdemoApplication.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(FtpdemoApplication.class, args);
        FtpUtils utils = new FtpUtils();
        utils.connect("10.38.0.128", "sxc", "sxc01sxc");
        Long start = System.currentTimeMillis();
        logger.error("下载开始" + new Date());
        utils.download("jusfounzip", new File("zhaozh_test"));
        logger.error("下载结束" + new Date());
        logger.error("耗时" + (System.currentTimeMillis() - start));
    }
}
