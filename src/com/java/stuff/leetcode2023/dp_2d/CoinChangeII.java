package com.java.stuff.leetcode2023.dp_2d;

public class CoinChangeII {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (coin <= i) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        return dp[amount];
    }


    public static void main(String[] args) {
        CoinChangeII cc = new CoinChangeII();

        System.out.println(cc.change(5, new int[]{1, 2, 5}));
        System.out.println(cc.change(3, new int[]{2}));
        System.out.println(cc.change(10, new int[]{10}));

//        Example 1:
//        Input: amount = 5, coins = [1,2,5]
//        Output: 4
//        Explanation: there are four ways to make up the amount:
//        5=5
//        5=2+2+1
//        5=2+1+1+1
//        5=1+1+1+1+1
//
//        Example 2:
//        Input: amount = 3, coins = [2]
//        Output: 0
//        Explanation: the amount of 3 cannot be made up just with coins of 2.
//
//        Example 3:
//        Input: amount = 10, coins = [10]
//        Output: 1
    }

    int rec(int left, int index, int[] coins, Integer[][] cache) {
        if (left < 0) {
            return 0;
        }
        if (left == 0) {
            return 1;
        }

        if (cache[left][index] != null)
            return cache[left][index];

        int value = 0;
        for (int i = index; i < coins.length; i++)
            value += rec(left - coins[i], i, coins, cache);

        cache[left][index] = value;
        return value;
    }

    public int changeRec(int amount, int[] coins) {
        Integer[][] cache = new Integer[amount + 1][coins.length];
        rec(amount, 0, coins, cache);
        return rec(amount, 0, coins, cache);
    }

}
