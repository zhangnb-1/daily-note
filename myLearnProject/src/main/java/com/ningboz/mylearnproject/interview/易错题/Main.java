package com.ningboz.mylearnproject.interview.易错题;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        System.out.println(finallyShunxu());
//        threadStateMethod();
    }

    // finally 代码块执行顺序
    private static int finallyShunxu(){
        int num =0 ;
        try {
            num++;
            return num++;
        }catch (Exception e){
            return num;
        }finally{
            return ++num;
        }
    }

    // Future如何处理异常
    private static void futureException(){
        CompletableFuture future = new CompletableFuture<>();


    }

    public Main() {
        super();
    }

    // 消息摘要算法
    // 线程状态 以及 转换
    private static void threadStateMethod(){
        /**
         *  传统线程状态：初始，就绪，运行，阻塞，终止
         *  java线程状态：初始，可运行，阻塞，限时等待，不限时等待，终止
         *  对应：
         *      1. 就绪，运行 -> 可运行
         *      2. 阻塞 -> 阻塞、限时等待、不限时等待
         *
         */
        Object lock = new Object();
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("execute1 thread");;
            }
        };
        System.out.println(thread.getState());
        ReentrantLock lock1 = new ReentrantLock();
        Condition condition = lock1.newCondition();
//        condition.await();

    }
    // redis数据类型
}
