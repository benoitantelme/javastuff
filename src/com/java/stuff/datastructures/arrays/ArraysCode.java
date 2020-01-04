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

        array = new int[]{1, 2, 3, 4};
        reverse(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{0, 1, 2, 4, 8, 0, 9, 0};
        pushZeros(array);
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

    private static void reverse(int[] array) {
        int start = 0;
        int end = array.length-1;
        int tmp;

        while(start < end){
            tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            start ++;
            end --;
        }
    }

    private static void pushZeros(int[] array) {
        int p = 0;

        for(int i = 0; i < array.length; i++)
            if(array[i] != 0)
                array[p++] = array[i];

        for(int i = p; i < array.length; i++)
            array[i] = 0;
    }

}
