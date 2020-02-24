package com.java.stuff.leetcode.medium;

public class ArrayMedium {

    public int countSquares(int[][] matrix) {
        int res = 0;

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != 0 && j != 0 && matrix[j][i] == 1) {
                    // 2 for the first bloc + 3 in a 4 blocs square
                    // 3 for the first, 6 for the 3 blocs next + 5 for a 9 blocs square
                    // rewriting the value to be reused for mins
                    int min = Math.min(
                            Math.min(matrix[j - 1][i], matrix[j - 1][i - 1]),
                            matrix[j][i - 1]) +1;
                    matrix[j][i] = min;
                }

                res += matrix[j][i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ArrayMedium am = new ArrayMedium();
        System.out.println(am.countSquares(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        }));
    }

}
