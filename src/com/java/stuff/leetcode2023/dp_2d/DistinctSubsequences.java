package com.java.stuff.leetcode2023.dp_2d;

import java.util.Arrays;

public class DistinctSubsequences {

    int rec(String s, String target, int i, int j, int[][] cache) {
        if (cache[i][j] != -1)
            return cache[i][j];

        int targetLength = target.length();
        int sLength = s.length();
        if (j >= targetLength)
            return 1;

        if (i >= sLength)
            return 0;

        int res = 0;
        if (s.charAt(i) == target.charAt(j))
            res += rec(s, target, i + 1, j + 1, cache);

        res += rec(s, target, i + 1, j, cache);

        cache[i][j] = res;
        return res;
    }

    public int numDistinct(String s, String t) {
        int[][] cache = new int[s.length() + 1][t.length() + 1];
        for (int[] row : cache)
            Arrays.fill(row, -1);

        return rec(s, t, 0, 0, cache);
    }

    public static void main(String[] args) {
        DistinctSubsequences ds = new DistinctSubsequences();

        System.out.println(ds.numDistinct("rabbbit", "rabbit"));
        System.out.println(ds.numDistinct("babgbag", "bag"));

//        Example 1:
//        Input: s = "rabbbit", t = "rabbit"
//        Output: 3
//        Explanation:
//        As shown below, there are 3 ways you can generate "rabbit" from s.
//        rabbbit
//                rabbbit
//        rabbbit
//
//        Example 2:
//        Input: s = "babgbag", t = "bag"
//        Output: 5
//        Explanation:
//        As shown below, there are 5 ways you can generate "bag" from s.
//        babgbag
//                babgbag
//        babgbag
//                babgbag
//        babgbag
    }

    int recU(String s, String target, int i, int j, int length, int[][] cache) {
        if (cache[i][j] != -1)
            return cache[i][j];

        int targetLength = target.length();
        int sLength = s.length();
        if (i >= sLength || j >= targetLength || length > targetLength) {
            cache[i][j] = 0;
            return 0;
        }

        boolean equals = s.charAt(i) == target.charAt(j);
        if (!equals) {
            cache[i][j] = 0;
            return 0;
        }

        length++;

        if (length == targetLength) {
            cache[i][j] = 1;
            return 1;
        }

        int res = 0;
        int index = equals ? j + 1 : i;
        for (int k = i + 1; k < sLength; k++)
            res += recU(s, target, k, index, length, cache);

        cache[i][j] = res;
        return res;
    }

    public int numDistinctU(String s, String t) {
        int res = 0;
        int[][] cache = new int[s.length()][t.length()];
        for (int[] row : cache)
            Arrays.fill(row, -1);

        for (int i = 0; i < s.length() - t.length() + 1; i++)
            res += recU(s, t, i, 0, 0, cache);

        return res;
    }

}
