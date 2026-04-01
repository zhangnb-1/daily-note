package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua;

/**
 * @ClassName: BeiBao
 * @author: Znb
 * @date: 2025/7/23
 * @description:
 */
public class BeiBao {
    public static void main(String[] args) {
        beibao01Test();
    }

    public static void beibao01Test(){
//        int [] val = {4,2,3,4,5};
//        int [] weight = {7,3,4,5,8};
//        int pakWeight = 20;

//        int [] val = {1,3,4};
//        int [] weight = {15,20,30};

        int [] weight = {2,3,4};
        int [] val = {3,4,5};
        int pakWeight = 20;
        System.out.println(beibao_1(val, weight, pakWeight));
    }

    // 01背包二维数组 压缩时间
    public static int beibao01_2(int val[], int weight[], int pakWeight){
        int [][] dp = new int[val.length+1][pakWeight+1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int w = weight[i - 1];
                if(j<w){
                    // 当前容量装不下 当前物品
                    dp[i][j] = dp[i-1][j];
                }else{
                    int v = val[i-1];
                    int i1 = dp[i - 1][j];
                    int i2 = dp[i - 1][j - w] + v;
                    dp[i][j] = Math.max(i1, i2);
                }
            }
        }

        return dp[val.length][pakWeight];
    }

    // 01背包一维数组 压缩空间
    public static int beibao01_1(int val[], int weight[], int pakWeight){
        int [] dp = new int[pakWeight+1];
        for (int i = 0; i < val.length; i++) {
            for (int curPakWeight = pakWeight; curPakWeight >= 1; curPakWeight--) {
                int w = weight[i];
                if(curPakWeight>=w){
                    // 装不下当前物品就不做处理 装得下再轮询修改
                    int v = val[i];
                    int i1 = dp[curPakWeight];
                    int i2 = dp[curPakWeight - w] + v;
                    dp[curPakWeight] = Math.max(i1, i2);
                }else break;
            }
        }

        return dp[pakWeight];
    }

    // 完全背包一维数组 压缩时间
    //

    /*
        01背包 -》 完全背包 : 为什么只需要调换背包容量的遍历顺序即可
        状态转换：01背包 当前的物品拿完之后就没了，所以拿完之后 物品只剩之前的，背包容量只剩总容量-当前物品容量
        所以 物品层面退回上一层，背包容量退回剩余容量
        不同的是 完全背包每个物品的数量是无限的 ，拿过当前物品后 当前物品仍然可以拿，所以物品层面不退回上一层 只退回背包容量到剩余容量
     */
    public static int beibao_1(int val[],int weight[],int pakWeight){
        int [] dp = new int[pakWeight+1];
        for (int i = 0; i < val.length; i++) {
            for (int curPakWeight = 1; curPakWeight <= pakWeight; curPakWeight++) {
                int w = weight[i];
                if(curPakWeight>=w){
                    // 装不下当前物品就不做处理 装得下再轮询修改
                    int v = val[i];
                    int i1 = dp[curPakWeight];
                    int i2 = dp[curPakWeight - w] + v;
                    dp[curPakWeight] = Math.max(i1, i2);
                }
            }
        }

        return dp[pakWeight];
    }


    /**
     * 变种背包问题 每个物品除了重量、价值外 再加上数量
     * @param val
     * @param weight
     * @param num
     * @param pakWeight
     * @return
     */
    public static int beibao_1(int val[],int weight[],int num[],int pakWeight){

        int [] dp = new int[pakWeight+1];
        for (int i = 0; i < val.length; i++) {
            for (int curPakWeight = 1; curPakWeight <= pakWeight; curPakWeight++) {
                int w = weight[i];
                if(curPakWeight>=w){
                    // 装不下当前物品就不做处理 装得下再轮询修改
                    int v = val[i];
                    int i1 = dp[curPakWeight];
                    int i2 = dp[curPakWeight - w] + v;
                    dp[curPakWeight] = Math.max(i1, i2);
                }
            }
        }

        return dp[pakWeight];
    }
}
