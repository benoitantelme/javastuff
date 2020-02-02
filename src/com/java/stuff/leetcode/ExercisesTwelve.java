package com.java.stuff.leetcode;

import java.util.PriorityQueue;

public class ExercisesTwelve {

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (Integer o1, Integer o2) -> -Integer.compare(o1, o2)
        );

        for (int stone : stones)
            maxHeap.add(stone);

        while (maxHeap.size() > 1) {
            Integer first = maxHeap.poll();
            Integer second = maxHeap.poll();

            if (!first.equals(second))
                maxHeap.add(Math.abs(first - second));
        }

        if (maxHeap.isEmpty())
            return 0;
        else
            return maxHeap.poll();
    }

    public static int maxProfit2(int[] prices) {
        int min;
        int max;
        int maxProfit = 0;
        int i = 0;

        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                ++i;
            min = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                ++i;
            max = prices[i];
            maxProfit += max - min;
        }

        return maxProfit;
    }

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int i = 0;

        while (i < prices.length - 1) {
            if (prices[i] < prices[i + 1])
                maxProfit += prices[i + 1] - prices[i];

            i++;
        }

        return maxProfit;
    }



    public static void main(String args[]) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));

        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
