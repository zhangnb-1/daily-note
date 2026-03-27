package com.ningboz.mygithubproject.jmm.volatileJMM;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2026/3/27
 * @description:
 */
public class Main {
    private volatile static int num;

    public void test(){
        num+=1;
        System.out.println(num);
    }
}
