package com.ningboz.mylearnproject.hotSwap;

import java.util.Scanner;

// 简单实现热部署
public class Main {
    public static void main(String[] args) throws InterruptedException {
        User user = new User("zs",20);
        Scanner scanner = new Scanner(System.in);
        while (true){
            user.showUser();
            String next = scanner.next();
            if("0".equals(next))
                continue;
            else if ("1".equals(next)){

            }else{
                break;
            }
        }

        System.out.println("end");
    }

    // 重新加载User类
    public static void refreshUser(){

    }
}
