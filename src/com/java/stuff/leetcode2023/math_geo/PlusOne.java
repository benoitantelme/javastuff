package com.java.stuff.leetcode2023.math_geo;

import java.util.Arrays;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int[] result;
        int index = digits.length;
        for (int i = digits.length - 1; i >= 0; i--)
            if (digits[i] == 9 && index == i + 1)
                index--;

        if (index == 0) {
            result = new int[digits.length + 1];
            result[0] = 1;
        } else {
            result = digits;
            result[index - 1]++;
            for (int i = index; i < digits.length; i++)
                result[i] = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        PlusOne po = new PlusOne();

        System.out.println(Arrays.toString(po.plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(po.plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(po.plusOne(new int[]{9})));
        System.out.println(Arrays.toString(po.plusOne(new int[]{1, 9})));
        System.out.println(Arrays.toString(po.plusOne(new int[]{9, 9})));
        System.out.println(Arrays.toString(po.plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})));

//        Example 1:
//        Input: digits = [1,2,3]
//        Output: [1,2,4]
//        Explanation: The array represents the integer 123.
//        Incrementing by one gives 123 + 1 = 124.
//        Thus, the result should be [1,2,4].
//
//        Example 2:
//        Input: digits = [4,3,2,1]
//        Output: [4,3,2,2]
//        Explanation: The array represents the integer 4321.
//        Incrementing by one gives 4321 + 1 = 4322.
//        Thus, the result should be [4,3,2,2].
//
//        Example 3:
//        Input: digits = [9]
//        Output: [1,0]
//        Explanation: The array represents the integer 9.
//        Incrementing by one gives 9 + 1 = 10.
//        Thus, the result should be [1,0].
    }


}
