package com.java.stuff.leetcode2023.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    List<List<String>> result;

    void rec(String str, int index, List<String> candidates) {
        if (index == str.length()) {
            result.add(new ArrayList<>(candidates));
            return;
        }

        for (int i = index; i < str.length(); i++) {
            String s = str.substring(index, i + 1);
            if (isPal(s)) {
                candidates.add(s);
                rec(str, i + 1, candidates);
                candidates.remove(candidates.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        rec(s, 0, new ArrayList<>());
        return result;
    }

    boolean isPal(String s) {
        boolean result = true;
        if (s.length() == 1)
            return result;

        int i = 0;
        int j = s.length() - 1;
        while (i <= j)
            if (s.charAt(i++) != s.charAt(j--))
                return false;

        return result;
    }

    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        System.out.println(pp.partition("aab"));
        System.out.println(pp.partition("a"));

//        Example 1:
//        Input: s = "aab"
//        Output: [["a","a","b"],["aa","b"]]
//
//        Example 2:
//        Input: s = "a"
//        Output: [["a"]]
    }

}
