package com.java.stuff.leetcode2023.math_geo;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> already = new HashSet<>();

        while (true) {
            int res = 0;

            while (n != 0) {
                int rest = n % 10;
                n = n / 10;

                res += (int) Math.pow(rest, 2);
            }

            if (res == 1)
                return true;

            if (already.contains(res))
                return false;
            else
                already.add(res);

            n = res;
        }
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();

        System.out.println(hn.isHappy(19));
        System.out.println(hn.isHappy(2));

//        Example 1:
//        Input: n = 19
//        Output: true
//        Explanation:
//        12 + 92 = 82
//        82 + 22 = 68
//        62 + 82 = 100
//        12 + 02 + 02 = 1
//
//        Example 2:
//        Input: n = 2
//        Output: false
    }

}
