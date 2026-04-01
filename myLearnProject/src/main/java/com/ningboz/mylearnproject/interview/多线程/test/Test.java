package com.ningboz.mylearnproject.interview.多线程.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Test
 * @author: Znb
 * @date: 2025/7/31
 * @description:
 */
public class Test {
    private static final Lock lock = new ReentrantLock();
    private static final Condition[] conditions = new Condition[3];
    private static int completedThreads = 0;

    static {
        for (int i = 0; i < conditions.length; i++) {
            conditions[i] = lock.newCondition();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = createWorkerThread("A", conditions[0]);
        Thread threadB = createWorkerThread("B", conditions[1]);
        Thread threadC = createWorkerThread("C", conditions[2]);

        Thread threadD = new Thread(() -> {
            try {
                lock.lock();
                try {
                    // Wait until all workers have completed
                    for (Condition condition : conditions) {
                        condition.await();
                    }
                    System.out.println("Thread D is working...");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadD.start(); // 启动D线程，它会立即进入等待状态

        threadA.start();
        threadB.start();
        threadC.start();

    }

    private static Thread createWorkerThread(final String name, final Condition condition) {
        return new Thread(() -> {
            try {
                System.out.println("Thread " + name + " is working...");
                Thread.sleep(1000); // 模拟工作
                System.out.println("Thread " + name + " has finished.");

                lock.lock();
                try {
                    completedThreads++;
                    if (completedThreads == 3) { // 如果三个线程都完成了
                        for (Condition cond : conditions) {
                            cond.signal(); // 唤醒所有等待的D线程
                        }
                    } else {
                        condition.signal(); // 唤醒D线程，但可能不是全部完成
                    }
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
