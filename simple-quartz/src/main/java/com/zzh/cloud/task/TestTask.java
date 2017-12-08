package com.zzh.cloud.task;

import com.zzh.cloud.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0
 * @auther air
 * @date 2017/12/8 22:59
 **/
public class TestTask {
    private static final Logger LOG = LoggerFactory.getLogger(TestTask.class);

    public void run() {
        if (LOG.isInfoEnabled()) {
            LOG.info("测试任务线程开始执行");
//            new ScheduleJobService().getScheduleJob();
        }
    }
}
