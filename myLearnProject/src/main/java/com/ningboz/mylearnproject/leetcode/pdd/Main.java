package com.ningboz.mylearnproject.leetcode.pdd;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * 第一题:直播巡检
     * 题目描述
     * AK机负责直播平台的首页直播排行榜的巡检工作。
     * 今天平台一共收到n条候选直播内容，按输入顺序依次编号为1到n。
     * 每条直播内容都属于某个主播。为了避免同一主播同时占据多个排行榜位置，正式生成榜单前，
     * 平台会先做一次主播去重：对于同一个主播，只保留该主播”最优”的一条直播内容进入选榜单，
     * 同一主播的其他直播内容会在这一步直接被淘汰，不再参与后续排序。
     * 因此，最终直播排行榜中每个主播至多出现一次，榜单条数恰好等于不同主播的个数。
     * 判断一条直播内容是否更优，按以下顺序比较:
     * 1.点赞数更多者更优。
     * 2.如果点赞数相同，则评论数更多者更优。
     * 3.如果点赞数和评论数都相同，则发布时间更早者更优。
     * 4.如果以上三项仍然完全相同，则原始编号更小者更优。
     * 完成主播去重后，再对所有保留下来的直播内容按完全相同的规则排序，得到最终直播排行榜。
     * 现在给出q个查询。每次查询一条直播内容的最终结果: 如果这条直播内容最终出现在排行榜中，输出它的排名; 如果它在主播去重阶段已经被淘汰，输出0。
     * 输入描述
     * 第一行输入两个整数n,q(1<n,q<2x105)，分别表示候选直播内容数和查询条数。
     * 接下来n行，第i行输入四个整数ui, ai, bi,ti(1< ui <109,0<ai, bi,ti<109), 表示第i条直播内容所属主播编号、点赞数、评论数和发布时间。其中t越小表示发布时间越早。
     * 接下来g行，每行输入一个整数id(1<id<n)，表示询问编号为id的直播内容最终排名。
     * 输出描述
     * 输出q行，每行一个整数。若对应直播条目最终上榜，输出其排名(排名从 1开始计数)；否则输出0。
     * 样例1
     * 输入
     * 5 4
     * 1 100 20 5
     * 2 100 15 8
     * 1 100 25 4
     * 3 100 18 6
     * 2  100 20 3
     * 1
     * 2
     * 3
     * 5
     * 输出
     * 0
     * 0
     * 1
     * 2
     * 样例解释
     * 主播1有第1，3两条直播，其中第3条更优（评论数25>20)。主播2有第2，5两条直播，其中第5条更优（评论数20>15)。主
     * 播3只有第4条直播。因此候选榜单只剩第3,4,5条,排序后顺序为3→>5→4。
     */

    private static void zhiboXunjian(){

    }

    /**
     * 1.
     * 多多的数字组合
     * 多多君最近在研究某种数字组合：
     * 定义为：每个数字的十进制表示中(0~9)，每个数位各不相同且各个数位之和等于N。
     * 满足条件的数字可能很多，找到其中的最小值即可。
     * 多多君还有很多研究课题，于是多多君找到了你--未来的计算机科学家寻求帮助。
     *
     * 数据范围：
     * 1≤n≤1000
     * 进阶：空间复杂度 O(1)，时间复杂度 O(n)
     */
    private static int method(int N){
        if(N>45) return -1;
        if(N>44) return 123456789;
        if(N>42) return 3456789+(N-42)*10000000;
        if(N>39) return 456789+(N-39)*1000000;
        if(N>35) return 56789+(N-35)*100000;
        if(N>30) return 6789+(N-30)*10000;
        if(N>24) return 789+(N-24)*1000;
        if(N>17) return 89+(N-17)*100;
        if(N>9) return 9+(N-9)*10;
        return N;
    }

    /**
     * 2.
     * 多多的字符变换
     * 多多君最近在研究字符串之间的变换，可以对字符串进行若干次变换操作:
     *
     * 交换任意两个相邻的字符，代价为0。
     * 将任意一个字符a修改成字符b，代价为 |a - b|（绝对值）。
     * 现在有两个长度相同的字符串X和Y，多多君想知道，如果要将X和Y变成两个一样的字符串，需要的最少的代价之和是多少。
     */
    private void method2(char[] chArr1 , char[] chArr2){
        int [] chArr = new int[26];
        for (char ch : chArr1) {
            chArr[ch-'a'] += 1;
        }

        for (char ch : chArr2) {
            chArr[ch-'a'] -= 1;
        }

        int result = 0;
        boolean isZheng = true;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < chArr.length; i++) {
            int val = chArr[i];
            if(val == 0) continue;
            if(list.isEmpty()){
                list.add(new int[]{i,val});
                isZheng = val>0;
                continue;
            }
            if(val<0 && !isZheng || (val>0 && isZheng)){
                list.add(new int[]{i,val});
            }
            else {
                for (int[] ints : list) {
                    int daijia = i-ints[0];
//                    if(Math.abs(ints[1])>= )
                }

            }
        }

    }

    /**
     * 4.
     * 多多的骰子组合
     * 多多君拼团购买了N个骰子，为了方便后面进行活动，多多君需要将这些骰子进行分类。
     */
    private static void getTypeNum(int[][] shaiziArr){
        Map<Integer,Integer> map = new HashMap<>();
        /**
         * 1. 1面的对面    5类
         * 2. 除去前两面的最小面及其对面 3类
         * 3.
         */
        // 上下左右前后
        /**
         * 三个维度的旋转
         * 1. 上下不变：左后右前 - [2,5,3,4]
         * 2. 左右不变：前上后下 - [4.0.5.1]
         * 3. 前后不变：左上右下 - [2,0,3,1]
          */

        for (int[] ints : shaiziArr) {
            int num0 = zhishuArr[ints[0]-1];
            int num1 = zhishuArr[ints[1]-1];
            int num2 = zhishuArr[ints[2]-1];
            int num3 = zhishuArr[ints[3]-1];
            int num4 = zhishuArr[ints[4]-1];
            int num5 = zhishuArr[ints[5]-1];

            int res = num0 * num2 * num4
                    + num0 * num2 * num5
                    + num0 * num3 * num4
                    + num0 * num3 * num5
                    + num1 * num2 * num4
                    + num1 * num2 * num5
                    + num1 * num3 * num4
                    + num1 * num3 * num5;

            map.put(res,map.getOrDefault(res,0)+1);
        }

        System.out.println(map.size());
        List<Integer> collect = map.values().stream().sorted(Integer::compareTo).collect(Collectors.toList());
        Collections.reverse(collect);
        System.out.println(collect.stream().map(num->String.valueOf(num)).collect(Collectors.joining(" ")));
    }
    private static int[][] arr = new int[][]{{2,5,3,4},{4,0,5,1},{2,0,3,1}};

    private static int [] zhishuArr = new int[]{2,3,5,7,11,13};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int step  = 0;
        int num = 0;
        int [][] arr = null;
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String sin = in.nextLine();
            if(step == 0){
                num = Integer.valueOf(sin);
                arr = new int[num][6];
                step = 1;
            }else if(step == 1){
                String strArr[] = sin.split(" ");
                for(int i=0;i<6;i++){
                    arr[arr.length-num][i] = Integer.valueOf(strArr[i]);
                }
                num--;
                if(num == 0){
                    getTypeNum(arr);
                    step = 0;
                }
            }
        }
    }
}
