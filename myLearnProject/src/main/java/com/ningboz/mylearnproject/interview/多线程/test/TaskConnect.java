package com.ningboz.mylearnproject.interview.多线程.test;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Condition;

/**
 * @ClassName: TaskConnect
 * @author: Znb
 * @date: 2025/7/31
 * @description:
 */
@Getter
@Setter
public class TaskConnect {
    private Task preTask;
    private Task aftTask;

    public TaskConnect(Task preTask, Task aftTask) {
        this.preTask = preTask;
        this.aftTask = aftTask;
    }
}
