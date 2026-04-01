package com.ningboz.mylearnproject.interview.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2025/8/11
 * @description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
//        List<Integer> list = new ArrayList<>();
        for (int i = 0; ; i++) {
            Thread.sleep(500);
            System.out.println(i);
//            list.add(i);
        }
    }
}
