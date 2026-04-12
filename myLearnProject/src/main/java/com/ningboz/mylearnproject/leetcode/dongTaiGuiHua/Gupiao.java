package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua;

import java.util.Arrays;

// 状态机与股票问题
public class Gupiao {
    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i]
     *  表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，
     *  并选择在 未来的某一个不同的日子 卖出该股票。
     *  设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。
     *  如果你不能获取任何利润，返回 0 。
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length<2) return 0;
        int max = 0;
        for (int start = 0 , end = 1; end < prices.length; ) {
            if(prices[end]>=prices[end-1]){
                max = Math.max(max,prices[end]-prices[start]);
            }else if(prices[end]<prices[start]){
                start = end;
            }
            end++;
        }
        return max;
    }

    // 在i天内可以多次买卖
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length<2) return 0;
        int sum = 0;
        for (int i = 1; i < prices.length; i++)
            if(prices[i]>prices[i-1]) sum+=(prices[i]-prices[i-1]);
        return sum;
    }

    // 在i天内 最多两次买卖
    public static int maxProfit3(int[] prices) {
        // dp[i][j] = max(dp[i][k]+dp[k+1][j]) 最终返回dp[0][n-1]
        return maxProfit3Format(prices, 2);
    }

    // 最多进行k次交易
    public static int maxProfit3Format(int[] prices,int k) {
        // dp[i][j] = max(dp[i][k]+dp[k+1][j]) 最终返回dp[0][n-1]
        // dp[i][j] = 第i天完成j比交易的最大利润

        // 两天才能完成一笔交易
        int n = prices.length;
        k =  Math.min(n /2,k)*2+1;
        int [][] dp = new int[n][k];
        // 边界初始化 初始交易状态，多少天都是0利润，第一天，不管第几次交易，买入都是-prices[0],卖出都是0
        for (int i = 0; i < n; i++)
            dp[i][0] = 0;
        for (int j = 0; j < k; j++)
            dp[0][j] = (j%2)*-prices[0];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                if(j%2==1){
                    // 持有 两种情况 今天买入，或者今天之前已买入
                    dp[i][j] = Math.max(dp[i-1][j-1]-prices[i],dp[i-1][j]);
                }else{
                    // 已卖出 两种情况 今天卖出，或者今天之前已卖出
                    dp[i][j] = Math.max(dp[i-1][j-1]+prices[i],dp[i-1][j]);
                }
            }
        }

        return dp[n-1][k-1];

    }

    public static int maxProfit3Format2(int[] prices,int k) {
        // dp[i][j] = max(dp[i][k]+dp[k+1][j]) 最终返回dp[0][n-1]
        // dp[i][j] = 第i天完成j比交易的最大利润

        // 最多交易次数
        int n = prices.length;
        k = Math.min(n /2,k);
        // 包含0次交易
        int [][][] dp = new int[n][k+1][2];
        // --- 1. 初始化第0天的情况 ---
        for (int j = 0; j <= k; j++) {
            // 第 0 天，无论允许交易几次，只要持有股票，利润就是 -prices[0]
            dp[0][j][0] = -prices[0];
            // 第 0 天，不持有股票，利润为 0
            dp[0][j][1] = 0;
        }
        // 0次交易时，任意前i天的最大利润都是0

        // dp[i][j][0] 第i天第j次交易持有状态 dp[i][j][1] 第i天第j次交易完成状态
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k+1; j++) {
                // 当天买入 或 之前买入
                dp[i][j][0] = Math.max(dp[i-1][j-1][1]-prices[i],dp[i-1][j][0]);
                dp[i][j][1] = Math.max(dp[i-1][j][0]+prices[i],dp[i-1][j][1]);

            }
        }
        return 0;
    }

    // 含冷冻期
    public static int maxProfitLengdong(int[] prices) {
        // 总天数
        int n = prices.length;
        // 一定天数内 最多交易的轮数
//        int k = ((n+1)/3+1)*3;
        int k = (n+2)/3*3;
        int [] dp = new int[k];
        for(int j=0;j<k;j++){
            if(j%3==1) dp[j] = -prices[0];
        }

        int last = 0;
        for(int i = 1;i<n;i++){
            for(int j=1;j<k;j++){
                int cur = dp[j];
                if(j%3==1){
                    // 持有
                    dp[j] = Math.max(last-prices[i],dp[j]);
                }else if(j%3==2){
                    // 卖出
                    dp[j] = Math.max(last+prices[i],dp[j]);
                }else{
                    // 冷冻期 等于 前一天卖出 或者 前一天也是冷冻期
                    dp[j] = Math.max(last,dp[j]);
                }
                last = cur;
            }
        }

        return dp[k-1];
    }

    public static int maxProfitLengdong2(int[] prices) {
        // 总天数
        int n = prices.length;
        // 一定天数内 最多交易的轮数 不限制轮数时，不用加上轮数限制
//        int k = ((n+1)/3+1)*3;
        int [][] dp = new int[n][3];
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][1] = Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
            dp[i][2] = Math.max(dp[i-1][1]+prices[i],dp[i-1][2]);
            dp[i][0] = Math.max(dp[i-1][2],dp[i-1][0]);
        }
        return dp[n-1][2];
    }

    // 内存缩小版
    public static int maxProfitLengdong3(int[] prices) {
        // 总天数
        int n = prices.length;
        // 一定天数内 最多交易的轮数 不限制轮数时，不用加上轮数限制
//        int k = ((n+1)/3+1)*3;
        int [] cur = new int[3];
        cur[1] = -prices[0];
        int [] last = null;
        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < last.length; j++) {
//                last[j] = cur[j];
//            }
            last = Arrays.copyOf(cur,3);
            cur[1] = Math.max(last[0]-prices[i],last[1]);
            cur[2] = Math.max(last[1]+prices[i],last[2]);
            cur[0] = Math.max(last[2],last[0]);
        }
        return cur[2];
    }


    public static void main(String[] args) {
//        System.out.println(maxProfit3(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(maxProfitLengdong3(new int[]{1,2}));
        System.out.println(maxProfitLengdong3(new int[]{1,2,3,0,2}));
        System.out.println(maxProfitLengdong2(new int[]{1,2}));
        System.out.println(maxProfitLengdong2(new int[]{1,2,3,0,2}));
    }
}
