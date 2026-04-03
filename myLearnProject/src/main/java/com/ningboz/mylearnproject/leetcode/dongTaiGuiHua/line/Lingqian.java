package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua.line;

/**
 * @ClassName: Lingqian
 * @author: Znb
 * @date: 2026/4/3
 * @description:
 */
public class Lingqian {
    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     *
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     *
     * 你可以认为每种硬币的数量是无限的。
     */
    public static int coinChange(int[] coins, int amount) {
        int [] dp = new int[amount+1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            boolean flag = false;
            for (int coin : coins) {
                if(coin>i)  continue;
                int stepNum = dp[i - coin];
                if(stepNum == -1) continue;
                min = Math.min(min, stepNum +1);
                flag = true;
            }
            dp[i] = flag?min:-1;
        }
        return dp[amount];
    }


    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2,3,4},20));
    }

}
