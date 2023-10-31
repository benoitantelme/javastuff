package com.java.stuff.leetcode2023.dp_1d;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];

        int res = nums[0];
        int max = 1;
        int min = 1;

        for (int n : nums) {
            int tmp = max * n;
            max = Math.max(n, Math.max(tmp, min * n));
            min = Math.min(n, Math.min(tmp, min * n));
            res = Math.max(res, max);
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumProductSubarray mps = new MaximumProductSubarray();

        System.out.println(mps.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(mps.maxProduct(new int[]{-2, 0, -1}));
        System.out.println(mps.maxProduct(new int[]{-2}));
        System.out.println(mps.maxProduct(new int[]{0, 2}));
        System.out.println(mps.maxProduct(new int[]{1, -2, 1, -2, 2}));


//        Example 1:
//        Input: nums = [2,3,-2,4]
//        Output: 6
//        Explanation: [2,3] has the largest product 6.
//
//        Example 2:
//        Input: nums = [-2,0,-1]
//        Output: 0
//        Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
    }

}