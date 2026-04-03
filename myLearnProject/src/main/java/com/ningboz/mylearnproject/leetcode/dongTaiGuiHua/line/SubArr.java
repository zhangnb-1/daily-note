package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua.line;

import java.util.*;

/**
 * @ClassName: SubArr 子数组问题
 * @author: Znb
 * @date: 2026/4/3
 * @description:
 */
public class SubArr {
    public static void main(String[] args) {
//        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLIS2(new int[]{10,12,14,16,5,8,9,11,13}));
    }

    // 最大子数组和
    public int maxSubArray(int[] nums) {
        /**
         *      dp[i] 包含第i个数字的子数组的最大和
         *      dp[i] = dp[i-1] + nums[i];
          */

        int [] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSubArrHe = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + Math.max(dp[i-1],0);
            maxSubArrHe = Math.max(maxSubArrHe,dp[i]);
        }

        return maxSubArrHe;
    }

    public int maxSubArray2(int[] nums) {
        /**
         *      dp[i] 包含第i个数字的子数组的最大和
         *      dp[i] = dp[i-1] + nums[i];
         */

        int dp = nums[0];
        int maxSubArrHe = dp;

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i] + Math.max(dp,0);
            maxSubArrHe = Math.max(maxSubArrHe,cur);
            dp = cur;
        }

        return maxSubArrHe;
    }

    // 最长递增子序列 返回长度
    public static int lengthOfLIS(int[] nums) {
        /**
         *  dp[i] = 数组前i位的最长递增子序列
         */

        List<int[]> list = new ArrayList<>();
        for (int num : nums) {
            boolean flag = true;
            for (int i = 0; i < list.size(); i++) {
                int[] ints = list.get(i);
                if(ints[0] < num){
                    flag = false;
                    int val = ints[1]+1;
                    ints[1] = val;
                }
            }

            if(flag){
                list.add(new int[]{num,1});
            }
        }

        int max = Integer.MAX_VALUE;
        for (int[] ints : list) {
            max = Math.max(ints[1],max);
        }

        return max;
    }

    //
    public static int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }


}
