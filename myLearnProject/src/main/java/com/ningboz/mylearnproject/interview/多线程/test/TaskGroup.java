package com.ningboz.mylearnproject.interview.多线程.test;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @ClassName: TaskGroup
 * @author: Znb
 * @date: 2025/7/31
 * @description:
 */
@Getter
public class TaskGroup {
    private List<Task> taskList;
    private Task head;
    private ReentrantLock lock;


    public TaskGroup(Task head, List<Task> taskList, List<TaskConnect> connectList, ReentrantLock lock) {
        this.taskList = new ArrayList<>();
        this.head = head;
        this.lock = lock;
        if(CollUtil.isNotEmpty(taskList)){
            this.taskList.addAll(taskList);
        }
        if(CollUtil.isNotEmpty(connectList)){
            for (TaskConnect taskConnect : connectList) {
                taskConnect.getPreTask().addAftTask(taskConnect.getAftTask());
                taskConnect.getAftTask().addPreTask(taskConnect.getPreTask());
            }

        }
    }

    public void start(){
        for (Task task : taskList) {
            new Thread(task).start();
        }

        lock.lock();
        try {
            head.getCondition().signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}
