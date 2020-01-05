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

        array = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Max sum of sub array: " + sumMaxSubArray(array));
    }

    static void rotateLeft(int[] array, int n) {
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

    static void reverse(int[] array) {
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

    static void pushZeros(int[] array) {
        int p = 0;

        for(int i = 0; i < array.length; i++)
            if(array[i] != 0)
                array[p++] = array[i];

        for(int i = p; i < array.length; i++)
            array[i] = 0;
    }

    static int distanceMin(int[] array, int a, int b) {
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

    /**
     * @param array
     * @return sum of max sub array using kadane dynamic programming
     */
    static int sumMaxSubArray(int[] array) {
        int max = Integer.MIN_VALUE;
        int localMax = 0;

        for(int i =0; i < array.length; i++){
            localMax = Math.max(0, localMax + array[i]);
            if(localMax > max)
                max = localMax;
        }

        return max;
    }

}
