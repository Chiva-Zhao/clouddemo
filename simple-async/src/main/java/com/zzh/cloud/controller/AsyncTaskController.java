package com.zzh.cloud.controller;

import com.zzh.cloud.task.AsyncTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class AsyncTaskController {

    @Autowired
    AsyncTasks asyncTasks;

    /**
     * 测试异步任务。
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/task")
    public String task() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = asyncTasks.doTaskOne();
        Future<String> task2 = asyncTasks.doTaskTwo();
        Future<String> task3 = asyncTasks.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        String result = "任务全部完成，总耗时：" + (end - start) + "毫秒";
        return result;
    }
}