package com.java.stuff.leetcode2023.bit_manipulation;

public class ReverseInteger {

    public int reverse(int x) {
        int res = 0;

        while (x != 0) {
            if (((x > 0) && Integer.MAX_VALUE / 10 < res)
                    || ((x < 0) && Integer.MIN_VALUE / 10 > res))
                return 0;
            res *= 10;

            if (((x > 0) && Integer.MAX_VALUE - x % 10 < res)
                    || ((x < 0) && Integer.MIN_VALUE - x % 10 > res))
                return 0;
            res += x % 10;

            x /= 10;
        }

        return res;
    }

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();

        System.out.println(ri.reverse(123));
        System.out.println(ri.reverse(-123));
        System.out.println(ri.reverse(120));
        System.out.println(ri.reverse(1534236469));
        System.out.println(ri.reverse(1463847412));

//        Example 1:
//        Input: x = 123
//        Output: 321
//
//        Example 2:
//        Input: x = -123
//        Output: -321
//
//        Example 3:
//        Input: x = 120
//        Output: 21
    }


}
