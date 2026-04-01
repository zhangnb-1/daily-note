package com.ningboz.mylearnproject.interview.多线程.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: FooBar
 * @author: Znb
 * @date: 2025/8/1
 * @description:
 */
class FooBar {
    private int n;
    private ReentrantLock lock;
    private Condition condition1;
    private Condition condition2;

    public FooBar(int n) {
        this.n = n;
        lock = new ReentrantLock();
        condition1 = lock.newCondition();
        condition2 = lock.newCondition();
    }

    public void foo() throws InterruptedException {


        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
//            printFoo.run();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }
}