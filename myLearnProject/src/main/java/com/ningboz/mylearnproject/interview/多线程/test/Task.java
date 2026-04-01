package com.ningboz.mylearnproject.interview.多线程.test;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Task
 * @author: Znb
 * @date: 2025/7/31
 * @description:
 */
@Getter
public class Task implements Runnable{

    private List<Task> preTaskList;
    private List<Task> aftTaskList;

    private ReentrantLock lock;
    private Condition condition;
    private TaskStatus taskStatus;
    private String printVal;

    public Task(ReentrantLock lock,String printVal) {
        this.lock = lock;
        this.condition = lock.newCondition();
        this.preTaskList = new ArrayList<>();
        this.aftTaskList = new ArrayList<>();
        this.taskStatus = TaskStatus.INIT;
        this.printVal = printVal;
    }

    public void addPreTask(Task task){
        this.preTaskList.add(task);
    }

    public void addAftTask(Task task){
        this.aftTaskList.add(task);
    }

    @Override
    public void run() {

        waitBefore();

        changeStatus(TaskStatus.EXCUTE);
        work();
        changeStatus(TaskStatus.COMPLETE);

        signalAfter();

    }

    private void waitBefore() {
        lock.lock();
        try {
            while (!isComparable()){
                // 阻塞 等待唤醒
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    private boolean isComparable() {
        return preTaskList.stream().allMatch(task -> task.getTaskStatus()==TaskStatus.COMPLETE);
    }

    private void work(){
        System.out.println(printVal);
    }

    private void changeStatus(TaskStatus taskStatus){
        this.taskStatus = taskStatus;
    }

    public void signalAfter(){
        lock.lock();
        for (Task task : this.aftTaskList) {
            task.getCondition().signal();
        }
        lock.unlock();
    }
}
