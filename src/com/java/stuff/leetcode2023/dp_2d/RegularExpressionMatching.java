package com.java.stuff.leetcode2023.dp_2d;

public class RegularExpressionMatching {

    boolean rec(String s, String p, int i, int j, boolean[][] cache) {
        if (i >= s.length() && j >= p.length())
            return true;

        if (j >= p.length())
            return false;

        if (cache[i][j] != false)
            return true;

        if (j <= p.length() - 2 && p.charAt(j + 1) == '*')
            j++;

        boolean res = false;
        if (p.charAt(j) == '*') {
            res |= rec(s, p, i, j + 1, cache);
            if (i < s.length())
                if (p.charAt(j - 1) == '.' || s.charAt(i) == p.charAt(j - 1))
                    res |= rec(s, p, i + 1, j, cache);
        } else if (i >= s.length()) {
            // can't match remaining
            res = false;
        } else if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
            res |= rec(s, p, i + 1, j + 1, cache);
        }

        cache[i][j] = res;
        return res;
    }

    public boolean isMatch(String s, String p) {
        return rec(s, p, 0, 0, new boolean[s.length() + 1][p.length() + 1]);
    }

    public static void main(String[] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();

        System.out.println(rem.isMatch("aa", "a"));
        System.out.println(rem.isMatch("ab", "ab"));
        System.out.println(rem.isMatch("aa", "a*"));
        System.out.println(rem.isMatch("ab", ".*"));
        System.out.println(rem.isMatch("abbbc", "ab*c"));
        System.out.println(rem.isMatch("abbbc", "ab*d"));
        System.out.println(rem.isMatch("aaa", "a*a"));
        System.out.println(rem.isMatch("aaa", "ab*a"));
        System.out.println(rem.isMatch("aa", "ab*a"));
        System.out.println(rem.isMatch("aaa", "ab*a*c*a"));
        System.out.println(rem.isMatch("bbbba", ".*a*a"));
        System.out.println(rem.isMatch("a", ".*..a*"));


//        Example 1:
//        Input: s = "aa", p = "a"
//        Output: false
//        Explanation: "a" does not match the entire string "aa".
//
//                Example 2:
//        Input: s = "aa", p = "a*"
//        Output: true
//        Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
//
//                Example 3:
//        Input: s = "ab", p = ".*"
//        Output: true
//        Explanation: ".*" means "zero or more (*) of any character (.)".
    }

    public boolean isMatchFailOnComplexCases(String s, String p) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < p.length()) {
            if (j <= p.length() - 2 && p.charAt(j + 1) == '*') {
                if (p.charAt(j) == '.') {
                    //if remaining chars after it will not match
                    if (j != p.length() - 2)
                        return false;
                    else
                        return true;
                } else {
                    int remove = 0;
                    if (s.charAt(i) == p.charAt(j)) {
                        // check if later chars are identical and update remove
                        int k = j + 2;
                        while (k < p.length() && p.charAt(j) == p.charAt(k++))
                            remove++;

                        while (i < s.length() && s.charAt(i) == p.charAt(j))
                            i++;
                    }

                    // update pointer and ignore the later identical chars
                    j += 2 + remove;
                }
            } else {
                if (p.charAt(j) == '.') {
                    i++;
                    j++;
                } else if (s.charAt(i) == p.charAt(j)) {
                    i++;
                    j++;
                } else
                    return false;
            }
        }

        if (i < s.length() || j < p.length())
            return false;
        else
            return true;
    }

}