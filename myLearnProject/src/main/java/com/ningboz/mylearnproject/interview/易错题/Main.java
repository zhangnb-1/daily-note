package com.ningboz.mylearnproject.interview.易错题;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        System.out.println(finallyShunxu());
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
    // 消息摘要算法
    // 线程状态 以及 转换
    // redis数据类型
}
