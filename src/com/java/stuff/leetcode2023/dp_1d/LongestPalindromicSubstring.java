package com.java.stuff.leetcode2023.dp_1d;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        String result = s.substring(s.length() - 1);

        for (int i = 0; i < s.length(); i++)
            for (int j = i + 1; j < s.length() + 1; j++) {
                String tmp = s.substring(i, j);
                if (tmp.length() > result.length() && isPal(tmp))
                    result = tmp;
            }

        return result;
    }

    boolean isPal(String s) {
        if (s.length() == 1)
            return true;

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;

        }

        return true;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();

        System.out.println(lps.longestPalindrome("babad"));
        System.out.println(lps.longestPalindrome("cbbd"));
        System.out.println(lps.longestPalindrome("bb"));


//        Example 1:
//        Input: s = "babad"
//        Output: "bab"
//        Explanation: "aba" is also a valid answer.
//
//                Example 2:
//        Input: s = "cbbd"
//        Output: "bb"
    }

    String result2;

    void rec2(String s, int start, int j) {
        if (start > s.length() || j > s.length())
            return;

        String tmp = s.substring(start, j);
        if (tmp.length() > result2.length() && isPal(tmp))
            result2 = tmp;
        rec2(s, start, j + 1);
    }

    public String longestPalindrome2(String s) {
        result2 = s.substring(s.length() - 1);

        for (int i = 0; i < s.length(); i++)
            rec2(s, i, i + 1);

        return result2;
    }


}
