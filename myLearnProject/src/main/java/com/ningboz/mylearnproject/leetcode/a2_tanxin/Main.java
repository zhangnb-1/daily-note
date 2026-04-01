package com.ningboz.mylearnproject.leetcode.a2_tanxin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        method1();
    }

    // 贪心：分发饼干 leetcode:455
    // 空间待优化
    public static int findContentChildren(int[] g, int[] s) {
        // 优先满足胃口小的孩子 并尽量将将好的去满足
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0,j = 0 ;
        while(i<g.length&&j<s.length){
            if(s[j]>=g[i]){
                // 满足
                i++;
                j++;
            }else{
                // 不满足，尝试用更大的饼
                j++;
            }
        }
        return i;
    }
    public static void method1(){
        int [] g = {1,2,3};
        int [] s = {1,1};
        System.out.println(findContentChildren(g, s));
    }

    // 贪心：摆动序列 leetcode:376
    // 待优化
    public static int wiggleMaxLength(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        if(nums.length == 1)
            return 1;

        int lastVal = nums[1];
        int cur = 2,result = 2;
        boolean flag = nums[1]>nums[0];

        for (int i = 1; i < nums.length; i++) {

        }
        return 0;
    }
    public static void method2(){
        int [] g = {1,2,3};
        int [] s = {1,1};
        System.out.println(findContentChildren(g, s));
    }
}
