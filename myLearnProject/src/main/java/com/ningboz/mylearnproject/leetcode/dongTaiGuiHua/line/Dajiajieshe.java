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
        System.out.println(robNew());

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
    public static int robNew(){
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
        return robNew(treeNode1);
    }
    public static int robNew(TreeNode root) {
        return diguiRob(root,0,0);
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

    /**
     * 深度优先 搜索
     * @param node 当前节点
     * @param pre1
     * @param pre2
     * @return
     */
    private static int diguiRob(TreeNode node,int pre1,int pre2){
        if(node == null) return pre1;
        // 选当前节点 父节点不可选
        // 不选当前节点
        int cur = Math.max(node.val + pre2,pre1);
        if(node.left!=null) {
            pre1 = cur;
            cur = diguiRob(node.left,cur,pre1);

        }
        if(node.right!=null) cur = diguiRob(node.right,cur,pre1);
        return cur;
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

}
