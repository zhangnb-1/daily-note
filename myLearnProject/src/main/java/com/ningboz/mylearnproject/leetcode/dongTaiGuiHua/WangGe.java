package com.ningboz.mylearnproject.leetcode.dongTaiGuiHua;

public class WangGe {
    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     *
     * 问总共有多少条不同的路径？
     * @param m
     * @param n
     * @return
     */
    // 用统计数学式子
    public static int uniquePaths(int m, int n) {
        return (int)calculateCombination(m+n-2,n-1);
    }

    public static int uniquePathsDongtaiguihua(int m, int n) {
        int min = m>n?n:m;
        int max = m>n?m:n;
        int [] dp = new int[min];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < min; j++) {
                if(i==0 || j==0) dp[j] = 1;
                else dp[j] = dp[j-1]+dp[j];
            }
        }
        return dp[min-1];
    }

    public static long calculateCombination(int n, int m) {
        // 优化：利用对称性 C(n, m) == C(n, n-m)
        // 如果 m > n/2，则计算 C(n, n-m) 以减少循环次数
        if (m > n - m) {
            m = n - m;
        }

        long result = 1;

        // 采用边乘边除的方法，防止中间结果溢出
        // C(n, m) = (n * (n-1) * ... * (n-m+1)) / (m * (m-1) * ... * 1)
        for (int i = 0; i < m; i++) {
            result = result * (n - i) / (i + 1);
        }

        return result;
    }

    /**
     * 不同路径 II
     * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。
     *
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
     *
     * 返回机器人能够到达右下角的不同路径数量。
     *
     * 测试用例保证答案小于等于 2 * 109。
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int [] dp = new int[n+1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i == 0 || j==0) continue;
                if(i == 1 && j == 1) {
                    dp[1] = 1;
                    continue;
                }
                dp[j] = obstacleGrid[i - 1][j - 1] == 1 ?0:(dp[j-1]+dp[j]);
            }

            }
        return dp[n];
    }

    /**
     * 在二维网格 grid 上，有 4 种类型的方格：
     *
     * 1 表示起始方格。且只有一个起始方格。
     * 2 表示结束方格，且只有一个结束方格。
     * 0 表示我们可以走过的空方格。
     * -1 表示我们无法跨越的障碍。
     * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
     *
     * 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
     */
    public int uniquePathsIII(int[][] grid) {
        int xLength = grid.length;
        int yLength = grid[0].length;
        int startX=0,startY=0,endX=0,endY=0;
        boolean findX = false,findY = false;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if(findX&&findY) break;
                if(grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }else if(grid[i][j] == 2){
                    endX = i;
                    endY = j;
                }
            }
            if(findX&&findY) break;
        }
        return 0;
    }

    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     */
    // 动态规划边界问题处理：边界规则单独处理，最简单且直观
    public static int minPathSumNew(int[][] grid) {
        int xLength = grid.length;
        int yLength = grid[0].length;
        int [] dp = new int[yLength];
        for (int i = 0; i < yLength; i++) {
            dp[i] = grid[0][i] + ((i==0)?0:dp[i-1]);
        }
        for (int i = 1; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                int left = j==0?0:dp[j-1];
                int top = dp[j];
                int before = j==0 ? top : Math.min(left, top);
                dp[j] = before + grid[i][j];
            }
        }
        return dp[dp.length-1];
    }
    public static int minPathSum(int[][] grid) {
        int xLength = grid.length;
        int yLength = grid[0].length;
        int [] dp = new int[yLength];
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                int left = j==0?0:dp[j-1];
                int top = i==0?0:dp[j];
                int before = i==0||j==0 ? Math.max(left, top) : Math.min(left, top);
                dp[j] = before + grid[i][j];

//                int left = j-1<0?0:dp[j-1];
//                int top = i-1<0?0:dp[j];
//                int before = left == 0 || top == 0 ? Math.max(left, top) : Math.min(left, top);
//                dp[j] = before + grid[i][j];
            }
        }
        return dp[dp.length-1];
    }

    /**
     * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
     * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
     * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）
     * 。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
     */
    public static int minFallingPathSum(int[][] matrix) {
        /**
         *  1. dp
         */
        if(matrix[0].length == 1){
            // 只有一列 直接返回数组的和
            int result = 0;
            for (int[] ints : matrix) {
                result += ints[0];
            }
            return result;
        }
        int dp [] = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            dp[i] = matrix[0][i];
        }

        int left = 0 ;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int top = dp[j];
                if(j==0) {
                    dp[j] = matrix[i][j] + Math.min(top,dp[j+1]);
                }
                else if(j == dp.length-1) {
                    dp[j] = matrix[i][j] + Math.min(left,top);
                }
                else{
                    dp[j] = matrix[i][j] + Math.min(Math.min(left,top),dp[j+1]);
                }
                left = top;
                if(i == matrix.length-1){
                    min = Math.min(dp[j],min);
                }
            }
        }
        // dp[i] = Math.min(dp[i-1],dp[i],dp[i+1])
        return min;
    }

    /*
        给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
        对于每个单元格，你可以往上，下，左，右四个方向移动。
        不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     */
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int res = 1;
        int[][] memo = new int[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                res = Math.max(res,dfs(matrix,i,j,memo));
            }
        }
        return res;
    }

    /**
     * 带缓存记忆的深度搜索 由小找大
     * @param matrix
     * @param row
     * @param col
     * @param memo
     * @return
     */
    private static int [][]fangxiang = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    private static int dfs(int[][] matrix,int row,int col,int[][] memo){
        if(memo[row][col]!=0) return memo[row][col];
        int res = 1;
        int deep = 0;
        // 上下左右
        for (int[] ints : fangxiang) {
            int newRow = row+ints[0];
            int newCol = col+ints[1];
            if(newRow<0||newRow>matrix.length-1 || newCol<0||newCol>matrix[0].length-1)
                continue;
            if(matrix[newRow][newCol]>matrix[row][col])
                deep=Math.max(dfs(matrix,newRow,newCol,memo),deep);
        }
        res+=deep;
        memo[row][col] = res;
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(uniquePathsDongtaiguihua(10, 10));
//        System.out.println(uniquePathsWithObstacles(new int[][]{{1}}));
//        System.out.println(minPathSum(new int[][]{{1,5,3},{6,3,4},{2,8,2}}));
//        System.out.println(minFallingPathSum(new int[][]{{1,5,3},{6,3,4},{2,8,2}}));
        System.out.println(longestIncreasingPath(new int[][]{
                {3,4,5},
                {3,2,6},
                {2,2,1},
        }));
    }
}
