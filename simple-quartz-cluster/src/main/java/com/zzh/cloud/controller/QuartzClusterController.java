package com.zzh.cloud.controller;

import com.zzh.cloud.service.ISchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 14:20
 */
@RestController
public class QuartzClusterController {
    @Autowired
    private ISchedulerService schedulerService;

    /**
     * 每隔多少秒调度一次。
     *
     * @param seconds
     * @return
     */
    @GetMapping("/modify/{seconds}")
    public String modifyStartQuartz(@PathVariable String seconds) {
        // eg: 0/10 * * ? * * *
        try {
            schedulerService.schedule("testJobTrigger", "DEFAULT", "0/" + seconds + " * * ? * * *");
        } catch (Exception e) {
            return "Failed";
        }
        return "Successful";
    }
}
