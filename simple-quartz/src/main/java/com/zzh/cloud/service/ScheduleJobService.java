package com.zzh.cloud.service;

import com.zzh.cloud.domain.ScheduleJob;
import com.zzh.cloud.util.SpringApplicationContextUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @auther air
 * @date 2017/12/8 23:12
 **/
public class ScheduleJobService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobService.class);

    public void getScheduleJob() {
        try {
            SchedulerFactoryBean schedulerFactoryBean = SpringApplicationContextUtil.getBean("scheduler");
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    ScheduleJob job = new ScheduleJob();
                    job.setJobName(jobKey.getName());
                    job.setJobGroup(jobKey.getGroup());
                    job.setDesc("触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setJobStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    jobList.add(job);
                }
            }

            for (ScheduleJob job : jobList) {
                logger.info("计划列表,name:{},group:{},desc:{},status:{}", job.getJobName(), job.getJobGroup(), job.getDesc(), job.getJobStatus());
            }

        } catch (SchedulerException e) {
            logger.error("SchedulerException", e);
        }
    }
}
