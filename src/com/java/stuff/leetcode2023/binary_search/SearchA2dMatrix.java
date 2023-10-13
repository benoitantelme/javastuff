package com.java.stuff.leetcode2023.binary_search;

public class SearchA2dMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix.length;

        int column = -1;

        while (i <= j) {
            int pivot = Math.min((i + j) / 2, matrix.length);

            if (pivot > matrix.length - 1)
                return false;

            int start = matrix[pivot][0];
            int end = matrix[pivot][matrix[pivot].length - 1];

            if (start > target) {
                j = pivot - 1;
            } else if (end < target) {
                i = pivot + 1;
            } else {
                column = pivot;
                break;
            }
        }

        if (column == -1) return false;

        i = 0;
        j = matrix[column].length;
        while (i <= j) {
            int pivot = Math.min((i + j) / 2, matrix[column].length);

            int val = matrix[column][pivot];
            if (val == target) {
                return true;

            } else if (val < target) {
                i = pivot + 1;
            } else {
                j = pivot - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SearchA2dMatrix search = new SearchA2dMatrix();

        System.out.println(search.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println(search.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        System.out.println(search.searchMatrix(new int[][]{{1}}, 13));
        System.out.println(search.searchMatrix(new int[][]{{1, 1}}, 13));
        System.out.println(search.searchMatrix(new int[][]{{1, 3, 5}}, 4));
        System.out.println(search.searchMatrix(new int[][]{{1}, {3}, {5}}, 5));

//        Example 1:
//        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//        Output: true
//
//        Example 2:
//        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//        Output: false
    }

}
