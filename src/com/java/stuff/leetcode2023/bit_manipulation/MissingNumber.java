package com.java.stuff.leetcode2023.bit_manipulation;

public class MissingNumber {

    public int missingNumber(int[] nums) {
        int res = 0;

        for(int i = 0 ; i <= nums.length; i++)
            res ^= i;

        for(int n : nums)
            res ^= n;

        return res;
    }

    public static void main(String[] args) {
        MissingNumber mn = new MissingNumber();

        System.out.println(mn.missingNumber(new int[]{3, 0, 1}));
        System.out.println(mn.missingNumber(new int[]{0, 1}));
        System.out.println(mn.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));

//        Example 1:
//        Input: nums = [3,0,1]
//        Output: 2
//        Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
//
//        Example 2:
//        Input: nums = [0,1]
//        Output: 2
//        Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
//
//        Example 3:
//        Input: nums = [9,6,4,2,3,5,7,0,1]
//        Output: 8
//        Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
    }

}
