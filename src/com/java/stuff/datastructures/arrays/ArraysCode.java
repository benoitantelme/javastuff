package com.java.stuff.datastructures.arrays;

import java.util.Arrays;

public class ArraysCode {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rotateLeft(array, 2);
        System.out.println("rotate left by 2: " + Arrays.toString(array));

        array = new int[]{1, 2, 3, 4};
        rotateLeft(array, 2);
        System.out.println("rotate left by 2: " + Arrays.toString(array));

        array = new int[]{1, 2, 3, 4};
        reverse(array);
        System.out.println("reversed: " + Arrays.toString(array));

        array = new int[]{0, 1, 2, 4, 8, 0, 9, 0};
        pushZeros(array);
        System.out.println("zeros at the end: " + Arrays.toString(array));

        array = new int[]{3, 5, 4, 2, 7, 3, 1, 0, 5, 1, 7, 3};
        System.out.println("distance min between 5 et 7: " + distanceMin(array, 5, 7));


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

    private static int distanceMin(int[] array, int a, int b) {
        int min = Integer.MAX_VALUE;
        int previous = 0;

        for (int i = 0; i < array.length; i++){
            int val = array[i];
            if(val == a || val == b) {
                previous = i;
                break;
            }
        }

        for (int i = previous; i < array.length; i++){
            int val = array[i];

            if(val == a || val == b) {
                if(val != array[previous] && (i-previous < min)) {
                    min = i - previous;
                }

                previous = i;
            }
        }

        return min;
    }

}
