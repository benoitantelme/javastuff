package com.java.stuff.leetcode2023.dp_1d;

import java.util.Arrays;

public class HouseRobberII {

    int rob2(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length > 2) {
            nums[2] = nums[2] + nums[0];
            for (int i = 3; i < nums.length; i++) {
                nums[i] = nums[i] + Math.max(nums[i - 2], nums[i - 3]);
            }
        }

        return Math.max(nums[nums.length - 2], nums[nums.length - 1]);
    }

    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        return Math.max(rob2(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                rob2(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    public static void main(String[] args) {
        HouseRobberII hr2 = new HouseRobberII();

        System.out.println(hr2.rob(new int[]{2, 3, 2}));
        System.out.println(hr2.rob(new int[]{1, 2, 3, 1}));
        System.out.println(hr2.rob(new int[]{1, 2, 3}));
        System.out.println(hr2.rob(new int[]{200, 3, 140, 20, 10}));
        System.out.println(hr2.rob(new int[]{4, 1, 2, 7, 5, 3, 1}));


//        Example 1:
//        Input: nums = [2,3,2]
//        Output: 3
//        Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
//
//        Example 2:
//        Input: nums = [1,2,3,1]
//        Output: 4
//        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//                Total amount you can rob = 1 + 3 = 4.
//
//        Example 3:
//        Input: nums = [1,2,3]
//        Output: 3
    }
}
