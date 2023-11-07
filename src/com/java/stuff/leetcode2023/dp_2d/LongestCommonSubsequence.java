package com.java.stuff.leetcode2023.dp_2d;

public class LongestCommonSubsequence {

    // bottom up
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] cache = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j))
                    cache[i][j] = 1 + (i + 1 < n && j + 1 < m ? cache[i + 1][j + 1] : 0);
                else
                    cache[i][j] = Math.max((i + 1 < n && j < m ? cache[i + 1][j] : 0),
                            (i < n && j + 1 < m ? cache[i][j + 1] : 0));
            }
        }

        return cache[0][0];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();

        System.out.println(lcs.longestCommonSubsequence("abcde", "ace"));
        System.out.println(lcs.longestCommonSubsequence("abc", "abc"));
        System.out.println(lcs.longestCommonSubsequence("abc", "def"));

//        Example 1:
//        Input: text1 = "abcde", text2 = "ace"
//        Output: 3
//        Explanation: The longest common subsequence is "ace" and its length is 3.
//
//        Example 2:
//        Input: text1 = "abc", text2 = "abc"
//        Output: 3
//        Explanation: The longest common subsequence is "abc" and its length is 3.
//
//        Example 3:
//        Input: text1 = "abc", text2 = "def"
//        Output: 0
//        Explanation: There is no such common subsequence, so the result is 0.
    }

    public int longestCommonSubsequenceTrick(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] cache = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j))
                    cache[i][j] = 1 + (cache[i + 1][j + 1]);
                else
                    cache[i][j] = Math.max(cache[i + 1][j], cache[i][j + 1]);
            }
        }

        return cache[0][0];
    }

}
