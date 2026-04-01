package com.ningboz.mylearnproject.leetcode.code0722;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2025/7/22
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        int [] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = trap(arr);
        System.out.println(trap);
    }

    public static int trap(int[] height) {
        Map<Integer,int[]> map = new HashMap<>();
        for (int i = 0; i < height.length; i++) {
            int heightVal = height[i];
            if(!map.containsKey(heightVal)){
                int[] arr = {i,i,1};
                map.put(heightVal,arr);
            }else{
                map.get(heightVal)[1] = i;
                map.get(heightVal)[2] += 1;
            }
        }

        int result = 0;
        int start = Integer.MAX_VALUE,end = Integer.MIN_VALUE;
        List<Integer> sortList = map.keySet().stream().sorted().collect(Collectors.toList());
        for (int i = sortList.size()-1; i >=0 ; i--) {
            Integer heightVal = sortList.get(i);
            int[] range = map.get(heightVal);
            if(i == sortList.size()-1){
                start = range[0];
                end = range[1];
                result += (end-start+1)*heightVal - range[2]*heightVal;
            }else{
                int startIndex = Math.min(range[0],start);
                int endIndex = Math.max(range[1],end);
                int n = endIndex-startIndex+1 - (end-start +1);
                result+= n*heightVal - range[2]*heightVal;
                start = startIndex;
                end = endIndex;
            }
        }

        return result;
    }

//    public static int trap2(int[] height) {
//        int[][] dp = new int[height.length][10];
//        for (int i = 0; i < height.length; i++) {
//            // 找到比自己高的最近的，或者找往前里面最高的
//            int heightVal = height[i];
//            if(i == 0){
//                dp[i][0] = heightVal;
//                dp[i][1] = 0;
//            }
//            else{
//                if(heightVal>)
//            }
//        }
//
//
//        return 0;
//    }



}
