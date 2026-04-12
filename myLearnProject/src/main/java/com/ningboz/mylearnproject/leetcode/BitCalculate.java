package com.ningboz.mylearnproject.leetcode;

import java.math.BigInteger;
import java.util.*;

// 位运算
public class BitCalculate {
    private static String method2(int N,int X,int[] searchArr,int[][] recordArr){
        Map<Integer, BigInteger> map = new HashMap<>();
        for(int[] arr: recordArr){
            int id = arr[0];
            int time = arr[1];
//            if(!map.containsKey(time)) map.put(time,BigInteger.ZERO.setBit(N));

            BigInteger bitMap = map.getOrDefault(time,BigInteger.ZERO);
            map.put(time,bitMap.setBit(id));
        }

        String [] result = new String[searchArr.length];
        for(int j = 0;j<searchArr.length;j++){
            int startTime = searchArr[j];
            BigInteger bitMap = BigInteger.ZERO;
            for(int i=0;i<X;i++){
                int time = startTime+i;
                BigInteger temp = map.get(time);
                if(temp!=null)
                    bitMap = bitMap.or(temp);
            }
            result[j] = String.valueOf(bitMap.bitCount());
        }

        return String.join(" ",result);
    }

    private static String method3(int N,int X,int[] searchArr,int[][] recordArr){
        Map<Integer, BigInteger> map = new HashMap<>();
        for(int[] arr: recordArr){
            int id = arr[0];
            int time = arr[1];
//            if(!map.containsKey(time)) map.put(time,BigInteger.ZERO.setBit(N));

            BigInteger bitMap = map.getOrDefault(time,BigInteger.ZERO);
            map.put(time,bitMap.setBit(id));
        }

        Map<Integer, List<Integer>> searchMap = new HashMap<>(searchArr.length);
        for(int i=0;i<searchArr.length;i++){
            List<Integer> list = null;
            if(!searchMap.containsKey(searchArr[i])){
                list = new ArrayList<>();
                searchMap.put(searchArr[i],list);
            }else{
                list = searchMap.get(searchArr[i]);
            }
            list.add(i);
        }


        Arrays.sort(searchArr);

        String [] result = new String[searchArr.length];
        int [] arr = new int[N];
        int res = 0;
        for(int j = 0;j<searchArr.length;j++){
            int startTime = searchArr[j];
            int endTime = startTime+X-1;
            if(j == 0) res += getAddNum(arr,map,startTime,endTime,true);
            else if(startTime-searchArr[j-1]< X){
                res += getAddNum(arr,map,searchArr[j-1],startTime-1,false);
                res += getAddNum(arr,map,searchArr[j-1]+X,endTime,true);
            }else{
                arr = new int[N];
                res += getAddNum(arr,map,startTime,endTime,true);
            }
            for (Integer index : searchMap.get(startTime)) {
                result[index] = String.valueOf(res);
            }
        }



        return String.join(" ",result);
    }

    private static int getAddNum(int[] arr,Map<Integer,BigInteger> map , int start, int end, boolean isAdd){
        int num = 0 ;
        for(int i = start;i<=end;i++){
            BigInteger bitMap = map.getOrDefault(i,BigInteger.ZERO);
            if(bitMap == null) continue;
            for(int j = 0;j<arr.length;j++){
                boolean has = bitMap.testBit(j);
                if(isAdd){
                    if(arr[j]==0 && has) num++;
                    if(has) arr[j]+=1;
                }else{
                    if(arr[j]==1 && has) num--;
                    if(has) arr[j]-=1;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(method3(72 ,82, new int[]{99,85,29,7,98,41,46,97,83,25,41,84,72,73,17,13,61,1,22,68,82,6,36,80,59,93,50,45,62,5,47,54,52,80,19,92,49,44,61,49,31,97}, new int[][]{
                        {45, 29},
                        {8, 62},
                        {46, 87},
                        {11, 1},
                        {46, 65},
                        {63, 0},
                        {17, 30},
                        {28, 53},
                        {40, 79},
                        {29, 58},
                        {8, 16},
                        {19, 92},
                        {67, 96},
                        {16, 65},
                        {32, 84},
                        {1, 92},
                        {37, 94},
                        {24, 100},
                        {59, 36},
                        {32, 94},
                        {16, 43},
                        {2, 87},
                        {40, 30},
                        {1, 45},
                        {62, 19},
                        {25, 49},
                        {62, 7},
                        {28, 29},
                        {18, 93},
                        {18, 14},
                        {21, 90},
                        {51, 65},
                        {2, 29},
                        {70, 54},
                        {23, 89},
                        {18, 60},
                        {4, 77},
                        {35, 17},
                        {11, 4},
                        {35, 84},
                        {68, 93},
                        {65, 29},
                        {67, 11},
                        {39, 6},
                        {28, 65},
                        {46, 15},
                        {66, 18},
                        {9, 52},
                        {3, 72},
                        {5, 56},
                        {32, 18},
                        {45, 80},
                        {70, 12},
                        {45, 24},
                        {40, 43},
                        {71, 17},
                        {68, 40},
                        {63, 50},
                        {70, 23},
                        {28, 88},
                        {8, 54},
                        {21, 32},
                        {47, 66},
                        {43, 35},
                        {26, 66},
                        {43, 67},
                        {58, 9},
                        {16, 49},
                        {51, 13},
                        {24, 84},
                        {57, 78},
                        {31, 10},
                        {49, 73},
                        {19, 84},
                        {9, 38},
                        {4, 37},
                        {1, 85},
                        {58, 25},
                        {13, 95},
                        {24, 99},
                        {5, 85},
                {19, 80},
                {62, 82}
        }));
    }
}
