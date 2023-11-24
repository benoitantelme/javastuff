package com.java.stuff.leetcode2023.math_geo;

import java.util.Arrays;

public class RotateImage {

    public void rotate(int[][] matrix) {
        int left = 0;
        int right = matrix.length - 1;

        while (left < right) {
            for (int i = 0; i < right - left; i++) {
                int top = left;
                int bottom = right;

                int topLeft = matrix[top][left + i];

                matrix[top][left + i] = matrix[bottom - i][left];
                matrix[bottom - i][left] = matrix[bottom][right - i];
                matrix[bottom][right - i] = matrix[top + i][right];
                matrix[top + i][right] = topLeft;
            }

            left++;
            right--;
        }
    }


    public static void main(String[] args) {
        RotateImage ri = new RotateImage();

        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ri.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));


        matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        ri.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));

//        Example 1:
//        Input:
//        matrix = [[1, 2, 3],[4, 5, 6],[7, 8, 9]]
//        Output: [[7, 4, 1],[8, 5, 2],[9, 6, 3]]
//
//        Example 2:
//        Input:
//        matrix = [[5, 1, 9, 11],[2, 4, 8, 10],[13, 3, 6, 7],[15, 14, 12, 16]]
//        Output: [[15, 13, 2, 5],[14, 3, 4, 1],[12, 6, 8, 9],[16, 7, 10, 11]]
    }

}
