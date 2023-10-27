package com.java.stuff.leetcode2023.bit_manipulation;

public class NumberOf1Bits {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        while(n !=0){
            if((n & 1) == 1)
                result++;

            n = n >>> 1;
        }

        return result;
    }

    public static void main(String[] args) {
        NumberOf1Bits nob = new NumberOf1Bits();

        System.out.println(nob.hammingWeight(11));
        System.out.println(nob.hammingWeight(256));
        System.out.println(nob.hammingWeight(-3));


//    Example 1:
//    Input: n = 00000000000000000000000000001011
//    Output: 3
//    Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
//
//            Example 2:
//    Input: n = 00000000000000000000000010000000
//    Output: 1
//    Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
//
//            Example 3:
//    Input: n = 11111111111111111111111111111101
//    Output: 31
//    Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
    }

    public int hammingWeight2(int n) {
        int result = 0;
        String s = Integer.toBinaryString(n);
        for (char c : s.toCharArray())
            if ('1' == c)
                result++;

        return result;
    }

}
