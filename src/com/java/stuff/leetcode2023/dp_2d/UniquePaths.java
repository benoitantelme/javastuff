package com.java.stuff.leetcode2023.dp_2d;

public class UniquePaths {

    // bottom up
    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];
        for (int j = n - 1; j >= 0; j--) {
            for (int i = m - 1; i >= 0; i--) {
                if (i == m - 1 && j == n - 1) {
                    cache[i][j] = 1;
                } else {
                    int down = j + 1 >= n ? 0 : cache[i][j + 1];
                    int right = i + 1 >= m ? 0 : cache[i + 1][j];
                    cache[i][j] = down + right;
                }
            }
        }

        return cache[0][0];
    }

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();

        System.out.println(up.uniquePaths(3, 7));
        System.out.println(up.uniquePaths(3, 2));
        System.out.println(up.uniquePaths(51, 9));

//        Example 1:
//        Input: m = 3, n = 7
//        Output: 28
//
//        Example 2:
//        Input: m = 3, n = 2
//        Output: 3
//        Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
//        1. Right -> Down -> Down
//        2. Down -> Down -> Right
//        3. Down -> Right -> Down
    }

    int rec(int m, int n, int i, int j, int[][] cache) {
        if (i == m - 1 && j == n - 1)
            return 1;

        if (cache[i][j] != 0)
            return cache[i][j];

        int res = 0;
        if (i < m - 1)
            res += rec(m, n, i + 1, j, cache);
        if (j < n - 1)
            res += rec(m, n, i, j + 1, cache);

        cache[i][j] = res;
        return res;
    }

    //top down
    public int uniquePathsDfs(int m, int n) {
        return rec(m, n, 0, 0, new int[m][n]);
    }

}
