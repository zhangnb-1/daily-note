package com.ningboz.mylearnproject.leetcode.thread;

public class FooBar {
    private int n;
    private Object fooLock = new Object();
    private Object barLock = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (this){
            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                fooLock.wait();
                barLock.notify();
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (this){
            barLock.wait();
            for (int i = 0; i < n; i++) {

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                barLock.wait();
                fooLock.notify();
            }
        }
    }
}