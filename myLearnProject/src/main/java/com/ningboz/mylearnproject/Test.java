package com.ningboz.mylearnproject;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 111000; k++) {

                }
            }
        }

        List<Integer> list = new ArrayList<>();
        int index = 0;
        while(true){
            if(list.size()==0){
                int num0 =1;
                System.out.println(num0);
                list.add(num0);
                index++;
                continue;
            }
            if(list.size()==1){
                int num1 =1;
                System.out.println(num1);
                list.add(num1);
                index++;
                continue;
            }

            int fbnq = FBNQ(1, 1);

            int num = FBNQ(list.get(index-2),list.get(index-1));

            if(num<=200){
                System.out.println(num);
                list.add(num);
                index++;
            }else{
                break;
            }
         }

    }

    public static int FBNQ(int num1 , int num2){
        return num1+num2;
    }


    /**
     * 根据三视图的平面点坐标List 还原出对应的三维立体坐标List
     * @param xyList
     * @param yzList
     * @param zxList
     * @return
     */
    public List<Double[]> get3DPointList(List<Double[]> xyList,List<Double[]> yzList,List<Double[]> zxList){
        /**
         *  1. 各自获取x,y,z轴上可能的点
         */
        List<Double[]> xList = new ArrayList<>();

        for (Double[] xy : xyList) {
//            Double[]
        }

        return null;
    }
}
