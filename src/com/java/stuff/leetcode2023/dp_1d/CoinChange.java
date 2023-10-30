package com.java.stuff.leetcode2023.dp_1d;

import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] cache = new int[amount + 1];
        Arrays.fill(cache, amount + 1);
        cache[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int c : coins) {
                if (i - c >= 0) {
                    cache[i] = Math.min(cache[i], 1 + cache[i - c]);
                }
            }
        }

        return cache[amount] != amount + 1 ? cache[amount] : -1;
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();

        System.out.println(cc.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(cc.coinChange(new int[]{2}, 3));
        System.out.println(cc.coinChange(new int[]{1}, 0));
        System.out.println(cc.coinChange(new int[]{186, 419, 83, 408}, 6249));


//        Example 1:
//        Input: coins = [1,2,5], amount = 11
//        Output: 3
//        Explanation: 11 = 5 + 5 + 1
//
//        Example 2:
//        Input: coins = [2], amount = 3
//        Output: -1
//
//        Example 3:
//        Input: coins = [1], amount = 0
//        Output: 0
    }

}
