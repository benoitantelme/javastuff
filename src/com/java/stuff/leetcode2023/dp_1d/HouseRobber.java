package com.java.stuff.leetcode2023.dp_1d;

public class HouseRobber {
    
    public int rob(int[] nums) {
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

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();

        System.out.println(hr.rob(new int[]{1, 2, 3, 1}));
        System.out.println(hr.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(hr.rob(new int[]{2, 1, 1, 2}));

//        Example 1:
//        Input: nums = [1,2,3,1]
//        Output: 4
//        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//                Total amount you can rob = 1 + 3 = 4.
//
//        Example 2:
//        Input: nums = [2,7,9,3,1]
//        Output: 12
//        Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//                Total amount you can rob = 2 + 9 + 1 = 12.
    }

}
