package com.zzh.cloud.task;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 14:17
 *
 * testScheduleTask 字符串名称在 quartz.xml 中配置为属性 targetObject 的 value 值。</li>
 * sayHello 方法名称在 quartz.xml 中配置为属性 targetMethod 的 value 值。</li>
 */
@Configuration
@Component("testScheduleTask")
@EnableScheduling
public class ScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    public void sayHello(JobExecutionContext context) {
        logger.info("====    sayHello 123456789    ====");
        System.out.println("====    sayHello 123456789    ====");
    }
}