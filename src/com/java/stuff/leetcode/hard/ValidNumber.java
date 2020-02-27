package com.java.stuff.leetcode.hard;

public class ValidNumber {

    public boolean isNumber(String s) {

        return false;
    }

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();

        System.out.println(vn.isNumber("0")); // => true
        System.out.println(vn.isNumber(" 0.1 ")); // => true
        System.out.println(vn.isNumber("abc")); // => false
        System.out.println(vn.isNumber("1 a")); // => false
        System.out.println(vn.isNumber("2e10")); // => true
        System.out.println(vn.isNumber(" -90e3   ")); // => true
        System.out.println(vn.isNumber(" 1e")); // => false
        System.out.println(vn.isNumber("e3")); // => false
        System.out.println(vn.isNumber(" 6e-1")); // => true
        System.out.println(vn.isNumber(" 99e2.5 ")); // = > false
        System.out.println(vn.isNumber("53.5e93")); // = > true
        System.out.println(vn.isNumber(" --6 ")); // = > false
        System.out.println(vn.isNumber("-+3")); // = > false
        System.out.println(vn.isNumber("95a54e53")); // = > false

    }


}
