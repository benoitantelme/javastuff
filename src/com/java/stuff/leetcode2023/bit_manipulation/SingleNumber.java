package com.java.stuff.leetcode2023.bit_manipulation;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int res = 0;
        for(int n : nums)
            res ^= n;

        return res;
    }

    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();

        System.out.println(sn.singleNumber(new int[]{2,2,1}));
        System.out.println(sn.singleNumber(new int[]{4,1,2,1,2}));
        System.out.println(sn.singleNumber(new int[]{1}));

//        Example 1:
//        Input: nums = [2,2,1]
//        Output: 1
//
//        Example 2:
//        Input: nums = [4,1,2,1,2]
//        Output: 4
//
//        Example 3:
//        Input: nums = [1]
//        Output: 1
    }

}
