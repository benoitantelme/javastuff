package com.java.stuff.leetcode.hard;

import java.util.Arrays;

public class ValidNumber {



    private boolean checkDigits(String s){
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }

        return true;
    }

    private String cleanPlusOrMinus(String s){
        if (s.length() > 0 && (s.charAt(0) == '+' || s.charAt(0) == '-'))
            s = s.substring(1);

        return s;
    }

    public boolean isNumber(String s) {
        s = s.trim();

        if(s.length() == 0)
            return false;

        String firstPart;
        String secondPart = null;
        if (s.contains("e")) {
            String[] splitted = s.split("e");

            //more than one e
            if(s.length() > Arrays.stream(splitted).map(splits -> splits.length()).reduce(0, Integer::sum) + 1)
                return false;

            if (splitted.length != 2)
                return false;

            firstPart = splitted[0];
            secondPart = splitted[1];

            // check parts length
            if(firstPart.length() == 0 || secondPart.length() == 0)
                return false;

            //remove eventual minus in second part
            secondPart = cleanPlusOrMinus(secondPart);

            if(secondPart.isEmpty())
                return false;

            if(!checkDigits(secondPart))
                return false;

        } else
            firstPart = s;

        // get read of the exp after it has been checked
        secondPart = null;

        if (firstPart.contains(".")) {
            String[] splitted = firstPart.split("\\.");

            //more than one dot
            if(firstPart.length() > Arrays.stream(splitted).map(splits -> splits.length()).reduce(0, Integer::sum) + 1)
                return false;

            // two many or no parts
            if(splitted.length == 0 || splitted.length > 2)
                return false;

            if (splitted.length == 2)
                secondPart = splitted[1];

            firstPart = splitted[0];
        }

        firstPart = cleanPlusOrMinus(firstPart);

        // nothing there
        if(firstPart.isEmpty() && (secondPart == null || secondPart.isEmpty()))
            return false;

        if(!checkDigits(firstPart))
            return false;

        if (secondPart != null)
            if(!checkDigits(secondPart))
                return false;

        return true;
    }

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();

        System.out.println(vn.isNumber("0")); // => true
        System.out.println(vn.isNumber(" 0.1 ")); // => true
        System.out.println(vn.isNumber(" +1 ")); // => true
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
        System.out.println(vn.isNumber(" "));
        System.out.println(vn.isNumber(".1"));
        System.out.println(vn.isNumber("3."));
        System.out.println(vn.isNumber("."));
        System.out.println(vn.isNumber("..2"));
        System.out.println(vn.isNumber("0.."));
        System.out.println(vn.isNumber(" -."));
        System.out.println(vn.isNumber(" +0e-"));
        System.out.println(vn.isNumber("7e69e"));
        System.out.println(vn.isNumber(" 005047e+6"));
    }


}
