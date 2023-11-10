package com.java.stuff.leetcode2023.dp_2d;

import java.util.Arrays;

public class LongestIncreasingPathInMatrix {

    int rec(int[][] matrix, int i, int j, int previous, int[][] cache) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length
                || matrix[i][j] <= previous)
            return 0;

        if (cache[i][j] != -1)
            return cache[i][j];

        int res = -1;
        res = Math.max(res, rec(matrix, i + 1, j, matrix[i][j], cache));
        res = Math.max(res, rec(matrix, i - 1, j, matrix[i][j], cache));
        res = Math.max(res, rec(matrix, i, j + 1, matrix[i][j], cache));
        res = Math.max(res, rec(matrix, i, j - 1, matrix[i][j], cache));
        cache[i][j] = res + 1;
        return cache[i][j];
    }

    public int longestIncreasingPath(int[][] matrix) {
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int[] row : cache)
            Arrays.fill(row, -1);

        int max = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                max = Math.max(max, rec(matrix, i, j, -3, cache));

        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix lip = new LongestIncreasingPathInMatrix();

        System.out.println(lip.longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        System.out.println(lip.longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
        System.out.println(lip.longestIncreasingPath(new int[][]{{1}}));
        System.out.println(lip.longestIncreasingPath(new int[][]{{1, 2}}));
        System.out.println(lip.longestIncreasingPath(new int[][]{{7, 8, 9}, {9, 7, 6}, {7, 2, 3}}));

//        Example 1:
//        Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
//        Output: 4
//        Explanation: The longest increasing path is [1, 2, 6, 9].
//
//        Example 2:
//        Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
//        Output: 4
//        Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
//
//                Example 3:
//        Input: matrix = [[1]]
//        Output: 1
    }

    int recTle(int[][] matrix, int i, int j, int max, boolean[][] visited) {
        int res = max;
        if (i + 1 < matrix.length && !visited[i + 1][j] && matrix[i + 1][j] > matrix[i][j]) {
            boolean[][] copy = Arrays.stream(visited).map(boolean[]::clone).toArray(boolean[][]::new);
            copy[i + 1][j] = true;
            res = Math.max(res, recTle(matrix, i + 1, j, max + 1, copy));
        }
        if (i - 1 >= 0 && !visited[i - 1][j] && matrix[i - 1][j] > matrix[i][j]) {
            boolean[][] copy = Arrays.stream(visited).map(boolean[]::clone).toArray(boolean[][]::new);
            copy[i - 1][j] = true;
            res = Math.max(res, recTle(matrix, i - 1, j, max + 1, copy));
        }
        if (j + 1 < matrix[0].length && !visited[i][j + 1] && matrix[i][j + 1] > matrix[i][j]) {
            boolean[][] copy = Arrays.stream(visited).map(boolean[]::clone).toArray(boolean[][]::new);
            copy[i][j + 1] = true;
            res = Math.max(res, recTle(matrix, i, j + 1, max + 1, copy));
        }
        if (j - 1 >= 0 && !visited[i][j - 1] && matrix[i][j - 1] > matrix[i][j]) {
            boolean[][] copy = Arrays.stream(visited).map(boolean[]::clone).toArray(boolean[][]::new);
            copy[i][j - 1] = true;
            res = Math.max(res, recTle(matrix, i, j - 1, max + 1, copy));
        }

        return res;
    }

    public int longestIncreasingPathTle(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                visited[i][j] = true;
                max = Math.max(max, recTle(matrix, i, j, 1, visited));
            }

        return max;
    }
}
