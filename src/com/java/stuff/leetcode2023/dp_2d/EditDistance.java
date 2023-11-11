package com.java.stuff.leetcode2023.dp_2d;

import java.util.Arrays;

public class EditDistance {

    int rec(String word1, String word2, int i, int j, int[][] cache) {
        if (cache[i][j] != -1)
            return cache[i][j];

        if (i >= word1.length() && j >= word2.length())
            return 0;

        if (i >= word1.length())
            return word2.length() - j;
        if (j >= word2.length())
            return word1.length() - i;

        int result;
        if (word1.charAt(i) == word2.charAt(j))
            result = rec(word1, word2, i + 1, j + 1, cache);

        else {
            result = 1 + rec(word1, word2, i + 1, j, cache);
            result = Math.min(result, 1 + rec(word1, word2, i, j + 1, cache));
            result = Math.min(result, 1 + rec(word1, word2, i + 1, j + 1, cache));
        }

        cache[i][j] = result;
        return result;
    }

    public int minDistance(String word1, String word2) {
        int[][] cache = new int[word1.length() + 1][word2.length() + 1];
        for (int[] row : cache)
            Arrays.fill(row, -1);
        return rec(word1, word2, 0, 0, cache);
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();

        System.out.println(ed.minDistance("horse", "hors"));
        System.out.println(ed.minDistance("horse", "herse"));
        System.out.println(ed.minDistance("horse", "ros"));
        System.out.println(ed.minDistance("intention", "execution"));

//        Example 1:
//        Input: word1 = "horse", word2 = "ros"
//        Output: 3
//        Explanation:
//        horse -> rorse (replace 'h' with 'r')
//        rorse -> rose (remove 'r')
//        rose -> ros (remove 'e')
//
//        Example 2:
//        Input: word1 = "intention", word2 = "execution"
//        Output: 5
//        Explanation:
//        intention -> inention (remove 't')
//        inention -> enention (replace 'i' with 'e')
//        enention -> exention (replace 'n' with 'x')
//        exention -> exection (replace 'n' with 'c')
//        exection -> execution (insert 'u')
    }

}
