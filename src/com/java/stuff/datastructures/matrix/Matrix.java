package com.java.stuff.datastructures.matrix;

import java.util.Arrays;

public class Matrix {

    public static void rotateMatrix(int[][] mtr){
        int n = mtr.length;
        int rotations = n/2;
        int x = 0, y = 0, times = 0;

        int tmp, curr;
        while(rotations >= times){
            tmp = mtr[x+1][y];

            for(; y < n; y ++){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }
            x++;
            y--;
            for(; x < n; x++){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }
            y--;
            x--;
            for(; y >= times; y --){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }
            x--;
            y++;
            for(; x >= times; x --){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }

            x = y = ++times;
            n--;
        }
    }


    public static void main(String[] args) {
        int mtr[][] = { {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16} };
        System.out.println("Matrix:");
        for(int[] array : mtr)
            System.out.println(Arrays.toString(array));

        rotateMatrix(mtr);
        System.out.println("Rotated:");
        for(int[] array : mtr)
            System.out.println(Arrays.toString(array));

    }

}
