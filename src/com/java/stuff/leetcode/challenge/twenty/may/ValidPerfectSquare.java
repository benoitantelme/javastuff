package com.java.stuff.leetcode.challenge.twenty.may;

public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        if(num == 1)
            return true;

        for(int i = 1; i <= num/2; i++)
            if(i*i == num)
                return true;

        return false;
    }


    public static void main(String[] args) {
        ValidPerfectSquare vps = new ValidPerfectSquare();

        System.out.println(vps.isPerfectSquare(16));
        System.out.println(vps.isPerfectSquare(14));
        System.out.println(vps.isPerfectSquare(1));
        System.out.println(vps.isPerfectSquare(4));
    }

}
