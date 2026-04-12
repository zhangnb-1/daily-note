package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua;

public class Qujian {
    /**
     * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
     * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     *
     * 求所能获得硬币的最大数量。
     * 
     * n == nums.length
     * 1 <= n <= 300
     * 0 <= nums[i] <= 100
     */
    public int maxCoins(int[] nums) {
        /**
         *  dp0[j] : nums数组前j个气球的最大数量
         *  1.只剩下第j个气球单独戳破 ：            dp1[j] = dp3[j-1] + nums[j]
         *  2.戳第j个气球连带戳破第j-1个气球         dp2[j] = max(dp[j-2]) + nums[j-1]*nums[j]
         *  3.第j个气球被第j-1个气球戳破时连带戳破
         *  4.第j-2,j-1,j个气球被一起戳破          dp4[j] = max(dp[j-3]) + nums[j-2]*nums[j-1]*nums[j]
          */
        /**
         *      dp[i][j]:表示填满（i,j）开区间的最大数
         *      将nums扩张2个单位 前后各填1
         *      最终结果即返回dp[0][nums.length+1]的值
         *      因为是开区间，所以当i>=j-1时dp[i][j] = 0;
         *      i<j-1 时 dp[i][j] = max(val[i]×val[k]×val[j]+dp[i][k]+dp[k][j])；k = i+1
          */

        int [] numArr = new int[nums.length+2];

        for (int i = 0; i <numArr.length; i++) {
            if(i == 0 || i == numArr.length-1) numArr[i] = 1;
            else numArr[i] = nums[i-1];
        }
        int [][] dp = new int[numArr.length][numArr.length];
        for (int start = dp.length-1; start >= 0; start--) {
            for (int end = start; end < dp.length; end++) {
                if(start<=end-2){
                    int max = Integer.MIN_VALUE;
                    for (int k = start+1; k < end; k++) {
                        max = Math.max(numArr[start]*numArr[k]*numArr[end] + dp[start][k] + dp[k][end],max);
                    }
                    dp[start][end] = max;
                }
            }
        }

        return dp[0][numArr.length-1];
    }

    public static void main(String[] args) {

    }
}
