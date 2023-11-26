package com.java.stuff.leetcode2023.math_geo;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);

                }
            }
        }

        for (int row : rows)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[row][j] = 0;

        for (int column : columns)
            for (int i = 0; i < matrix.length; i++)
                matrix[i][column] = 0;
    }

    public static void main(String[] args) {
        SetMatrixZeroes smz = new SetMatrixZeroes();

        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        smz.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));

        matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        smz.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));

//        Example 1:
//        Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//        Output: [[1,0,1],[0,0,0],[1,0,1]]
//
//        Example 2:
//        Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//        Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
    }

}
