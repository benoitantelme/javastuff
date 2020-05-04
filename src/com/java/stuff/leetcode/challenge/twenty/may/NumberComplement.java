package com.java.stuff.leetcode.challenge.twenty.may;

public class NumberComplement {

    public int findComplement(int num) {
        int temp = num;
        int length = 0;
        int mask = 0;
        while(temp > 0){
            temp = temp >> 1;
            mask += (int) Math.pow(2, length++);
        }

        return num ^ mask;
    }


    public static void main(String[] args) {
        NumberComplement nc = new NumberComplement();
        System.out.println(nc.findComplement(5));

    }
}
