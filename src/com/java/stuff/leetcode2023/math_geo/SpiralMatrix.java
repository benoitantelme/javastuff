package com.java.stuff.leetcode2023.math_geo;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0;
        int bot = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        int total = matrix.length * matrix[0].length;
        while (notFinished(result.size(), total)) {
            int i = top;
            int j;

            for (j = left; j <= right && notFinished(result.size(), total); j++)
                result.add(matrix[i][j]);
            j = right;

            for (i = top + 1; i <= bot && notFinished(result.size(), total); i++)
                result.add(matrix[i][j]);
            i = bot;

            for (j = right - 1; j >= left && notFinished(result.size(), total); j--)
                result.add(matrix[i][j]);
            j = left;

            for (i = bot - 1; i > top && notFinished(result.size(), total); i--)
                result.add(matrix[i][j]);

            top++;
            bot--;
            left++;
            right--;
        }


        return result;
    }

    boolean notFinished(int size, int total) {
        return size != total;
    }

    public static void main(String[] args) {

        SpiralMatrix sm = new SpiralMatrix();

        System.out.println(sm.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(sm.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));


//        Example 1:
//        Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//        Output: [1,2,3,6,9,8,7,4,5]
//
//        Example 2:
//        Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//        Output: [1,2,3,4,8,12,11,10,9,5,6,7]
    }

}
