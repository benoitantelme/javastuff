package com.java.stuff.leetcode2023.bit_manipulation;

public class SumOfTwoIntegers {

    public int getSum(int a, int b) {
        while (b != 0) {
            int tmp = (a & b) << 1;
            a = (a ^ b);
            b = tmp;
        }
        return a;
    }

    public static void main(String[] args) {
        SumOfTwoIntegers soti = new SumOfTwoIntegers();

        System.out.println(soti.getSum(1, 2));
        System.out.println(soti.getSum(2, 3));
        System.out.println(soti.getSum(1, 3));

//        Example 1:
//        Input: a = 1, b = 2
//        Output: 3
//
//        Example 2:
//        Input: a = 2, b = 3
//        Output: 5
    }
}
