package com.java.stuff.leetcode2023.dp_1d;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            // Odd Length
            result += countPals(s, i, i);

            // Even Length
            result += countPals(s, i, i + 1);
        }
        return result;
    }

    int countPals(String s, int l, int r) {
        int result = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            result++;
            l--;
            r++;
        }
        return result;
    }

    public static void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();

        System.out.println(ps.countSubstrings("abc"));
        System.out.println(ps.countSubstrings("aaa"));


//        Example 1:
//        Input: s = "abc"
//        Output: 3
//        Explanation: Three palindromic strings: "a", "b", "c".
//
//                Example 2:
//        Input: s = "aaa"
//        Output: 6
//        Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
    }

    List<String> set2;

    void rec2(String s, int start, int end) {
        if (start > s.length() - 1 || end > s.length())
            return;

        String tmp = s.substring(start, end);
        if (isPal2(tmp))
            set2.add(tmp);

        rec2(s, start, end + 1);
    }

    public int countSubstrings2(String s) {
        set2 = new ArrayList<>();

        for (int i = 0; i < s.length(); i++)
            rec2(s, i, i + 1);

        return set2.size();
    }

    boolean isPal2(String s) {
        if (s.length() == 1)
            return true;

        int i = 0;
        int j = s.length() - 1;
        char[] sc = s.toCharArray();
        while (i <= j)
            if (sc[i++] != sc[j--])
                return false;

        return true;
    }
}
