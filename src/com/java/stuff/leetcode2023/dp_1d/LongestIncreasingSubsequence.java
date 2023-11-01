package com.java.stuff.leetcode2023.dp_1d;

import java.util.Arrays;
import java.util.LinkedList;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1)
            return 1;

        int[] cache = new int[nums.length];
        Arrays.fill(cache, 1);
        int max = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] < nums[j])
                    cache[i] = Math.max(cache[i], 1 + cache[j]);

            max = Math.max(max, cache[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();

        System.out.println(lis.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lis.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lis.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
        System.out.println(lis.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9}));
        System.out.println(lis.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));


//        Example 1:
//        Input: nums = [10,9,2,5,3,7,101,18]
//        Output: 4
//        Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//
//        Example 2:
//        Input: nums = [0,1,0,3,2,3]
//        Output: 4
//
//        Example 3:
//        Input: nums = [7,7,7,7,7,7,7]
//        Output: 1
    }

}
