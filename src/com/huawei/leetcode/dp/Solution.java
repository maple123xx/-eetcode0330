package com.huawei.leetcode.dp;

public class Solution {
    //518 零钱兑换Ⅱ
    public int change(int amount, int[] coins) {
        if(coins == null )
            return 0;
        int dp[] = new int[amount+1];
        //完全背包问题，用dp记录可以达成的目标的组合数目。
        //dp[i]表示价值为i的金额可被表示的目标组合数目。
        // 设置起始状态
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            // 记录每添加一种面额的零钱，总金额j的变化
            for (int j = coins[i]; j <= amount; j++) {
                // 在上一钟零钱状态的基础上增大
                // 例如对于总额5，当只有面额为1的零钱时，只有一种可能 5x1
                // 当加了面额为2的零钱时，除了原来的那一种可能外
                // 还加上了组合了两块钱的情况，而总额为5是在总额为3的基础上加上两块钱来的
                // 所以就加上此时总额为3的所有组合情况
                dp[j] += dp[j-coins[i]];
            }
        }
        return dp[amount];
    }
}
