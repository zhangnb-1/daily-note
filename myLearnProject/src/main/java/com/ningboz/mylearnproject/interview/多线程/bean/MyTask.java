package com.ningboz.mylearnproject.interview.多线程.bean;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: MyTask
 * @author: Znb
 * @date: 2025/7/31
 * @description:
 */
public abstract class MyTask implements Runnable {
    private List<Long> preTaskId;
    private Long taskId;
    private Long taskGroupId;

    @Override
    public void run() {
//        TaskResult result = excuteTask();


    }
}
