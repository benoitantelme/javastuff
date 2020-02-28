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

    private String[] getCoord(String s){
        String[] coord = s.split("\\+");
        coord[1] = coord[1].substring(0, coord[1].length()-1);
        return coord;
    }
    public String complexNumberMultiply(String a, String b) {
        String[] coorda = getCoord(a);
        String[] coordb = getCoord(b);

        //  (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i
        int im = Integer.valueOf(coorda[0]) * Integer.valueOf(coordb[1]) + Integer.valueOf(coorda[1]) * Integer.valueOf(coordb[0]);
        int re = Integer.valueOf(coorda[0]) * Integer.valueOf(coordb[0]) - Integer.valueOf(coorda[1]) * Integer.valueOf(coordb[1]);

        String res = re + "+" + im + "i";

        return res;
    }


    public static void main(String[] args){
        MathMedium mm = new MathMedium();
        System.out.println(mm.myPow(2.00000, 10));
        System.out.println(mm.myPow(2.10000, 3));
        System.out.println(mm.myPow(2.00000, -2));
        System.out.println(mm.myPow(0.00001, 2147483647));
        System.out.println(mm.myPow(2.00000,-2147483648));
        System.out.println(mm.complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(mm.complexNumberMultiply("1+-1i", "1+-1i"));
    }

}




