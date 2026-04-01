package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: Taijie
 * @author: Znb
 * @date: 2026/4/1
 * @description:
 */
public class Taijie {
    public static void main(String[] args) {
        System.out.println(PaiTaijie(10));
    }
    /**
     * 假设你要爬 10 级台阶，每次可以走 1 步或 2 步，求多少种方法。
     */
    public static int PaiTaijie(int n){
        // dp[i] :所有到达第i阶的办法
        int [] dp = new int[n+1];
        for (int i = 0; i <= n; i++) {
            if(i == 0) dp[i] = 0;
            else if (i == 1) dp[i] = 1;
            else if (i == 2) dp[i] = 2;
            else dp[i] = dp[i-2]+dp[i-1];
        }
        return dp[n];
    }

    /**
     * 假设你要爬 n 级台阶，每次可以走的步数为数组里的任一值
     */
    public static int PaiTaijie(int n,int arr[]){
        // dp[i] :所有到达第i阶的办法
//        int [] dp = new int[n+1];
//        for (int i = 0; i <= n; i++) {
//            if(i == 0) dp[i] = 0;
//            else if (i == 1) dp[i] = 1;
//            else if (i == 2) dp[i] = 2;
//            else dp[i] = dp[i-2]+dp[i-1];
//        }
//        return dp[n];
        int [] dp = new int[n+1];
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        for (int i = 1; i <= n; i++) {
            // 找到当前台阶数能走的步子类型
            int curStepNum = i;
            List<Integer> collect = list.stream().filter(num -> num <= curStepNum).collect(Collectors.toList());
            if()
        }

        return 0;
    }
}
