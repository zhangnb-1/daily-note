package com.ningboz.mylearnproject.interview.多线程.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2025/7/31
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
//        new TaskGroup()
        ReentrantLock lock = new ReentrantLock();
        List<Task> taskList = new ArrayList<Task>(){{
            add( new Task(lock, "head"));
            add( new Task(lock, "a1"));
            add( new Task(lock, "a2"));
            add( new Task(lock, "a3"));
            add( new Task(lock, "b1"));
            add( new Task(lock, "b2"));
            add( new Task(lock, "c1"));
            add( new Task(lock, "d1"));
            add( new Task(lock, "d2"));
        }};

        List<TaskConnect> connectList = new ArrayList<TaskConnect>(){{
            add(new TaskConnect(taskList.get(0),taskList.get(1)));
            add(new TaskConnect(taskList.get(0),taskList.get(2)));
            add(new TaskConnect(taskList.get(0),taskList.get(3)));
            add(new TaskConnect(taskList.get(1),taskList.get(7)));
            add(new TaskConnect(taskList.get(2),taskList.get(6)));
            add(new TaskConnect(taskList.get(3),taskList.get(4)));
            add(new TaskConnect(taskList.get(3),taskList.get(5)));
            add(new TaskConnect(taskList.get(4),taskList.get(6)));
            add(new TaskConnect(taskList.get(5),taskList.get(6)));
            add(new TaskConnect(taskList.get(6),taskList.get(7)));
            add(new TaskConnect(taskList.get(3),taskList.get(8)));
            add(new TaskConnect(taskList.get(8),taskList.get(4)));
        }};

        TaskGroup taskGroup = new TaskGroup(taskList.get(0), taskList, connectList, lock);
        taskGroup.start();
    }

    public static void method2(){
        CyclicBarrier cyclicBarrie = new CyclicBarrier(4);
    }
}
