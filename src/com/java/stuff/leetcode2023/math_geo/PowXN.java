package com.java.stuff.leetcode2023.math_geo;

public class PowXN {

    double rec(double x, int n) {
        if (n == 1)
            return x;

        double result = rec(x * x, n / 2);
        if (n % 2 != 0)
            result = result * x;

        return result;
    }

    public double myPow(double x, int n) {
        if (x == 0d || x == 1d)
            return x;

        if (n == 0)
            return 1d;

        if (n < 0) {
            if (n != Integer.MIN_VALUE) {
                n = -n;
                x = 1 / x;
            } else {
                // avoid overflow
                n = n / 2;
                n = -n;
                x = (1 / x) * (1 / x);
            }
        }

        return rec(x, n);
    }

    public static void main(String[] args) {
        PowXN pow = new PowXN();

        System.out.println(pow.myPow(2d, 4));
        System.out.println(pow.myPow(2.00000, 10));
        System.out.println(pow.myPow(2.10000, 3));
        System.out.println(pow.myPow(2.00000, -2));
        System.out.println(pow.myPow(1.00000, 2147483647));
        System.out.println(pow.myPow(8.84372, -5));
        System.out.println(pow.myPow(2.00000, -2147483648));
        System.out.println(pow.myPow(-1.00000, -2147483648));

//        Example 1:
//        Input: x = 2.00000, n = 10
//        Output: 1024.00000
//
//        Example 2:
//        Input: x = 2.10000, n = 3
//        Output: 9.26100
//
//        Example 3:
//        Input: x = 2.00000, n = -2
//        Output: 0.25000
//        Explanation: 2-2 = 1/22 = 1/4 = 0.25
    }

    public double myPowLinearTle(double x, int n) {
        double result = 1;

        boolean negative = false;
        if (n < 0) {
            negative = true;
            n = -n;
        }

        for (int i = 0; i < n; i++)
            result *= x;

        if (negative)
            result = 1 / result;

        return result;
    }

}
