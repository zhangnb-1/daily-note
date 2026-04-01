package com.ningboz.mygithubproject.suanfa;

import java.util.Arrays;

/**
 * @ClassName: DongTaiGH
 * @author: Znb
 * @date: 2026/4/1
 * @description:
 */
public class DongTaiGH {
    public static void main(String[] args) {
        String str1 = "ace";
        String str2 = "abcce";
        System.out.println(levenshtein(str1 , str2));
        System.out.println(levenshtein2(str1 , str2));

        double[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(getMaxLirun(prices));
    }

    // 最短编辑距离
    private static int levenshtein(String str1,String str2){
        System.out.println(String.join("        ",str1,str2));
        int row = str1.length()+1;
        int col = str2.length()+1;
        int [][] dp = new int[row][col];

        // 初始化
        for (int i = 0; i < row; i++) {
            dp[i][0] = i;
//            showArr(dp);
        }

        for (int i = 0; i < col; i++) {
            dp[0][i]=i;
//            showArr(dp);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i == 0)
                    dp[0][j] = j;
                else if(j == 0)
                    dp[i][0] = i;
                else if(str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else {
                    dp[i][j] = Arrays.asList(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]).stream().mapToInt(num->num).min().getAsInt()+1;
                }
//                showArr(dp);
            }
        }
        showArr(dp);

        return dp[row-1][col-1];
    }

    private static void showArr(int [][] arr){
        for (int[] row : arr) {
            for (int val : row) {
                // %-4d 表示左对齐，占4个字符宽度，让数字排列整齐
                System.out.printf("%-4d", val);
            }
            System.out.println(); // 每行打印完换行
        }

//        System.out.println(Arrays.deepToString(arr));
    }

    private static void showArr(int [] arr){
            for (int val : arr) {
                // %-4d 表示左对齐，占4个字符宽度，让数字排列整齐
                System.out.printf("%-4d", val);
            }
            System.out.println(); // 每行打印完换行

//        System.out.println(Arrays.deepToString(arr));
    }

    private static int levenshtein2(String str1,String str2){
        int row = str1.length()+1;
        int col = str2.length()+1;
        int [] dp = new int[col];

        // 初始化
        for (int i = 0; i < row; i++) {
            dp[0] = i;
        }

        int last=0,cur = 0;

        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                cur = dp[i];
                if(i == 0) {
                    dp[i] = j;
                }else if(j == 0){
                    dp[i] = i;
                }else if(str1.charAt(j-1) == str2.charAt(i-1))
                    dp[i] = last;
                else {
                    dp[i] = Arrays.asList(dp[i-1],dp[i],last).stream().mapToInt(num->num).min().getAsInt()+1;
                }
                last = cur;
                System.out.printf("%-4d", dp[i]);
            }
            System.out.println();
//            showArr(dp);
        }

        return dp[col-1];
    }

    /**
     * LeetCode 121. 买卖股票的最佳时机
     * 难度：简单
     * 题目描述：
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 示例 1：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     */
    public static double getMaxLirun(double[] prices){
        for (double price : prices) {
            System.out.printf("%-8s", price);
        }
        System.out.println();

        double [] dp = new double[prices.length+1];
        for (int start = 0; start <= prices.length; start++) {
            for (int end = 0; end <= prices.length; end++) {
                if(start == 0 || end == 0 || end<=start){
                    dp[end] = 0;
                }else {
                    double val = prices[end-1] - prices[start-1];
                    dp[end] = Math.max(val,dp[end-1]);
                }
                System.out.printf("%-8s", dp[end]);
            }
            System.out.println();
        }
        return dp[prices.length];
    }

    public static double getMaxLirun2(double[] prices){
        // 天数  持有/不持有的状态
        for (double price : prices) {
            System.out.printf("%-8s", price);
        }
        System.out.println();

        double [] dp = new double[prices.length];
        return 0;
    }
}
