package com.zzh.boot.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Job2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("==================================================================");
        System.out.println("===========        " + (new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSSSSS")).format(new Date()) + "         ============");
        System.out.println("=======================        Job2         ======================");
        System.out.println("==================================================================");
        System.out.println();
    }
}