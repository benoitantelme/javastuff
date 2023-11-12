package com.java.stuff.leetcode2023.dp_2d;

import java.util.*;
import java.util.stream.Collectors;

public class BurstBalloons {

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

    public int maxCoins(int[] nums) {
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
