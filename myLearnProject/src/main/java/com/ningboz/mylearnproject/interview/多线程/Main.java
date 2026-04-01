package com.ningboz.mylearnproject.interview.多线程;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger count = new AtomicInteger(0);
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        method1();
//        method1();
    }

    private static void method3() {
    }

    private static void method2() {
        System.out.println(count.addAndGet(1));
        System.out.println(count.getAndAdd(1));
        System.out.println(count.compareAndSet(3,3));
        System.out.println(count.get());
    }

    private static void method1() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Object lock = new Object();
        new Thread(()->{
            synchronized (lock){
                while (count.get()<200){
                    lock.notify();
                    System.out.println(Thread.currentThread().getName()+"--"+count.addAndGet(1));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(()->{
            synchronized (lock){
                while (count.get()<200){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName()+"--"+count.addAndGet(1));
                    lock.notify();
                }
            }

        }).start();
    }
}
