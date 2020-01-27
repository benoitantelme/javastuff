package com.java.stuff.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ExercisesNine {

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            Integer times = map.getOrDefault(num, 0);
            times +=1;
            if(times > nums.length/2)
                return num;

            map.put(num, times);
        }

        return -1;
    }

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int tmp = 0;

        for(int i = 0; i < nums.length; i++){
            tmp = Math.max(nums[i], tmp+nums[i]);

            if(tmp > max)
                max = tmp;
        }

        return max;
    }

    public static int maxProfit(int[] prices) {
        if(prices.length < 2)
            return 0;

        int min = prices[0];
        int maxDiff = prices[1] - prices[0];

        for(int i = 1; i < prices.length; i++){
            if(prices[i] - min > maxDiff)
                maxDiff = prices[i] - min;

            if(prices[i] < min)
                min = prices[i];
        }

        return Math.max(0, maxDiff);
    }

    public static void main(String args[]) {
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));

        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

        System.out.println(maxProfit(new int[]{1, 2, 4}));

    }
}
