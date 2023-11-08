package com.java.stuff.leetcode2023.dp_2d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TargetSum {

    int rec(int[] nums, int target, Map<List<Integer>, Integer> cache, int sum, int index) {
        if (index == nums.length)
            if (sum == target)
                return 1;
            else
                return 0;

        if (index > nums.length)
            return 0;

        List<Integer> key = List.of(sum, index);
        if (cache.containsKey(key))
            return cache.get(key);

        int res = 0;
        res += rec(nums, target, cache, sum + nums[index], index + 1);
        res += rec(nums, target, cache, sum - nums[index], index + 1);

        cache.put(key, res);
        return res;
    }

    public int findTargetSumWays(int[] nums, int target) {
        return rec(nums, target, new HashMap<>(), 0, 0);
    }

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        System.out.println(ts.findTargetSumWays(new int[]{1, 2}, 3));
        System.out.println(ts.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(ts.findTargetSumWays(new int[]{1}, 1));


//        Example 1:
//        Input: nums = [1,1,1,1,1], target = 3
//        Output: 5
//        Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
//                -1 + 1 + 1 + 1 + 1 = 3
//                +1 - 1 + 1 + 1 + 1 = 3
//                +1 + 1 - 1 + 1 + 1 = 3
//                +1 + 1 + 1 - 1 + 1 = 3
//                +1 + 1 + 1 + 1 - 1 = 3
//
//        Example 2:
//        Input: nums = [1], target = 1
//        Output: 1
    }

}