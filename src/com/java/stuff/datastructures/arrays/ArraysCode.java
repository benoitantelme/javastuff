package com.java.stuff.datastructures.arrays;

import java.util.Arrays;

public class ArraysCode {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rotateLeft(array, 2);
        System.out.println(Arrays.toString(array));

        array = new int[]{1, 2, 3, 4};
        rotateLeft(array, 2);
        System.out.println(Arrays.toString(array));

    }


    private static void rotateLeft(int[] array, int n) {
        int p = array.length - n;

        for (int i = 0; i < n; i++) {
            int temp = array[i];

            for (int j = i; j + n < array.length; j += n) {
                array[j] = array[j + n];
            }
            array[p] = temp;
            p++;
        }
    }


}
