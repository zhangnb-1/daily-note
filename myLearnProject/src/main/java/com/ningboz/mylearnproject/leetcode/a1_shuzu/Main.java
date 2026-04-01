package com.ningboz.mylearnproject.leetcode.a1_shuzu;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2025/7/23
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        method4();
    }

    // 二分查找 leetcode:704
    public static void method1(){
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 6));
    }
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length==0)
            return -1;

        int startIndex = 0,endIndex = nums.length-1;

        while(startIndex<=endIndex){
            int middle = (startIndex+endIndex)>>1;
            int val = nums[middle];
            if(val == target)
                return middle;
            if(val>target)
                endIndex = middle-1;
            if(val<target)
                startIndex = middle+1;
        }
        return -1;
    }


    // 数组删除元素 leetcode:27
    public static void method2(){
        int[] arr = {3, 2, 2, 3};
        System.out.println(removeElement(arr, 3));
        System.out.println(String.join(",", Arrays.stream(arr).boxed().map(num -> String.valueOf(num)).collect(Collectors.toList())));
    }
    public static int removeElement(int[] nums, int val) {
        int removeNum = 0;
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if(v == val){
                removeNum ++;
            }else{
                nums[i-removeNum] = nums[i];
            }
        }

        return nums.length-removeNum;
    }

    // 有序数组平方 leetcode:977
    // 空间复杂度有优化空间
    public static void method3(){
        int[] arr = {-7,-3,2,3,11};
        print(sortedSquares(arr));
    }
    public static int[] sortedSquares(int[] nums) {
        int startIndex = 0,endIndex = nums.length-1;
        for (int i = 0; i < nums.length; i++) {
            nums[i]*=nums[i];
        }

        int[] arr = new int[nums.length];
        int k = arr.length-1;
        while(startIndex <= endIndex){
            int num1 = nums[startIndex];
            int num2 = nums[endIndex];
            if(num1>num2){
                arr[k] = num1;
                startIndex++;
            }
            else {
                arr[k]=num2;
                endIndex--;
            }
            k--;
        }

        return arr;
    }

    // 长度最小的子数组 leetcode:209
    public static void method4(){
        int[] arr = {2,3,1,2,4,3};
        System.out.println((minSubArrayLen(7,arr)));
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int result = 0;

        int start = 0,end = 0,cur = nums[0];
        while(true){
            if(cur>=target){
                // 符合条件
                if(end==start) return 1;
                else {
                    int length = end - start + 1;
                    result = result == 0?length:Math.min(result,length);
                    cur-=nums[start];
                    start++;
                }
            }else{
                //
                end++;
                if(end > nums.length-1)
                    break;
                cur+=nums[end];
            }
        }

        return result;
    }

    // 螺旋矩阵 leetcode:59
    public static void method5(){
        System.out.println(generateMatrix(10));
    }
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int i=0,j=-1,v = 1;
        // 按圈来，每圈步骤为 右n,下n-1,左n-1,上n-2
        while(n>0){
            for (int k = 1; k <= n; k++) {
                j++;
                result[i][j] = v;
                v++;
            }
            if(n-1>0){
                for (int k = 1; k <= n-1; k++) {
                    i++;
                    result[i][j] = v;
                    v++;
                }

                for (int k = 1; k <= n-1; k++) {
                    j--;
                    result[i][j] = v;
                    v++;
                }
            }

            if(n-2>0){
                for (int k = 1; k <= n-2; k++) {
                    i--;
                    result[i][j] = v;
                    v++;
                }
            }
            n-=2;
        }

        return result;
    }

    private static void print(int[] arr){
        System.out.println(String.join(",", Arrays.stream(arr).boxed().map(num -> String.valueOf(num)).collect(Collectors.toList())));
    }
}
