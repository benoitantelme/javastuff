package com.java.stuff.leetcode2023.bit_manipulation;

import java.util.Arrays;

public class CountingBits {

    public int[] countBits(int n) {
        int[] res = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            res[i] = 1 + res[i & (i - 1)];
        }

        return res;
    }

    public static void main(String[] args) {
        CountingBits cb = new CountingBits();

        System.out.println(Arrays.toString(cb.countBits(2)));
        System.out.println(Arrays.toString(cb.countBits(5)));


//        Example 1:
//        Input: n = 2
//        Output: [0,1,1]
//        Explanation:
//        0 --> 0
//        1 --> 1
//        2 --> 10
//
//        Example 2:
//        Input: n = 5
//        Output: [0,1,1,2,1,2]
//        Explanation:
//        0 --> 0
//        1 --> 1
//        2 --> 10
//        3 --> 11
//        4 --> 100
//        5 --> 101
    }

    public int[] countBitsSlow(int n) {
        int[] res = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            res[i] = hammingWeight(i);
        }

        return res;
    }

    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            if ((n & 1) == 1)
                result++;

            n = n >>> 1;
        }

        return result;
    }

}
