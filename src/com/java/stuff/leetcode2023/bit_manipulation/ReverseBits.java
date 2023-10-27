package com.java.stuff.leetcode2023.bit_manipulation;

public class ReverseBits {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;

        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= n & 1;
            n >>= 1;
        }

        return res;
    }


    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();

        System.out.println();


//        Example 1:
//        Input: n = 00000010100101000001111010011100
//        Output:    964176192 (00111001011110000010100101000000)
//        Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
//
//        Example 2:
//        Input: n = 11111111111111111111111111111101
//        Output:   3221225471 (10111111111111111111111111111111)
//        Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
    }

    public int reverseBitsEasy(int n) {
        return Integer.reverse(n);
    }
}
