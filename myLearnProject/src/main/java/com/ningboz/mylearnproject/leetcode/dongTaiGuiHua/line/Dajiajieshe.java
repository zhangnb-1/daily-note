package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua.line;

import java.util.*;

/**
 * @ClassName: Dajiajieshe
 * @author: Znb
 * @date: 2026/4/2
 * @description:
 */
public class Dajiajieshe {
    public static void main(String[] args) {
//        System.out.println(rob2(new int[]{2, 3, 2}));
        TreeNode root = getRoot();
        System.out.println(robNew(root));

    }


    /**
     * LCR 089. 打家劫舍
     * 中等
     * 相关标签
     * premium lock icon
     * 相关企业
     * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     *
     * 输入：nums = [2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    public int rob(int[] nums) {
        // dpDo 打劫第i家的最大金额，不打劫i家的最大金额
        int dajieLast=0,noDajieLast=0;
        for (int num : nums) {
            int noDajie = Math.max(dajieLast,noDajieLast);
            dajieLast = noDajieLast+num;
            noDajieLast = noDajie;
        }

        return Math.max(dajieLast,noDajieLast);
    }

    // 标准答案
    public int robNew(int[] nums) {
        // pre1 偷了上家的最大值 ， pre2 偷了上上家的最大值
        int pre1 = 0, pre2 = 0;
        for (int num : nums) {
            int cur = Math.max(pre2+num,pre1);
            pre2=pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     * 一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈
     * ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     */
    public static int rob2(int[] nums) {
        // dp[i][j] 表示从i到j无环的最大打劫值

        int length = nums.length;
        return Math.max(robRange(nums,0, length -2),robRange(nums,1, length -3));
    }

    private static int robRange(int[] nums,int start,int end) {
        // pre1 偷了上家的最大值 ， pre2 偷了上上家的最大值
        int pre1 = 0, pre2 = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(pre2+nums[i],pre1);
            pre2=pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     *小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     *
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     *
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     */

    public int rob(TreeNode root) {
        // 层序遍历
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int levelCount = 1;
        while(!queue.isEmpty()){
            int val = 0;
            int curLevelCount = 0;
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.remove();
                val+= node.val;
                if(node.left!=null) {
                    queue.add(node.left);
                    curLevelCount++;
                }
                if(node.right!=null) {
                    queue.add(node.right);
                    curLevelCount++;
                }
            }
            list.add(val);
            levelCount = curLevelCount;
        }

        int[] ints = new int[list.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = list.get(i);
        }
        return rob(ints);
    }

    // 把tree展开为数组
    public static TreeNode getRoot(){
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(1);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode5;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode5.right = treeNode6;
        return treeNode1;
    }
    public static int robNew(TreeNode root) {
        int[] rob = diguiRob(root);
        return Math.max(rob[0],rob[1]);
    }

    /**
     *
     * @param node
     * @param flag 当前node节点是否可选
     * @return
     */
    private static int diguiRob(TreeNode node,boolean flag){
        if(flag){
            // 可选
//            选
            int xuan = node.val + (node.left == null ? 0 : diguiRob(node.left, false)) + (node.right == null ? 0 : diguiRob(node.right, false));
            int noXuan = (node.left == null ? 0 : diguiRob(node.left, true)) +(node.right == null ? 0 : diguiRob(node.right, true));
            return Math.max(xuan,noXuan);
        }else {
            // 不可选
            return (node.left == null ? 0 : diguiRob(node.left, true)) +(node.right == null ? 0 : diguiRob(node.right, true));
        }
    }

    // 树状动态规划
    private static int[] diguiRob(TreeNode node){
        int[] result = {0, 0};
        if(node == null) return result;

        int[] left = diguiRob(node.left);
        int[] right = diguiRob(node.right);

        result[0] = node.val + left[1] + right[1];
        result[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        return result;
    }

    // 空间优化版
    static int yes,no;
    private static void diguiRob2(TreeNode root){
        if(root==null){
            yes=0;
            no=0;
            return ;
        }
        int y=root.val;
        int n=0;
        diguiRob2(root.left);
        y+=no;
        n+=Math.max(no,yes);
        diguiRob2(root.right);
        y+=no;
        n+=Math.max(no,yes);
        yes=y;
        no=n;
    }


      public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    /** 最小化最大值 : 多个单组group 每个group里面有个最大值， 求所有group最大值里 最小的那个值
     *      现实含义：多种可能，每种可能里有多个缺点，最后选一种可能，其中最大的缺点值最小，主打保证一个均衡
     *  反过来最大化最小：
     *      现实含义： 一块木头做成木桶，在特定要求下，有多种裁剪方法，找到一种裁剪方法 使木桶的短板尽量长
     *
     * leetcode : 2560 打家劫舍4
     * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
     *
     * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
     *
     * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
     *
     * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
     *
     * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
     *
     * 返回小偷的 最小 窃取能力。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,3,5,9], k = 2
     * 输出：5
     * 解释：
     * 小偷窃取至少 2 间房屋，共有 3 种方式：
     * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
     * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
     * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
     * 因此，返回 min(5, 9, 9) = 5 。
     * 示例 2：
     *
     * 输入：nums = [2,7,9,3,1], k = 2
     * 输出：2
     * 解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
     */
    public int minCapability(int[] nums, int k) {
        // dp[i][j] 总共 i 家 偷 j家的 最大值
        // dp[i][j] = max(num[j] + dp[i-2][j-1], dp[i-1][j])
        int[][] dp = new int[nums.length][];

        // 从大到小排nums里的index
        return 0;
    }

}
