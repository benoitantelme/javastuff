package com.java.stuff.leetcode2023.dp_2d;

import java.util.*;
import java.util.stream.Collectors;

public class BurstBalloons {

    int rec(int[] nums, int left, int right, int[][] cache) {
        if (left + 1 == right)
            return 0;

        if (cache[left][right] > 0)
            return cache[left][right];

        int res = 0;
        for (int i = left + 1; i < right; i++)
            // bursting i last,
            res = Math.max(res,
                    nums[left] * nums[i] * nums[right]
                            + rec(nums, left, i, cache)
                            + rec(nums, i, right, cache));

        cache[left][right] = res;
        return res;
    }


    public int maxCoins(int[] nums) {
        // padd nums with 1 before and after to simplify while multiplying
        int[] padded = new int[nums.length + 2];
        padded[0] = 1;
        padded[padded.length - 1] = 1;
        for (int i = 0; i < nums.length; i++)
            padded[i + 1] = nums[i];

        int[][] cache = new int[padded.length][padded.length];
        return rec(padded, 0, padded.length - 1, cache);
    }

    public static void main(String[] args) {
        BurstBalloons bb = new BurstBalloons();

        System.out.println(bb.maxCoins(new int[]{3, 1, 5, 8}));
        System.out.println(bb.maxCoins(new int[]{1, 5}));
        System.out.println(bb.maxCoins(new int[]{7, 9, 8, 0, 7, 1, 3, 5, 5, 2, 3}));


//        Example 1:
//        Input: nums = [3,1,5,8]
//        Output: 167
//        Explanation:
//        nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//        coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
//
//        Example 2:
//        Input: nums = [1,5]
//        Output: 10
    }

    public int maxCoinsTle(int[] nums) {
        return recTle(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, 0);
    }

    int recTle(List<Integer> nums, int i, int count) {
        int length = nums.size();
        if (length == 0)
            return count;

        int res = count;

        // base case
        int before = i > 0 ? nums.get(i - 1) : 1;
        int after = i < nums.size() - 1 ? nums.get(i + 1) : 1;
        int mult = nums.get(i) * before * after;
        List<Integer> copy = new ArrayList<>(nums);
        copy.remove(i);
        res += recTle(copy, 0, mult);

        // check others
        if (i < length - 1)
            res = Math.max(res, recTle(nums, i + 1, count));

        return res;
    }

}
