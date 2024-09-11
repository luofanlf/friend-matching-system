package com.luofan.usercenter.utils;

import java.util.List;

public class AlgorithmUtils {
    public static int minDistance(List<String> tagList1, List<String> tagList2) {
        int m = tagList1.size();
        int n = tagList2.size();

        // 创建二维数组保存动态规划的结果
        int[][] dp = new int[m + 1][n + 1];

        // 初始化第一行和第一列
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // 从 word1 的前 i 个字符变为空需要 i 步
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // 从空变为 word2 的前 j 个字符需要 j 步
        }

        // 填充动态规划表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (tagList1.get(i - 1).equals(tagList2.get(j - 1))) {
                    // 如果当前字符相等，不需要编辑
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 取插入、删除、替换中的最小值加 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], // 替换
                            Math.min(dp[i - 1][j], // 删除
                                    dp[i][j - 1])); // 插入
                    dp[i][j] += 1;
                }
            }
        }

        // 返回结果：最小编辑距离
        return dp[m][n];
    }
}
