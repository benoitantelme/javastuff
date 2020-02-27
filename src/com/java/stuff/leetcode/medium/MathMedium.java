package com.java.stuff.leetcode.medium;

public class MathMedium {


    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n == 1)
            return x;

        // abs(MAX VALUE) = abs(MIN VALUE - 1) so -n does not work because of 32 bits limit
        if(n==Integer.MIN_VALUE)
        {
            x=x*x;
            n/=2;
        }

        if(n <0){
            n = -n;
            x = 1/x;
        }

        if(n % 2 == 0)
            return myPow(x*x, n/2);
        else
            return myPow(x*x, n/2) * x;
    }


    public static void main(String[] args){
        MathMedium mm = new MathMedium();
        System.out.println(mm.myPow(2.00000, 10));
        System.out.println(mm.myPow(2.10000, 3));
        System.out.println(mm.myPow(2.00000, -2));
        System.out.println(mm.myPow(0.00001, 2147483647));
        System.out.println(mm.myPow(2.00000,-2147483648));

    }

}




