package com.java.stuff.leetcode2023.dp_2d;

import java.util.HashMap;
import java.util.Map;

public class BestTimeBuySellStockWithCooldown {


    public int maxProfit(int[] prices) {
        Map<String, Integer> cache = new HashMap<>();
        return rec(prices, cache, 0, true);
    }

    int rec(int[] prices, Map<String, Integer> cache, int index, boolean bos) {
        if (index >= prices.length)
            return 0;

        String key = index + "" + bos;
        if (cache.containsKey(key))
            return cache.get(key);

        int cooldown = rec(prices, cache, index + 1, bos);
        int order;
        if (bos)
            order = rec(prices, cache, index + 1, !bos) - prices[index];
        else
            order = rec(prices, cache, index + 2, !bos) + prices[index];

        cache.put(key, Math.max(cooldown, order));
        return cache.get(key);
    }

    public static void main(String[] args) {
        BestTimeBuySellStockWithCooldown bs = new BestTimeBuySellStockWithCooldown();

        System.out.println(bs.maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(bs.maxProfit(new int[]{1}));

//        Example 1:
//        Input: prices = [1,2,3,0,2]
//        Output: 3
//        Explanation: transactions = [buy, sell, cooldown, buy, sell]
//
//        Example 2:
//        Input: prices = [1]
//        Output: 0
    }


    public int maxProfitBottomUpIsh(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int[] cache = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            if (i == 0)
                cache[0] = 0;
            else if (i == 1)
                cache[1] = Math.max(prices[1] - prices[0], 0);
            else {
                cache[i] = cache[i - 1];

                for (int j = 0; j < i; j++) {
                    int prev = j >= 2 ? cache[j - 2] : 0;
                    cache[i] = Math.max(cache[i], prev + prices[i] - prices[j]);
                }
            }
        }

        return cache[prices.length - 1];
    }

}
