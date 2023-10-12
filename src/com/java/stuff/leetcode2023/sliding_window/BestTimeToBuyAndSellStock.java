package com.java.stuff.leetcode2023.sliding_window;

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int max = 0;

        if (prices.length == 1)
            return 0;

        int minLeft = prices[0];
        int i = 0;
        while (++i < prices.length) {
            int right = prices[i];
            int tmp = Math.max(0, right - minLeft);

            if(tmp > max)
                max = tmp;


            if (right < minLeft)
                minLeft = right;
        }

        return max;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock bttb = new BestTimeToBuyAndSellStock();

        System.out.println(bttb.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(bttb.maxProfit(new int[]{7, 6, 4, 3, 1}));

//        Example 1:
//
//        Input: prices = [7,1,5,3,6,4]
//        Output: 5
//        Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//        Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
//        Example 2:
//
//        Input: prices = [7,6,4,3,1]
//        Output: 0
//        Explanation: In this case, no transactions are done and the max profit = 0.
    }


}
