package com.ningboz.mygithubproject.jmm.volatileJMM;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2026/3/27
 * @description:
 */
public class Main {
    public static void main(String[] args) {

    }

    private static void ifMethod(){
        int i = 1,result=0;
        if(i<100){
            result+=i;
            i++;
        }
        System.out.println(result);
    }

    private static void whereMethod(){
        int i = 1,result=0;
        while (i<100){
            result+=i;
            i++;
        }
        System.out.println(result);
    }
}
