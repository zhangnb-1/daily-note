package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua.line;

import java.util.Arrays;
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
        System.out.println(PaiTaijie(10,new int[]{1,3}));
        System.out.println(PaiTaijieNew(10,new int[]{1,3,5}));
        System.out.println(minCostClimbingStairs(new int[]{10,15,20}));
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
        dp[0] = 1;
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        for (int i = 1; i <= n; i++) {
            // 找到当前台阶数能走的步子类型
            int curStepNum = i;
            List<Integer> stepTypeList = list.stream().filter(num -> num <= curStepNum).collect(Collectors.toList());
            dp[i] = 0;
            for (Integer stepNum : stepTypeList) {
                dp[i]+=dp[i-stepNum];
            }

        }
        return dp[n];
    }

    // 对数组长度进行优化
    public static int PaiTaijieNew(int n,int arr[]){
        // dp[i] :所有到达第i阶的办法
//        int [] dp = new int[n+1];
//        for (int i = 0; i <= n; i++) {
//            if(i == 0) dp[i] = 0;
//            else if (i == 1) dp[i] = 1;
//            else if (i == 2) dp[i] = 2;
//            else dp[i] = dp[i-2]+dp[i-1];
//        }
//        return dp[n];
        int maxStep = Arrays.stream(arr).max().getAsInt();
        int [] dp = new int[Math.min(n,maxStep)];
        dp[0] = 1;
        int curIndex = 0;
        for (int i = 1; i <= n; i++) {
            curIndex = i%dp.length;
            // 找到当前台阶数能走的步子类型
            int result = 0;
            for (int stepNum : arr) {
                if(stepNum<=i)
                    result+=dp[(i-stepNum)%dp.length];
            }
            dp[curIndex] = result;

        }
        return dp[curIndex];
    }

    /**
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     *
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     *
     * 请你计算并返回达到楼梯顶部的最低花费。
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        // dp[i] =
//        if(cost == null) return 0;
//        int[] cost2 = Arrays.copyOf(cost,cost.length+1);
//        if(cost.length < 2) return cost[cost.length-1] ;
//        int [] dp =new int[cost.length+2];
//        for (int i = 2; i < dp.length; i++) {
//            dp[i] = Math.min(dp[i-1]+cost2[i-1],dp[i-2]+cost2[i-2]);
//        }
//        return dp[dp.length-1];

        int pre1 = 0,pre2 = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            int cur = Math.min(pre1+cost[i-1],pre2+cost[i-2]);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
