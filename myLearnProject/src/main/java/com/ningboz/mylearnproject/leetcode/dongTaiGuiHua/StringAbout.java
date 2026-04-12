package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua;

import com.ningboz.mylearnproject.leetcode.code0722.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class StringAbout {
    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        /**
         *  分析字符串 y 和 他的子序列 x的关系
         *  y可由x随意位置插入若干字符得到
         *
         *  编辑距离
         *
         *
         *  最终解决 和 编辑距离无关 但逻辑比较类似
         *  charArr1[i] == charArr2[j] 时 : dp[i][j] = dp[i-1][j-1]+1
         *  charArr1[i] != charArr2[j] 时 : dp[i][j] = max(dp[i-1][j],dp[i][j-1])
         *
         */
        char[] charArr1 = text1.toCharArray();
        char[] charArr2 = text2.toCharArray();
        boolean isLong = charArr1.length>charArr2.length;
        char[] longCharArr = isLong?charArr1:charArr2;
        char[] shortCharArr = isLong?charArr2:charArr1;

        int last = 0;
        int [] dp = new int[shortCharArr.length+1];
        for (int i = 1; i <= longCharArr.length; i++) {
            for (int j = 0; j < shortCharArr.length + 1; j++) {
                if(j == 0) last = 0;
                else{
                    int cur = dp[j];
                    if(longCharArr[i-1] == shortCharArr[j-1]){
                        dp[j] = last+1;
                    }else{
                        dp[j] = Math.max(dp[j-1],dp[j]);
                    }
                    last = cur;
                }
            }
        }
        return dp[dp.length-1];
    }

    // 所有最短编辑路径
    private static int shortestPath(String word1,String word2){
        boolean is1MoreLength = word1.length()>word2.length();
        String shortStr = is1MoreLength?word2:word1;
        String longStr = is1MoreLength?word1:word2;

        int [] dp = new int[shortStr.length()+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }

        int last = 0;
        for (int i = 1; i <= longStr.length(); i++) {
            for (int j = 0; j <= shortStr.length(); j++) {
                int cur = dp[j];
                if(j == 0) {
                    dp[j] = i ;
                } else {
                    if(longStr.charAt(i-1) == shortStr.charAt(j-1)){
                        dp[j] = last;
                    }else{
                        dp[j] = Math.min((Math.min(last,cur)),dp[j-1]) + 1;
                    }
                }
                last = cur;
            }
        }

        return dp[dp.length-1];
    }

    // 最短编辑距离
    private static int levenshtein2(String word1,String word2){
        boolean is1MoreLength = word1.length()>word2.length();
        String shortStr = is1MoreLength?word2:word1;
        String longStr = is1MoreLength?word1:word2;

        int [] dp = new int[shortStr.length()+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }

        int last = 0;
        for (int i = 1; i <= longStr.length(); i++) {
            for (int j = 0; j <= shortStr.length(); j++) {
                int cur = dp[j];
                if(j == 0) {
                    dp[j] = i ;
                } else {
                    if(longStr.charAt(i-1) == shortStr.charAt(j-1)){
                        dp[j] = last;
                    }else{
                        dp[j] = Math.min((Math.min(last,cur)),dp[j-1]) + 1;
                    }
                }
                last = cur;
            }
        }

        return dp[dp.length-1];
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

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
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
        return dp[row-1][col-1];
    }

    /**
     * 通配符匹配
     *
     * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符序列（包括空字符序列）。
     * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
     */
    public static boolean isMatch(String s, String p) {
        char[] charArray = s.toCharArray();
        char[] pCharArr = p.toCharArray();

//        if(charArray.length == 0){
//            for (char ch : pCharArr) {
//                if(ch!='*') return false;
//            }
//            return true;
//        }
        // 长度一致时 简单匹配即可 必须一一对应
//        if(s.length() == p.length()){
//            for (int i = 0; i < charArray.length; i++) {
//                if(pCharArr[i] != '*' && pCharArr[i] != '?' && pCharArr[i] != charArray[i]) return false;
//            }
//            return true;
//        }

        // 长度不一时，p必定包含* 且

        // tp表示当前*通配符指向
        // tpP:通配符自身所在位置，tpV:通配符目前匹配到的位置
        int i=0,j=0,tpP=-1,tpV=-1;
        while(i<charArray.length && j<pCharArr.length){
            char chP = pCharArr[j];
            if (chP == '*') {
                tpP = j;
                tpV = i;
                j++;
                continue;
            }
            char ch = charArray[i];
            if(chP == '?' || ch==chP){
                i++;
                j++;
                continue;
            }
            // 当前匹配不成功 且前置无通配符
            if(tpP == -1) return false;
            // 不匹配但前置有通配符
            tpV++;
            i = tpV;
            j = tpP+1;
        }
        // 字符串没匹配完时 字符模式后续必须全是*才通过
        if(i<charArray.length && tpP == pCharArr.length-1) return true;
        // 字符模式没匹配完时
        else if(j<pCharArr.length){
            for (;j < pCharArr.length; j++) {
                if(pCharArr[j] != '*') return false;
            }
            return true;
        }
        else if(i == charArray.length && j == pCharArr.length) return true;

        return false;
    }

    public static boolean isMatch2(String s, String p) {
        /**
         * dp[i][j] 是否匹配：
         *      s[i] == p[j] -> dp[i][j] = dp[]
          */
        boolean [][] dp = new boolean[s.length()+1][p.length()+1];
        char[] ch1 = s.toCharArray();
        char[] ch2 = p.toCharArray();
        for (int i = 0; i <= ch1.length; i++) {
            for (int j = 0; j <= ch2.length; j++) {
                if(i == 0 && j == 0)
                    dp[i][j] = true;
                else if(i==0)
                    dp[0][j] = ch2[j-1]=='*' && dp[0][j-1];
                else if (j==0)
                    dp[i][0] = false;
                else{
                    char chS = ch1[i-1];
                    char chP = ch2[j-1];
                    if(chP == '?' ||chP == chS) dp[i][j] = dp[i-1][j-1];
                    else if(chP == '*') {
                        // * 匹配字符 或者不匹配字符
                        dp[i][j] = dp[i-1][j-1] || dp[i][j-1] || dp[i-1][j];
                    }
                }
            }
        }


        return dp[s.length()][p.length()];
    }

    /**
     * 回文子串的数量
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        if(s == null || "".equals(s)) return 0;
        char[] chArr = s.toCharArray();
        int count = 0;
//        List<Integer> lastHuiwenNums = new ArrayList<Integer>(){{add(1);}};
        List<Integer> lastHuiwenNums = new ArrayList<>();
        for (int i = 0; i < chArr.length; i++) {
            char ch = chArr[i];
            count++;
            List<Integer> curHuiwenNums = new ArrayList<Integer>(){{add(1);add(2);}};
            for (Integer lastHuiwenNum : lastHuiwenNums) {
                int index = i-lastHuiwenNum;
                if(index>=0 && ch == chArr[index]){
                    count++;
                    curHuiwenNums.add(lastHuiwenNum+2);
                }
            }
            lastHuiwenNums = curHuiwenNums;

            // 0.回文子串数量 1.当前回文长度
            // dp[i] 前i长度字符串的回文数量
        }
        return count;
    }

    // 查找最长回文子串
    public static String longestPalindrome(String s) {
        if(s == null || s.length()<2) return s;
        char[] chArr = s.toCharArray();
        int maxLength = 1;
        int endIndex = 0;
//        List<Integer> lastHuiwenNums = new ArrayList<Integer>(){{add(1);}};
        List<Integer> lastHuiwenNums = new ArrayList<>();
        for (int i = 0; i < chArr.length; i++) {
            char ch = chArr[i];
            List<Integer> curHuiwenNums = new ArrayList<Integer>(){{add(1);add(2);}};
            for (Integer lastHuiwenNum : lastHuiwenNums) {
                int index = i-lastHuiwenNum;
                if(index>=0 && ch == chArr[index]){
                    curHuiwenNums.add(lastHuiwenNum+2);
                    int huiwenLength = lastHuiwenNum + 1;
                    if(huiwenLength>maxLength){
                        maxLength = huiwenLength;
                        endIndex = i;
                    }
                }
            }
            lastHuiwenNums = curHuiwenNums;

            // 0.回文子串数量 1.当前回文长度
            // dp[i] 前i长度字符串的回文数量
        }

        return s.substring(endIndex-maxLength+1,endIndex+1);
    }
    private static String longestHuiwen(String s){
        char[] chArr = s.toCharArray();
        char[] chArrNew = new char[chArr.length*2+1];

        int max = 0;
        int index = 0;
        for(int i=0;i<chArrNew.length;i++){
            if(i%2==0) chArrNew[i] = '*';
            else chArrNew[i] = chArr[i/2];
        }

        for(int i = 0 ;i<chArrNew.length;i++){
            int startIndex,endIndex;
            for(startIndex = i-1,endIndex = i+1;startIndex>=0&&endIndex<chArrNew.length;startIndex--,endIndex++){
                if(chArrNew[startIndex] == chArrNew[endIndex]);
                else break;
            }

            int curLength = (endIndex-startIndex-1)/2;

            if(curLength>max){
                index = (startIndex+1)/2;
                max = curLength;
            }

        }

        return s.substring(index,index+max);
    }

    // 标准答案
    public static String longestPalindromeFormat(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {

                System.out.print (String.format("当前所在节点:(%s,%s)     ",l,r));
                System.out.print (String.format("推导前置节点:(%s,%s)     ",l+1,r-1));
                if(r - l <= 2) System.out.print("无需推导       ");
                System.out.println();
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }

            }

        }
        return s.substring(maxStart, maxEnd + 1);

    }

    public static void main(String[] args) {
//        System.out.println(levenshtein2("bd", "abcde"));
//        System.out.println(longestCommonSubsequence("bd", "abcde"));
//        System.out.println(isMatch2("ABCDCDEFGHI","*C?EF*I"));
//        System.out.println(countSubstrings("aaa"));
//        System.out.println(longestPalindromeFormat("abababcde"));
//        System.out.println(isMatch2("aa","*"));
//        System.out.println(isMatch2("","*****"));
//        System.out.println(isMatch2("abcabczzzde","*abc???de*"));
//        System.out.println(isMatch2("aa","a"));
        System.out.println(longestHuiwen("abssbc"));
        System.out.println(longestHuiwen("babad"));
    }
}
