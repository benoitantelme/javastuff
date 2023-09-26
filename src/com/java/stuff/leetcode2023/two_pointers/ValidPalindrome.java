package com.java.stuff.leetcode2023.two_pointers;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if(s.isEmpty())
            return true;

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while(i < j && !Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }

            while(j > i && !Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }

            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;

            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();

        System.out.println(vp.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(vp.isPalindrome("race a car"));
        System.out.println(vp.isPalindrome(" "));
        System.out.println(vp.isPalindrome("aa"));
        System.out.println(vp.isPalindrome("0P"));
        System.out.println(vp.isPalindrome(".,"));

    }

    public boolean problemIsPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        s = s.toLowerCase();

        if(s.length() < 2)
            return true;

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        }

        return true;
    }

}
