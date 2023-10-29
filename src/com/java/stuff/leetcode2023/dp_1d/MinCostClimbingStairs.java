package com.java.stuff.leetcode2023.dp_1d;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0)
            return 0;

        if (cost.length == 1)
            return cost[0];

        for (int i = cost.length - 3; i >= 0; i--)
            cost[i] = cost[i] + Math.min(cost[i + 1], cost[i + 2]);

        return Math.min(cost[0], cost[1]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs mc = new MinCostClimbingStairs();

        System.out.println(mc.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(mc.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));


//        Example 1:
//        Input: cost = [10,15,20]
//        Output: 15
//        Explanation: You will start at index 1.
//                - Pay 15 and climb two steps to reach the top.
//        The total cost is 15.
//
//        Example 2:
//        Input: cost = [1,100,1,1,1,100,1,1,100,1]
//        Output: 6
//        Explanation: You will start at index 0.
//                - Pay 1 and climb two steps to reach index 2.
//                - Pay 1 and climb two steps to reach index 4.
//                - Pay 1 and climb two steps to reach index 6.
//                - Pay 1 and climb one step to reach index 7.
//                - Pay 1 and climb two steps to reach index 9.
//                - Pay 1 and climb one step to reach the top.
//        The total cost is 6.
    }

    int[] min;

    void rec(int[] cost, int index, int[] min) {
        if (index == cost.length + 1)
            return;

        min[index] = Math.min(cost[index - 2] + min[index - 2], cost[index - 1] + min[index - 1]);
        rec(cost, index + 1, min);
    }

    public int minCostClimbingStairsRec(int[] cost) {
        if (cost.length == 0)
            return 0;

        if (cost.length == 1)
            return cost[0];

        min = new int[cost.length + 1];
        rec(cost, 2, min);
        return min[cost.length];
    }

}
