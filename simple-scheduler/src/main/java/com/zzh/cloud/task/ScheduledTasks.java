package com.zzh.cloud.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
//        System.out.println("当前时间: " + dateFormat.format(new Date()));
        logger.info("打印当前时间: {}.", dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = 5000)
    public void addMovieJob() {
//        System.out.println("当前时间: " + dateFormat.format(new Date()));
        logger.info("当前时间: {}.", dateFormat.format(new Date()));
    }
}