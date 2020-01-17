package com.java.stuff.sort;

import java.util.Arrays;

public class Quicksort {

    static private int partition(int[] array, int min, int max){
        int pivot = array[max];
        int i = min-1;

        for(int j = min; j < max; j++){
            if(array[j] < pivot){
                i++;
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        int tmp = array[max];
        array[max] = array[i+1];
        array[i+1] = tmp;

        return i+1;
    }

    static void sort(int[] array, int min, int max){
        if (min < max) {
            int pivot = partition(array, min, max);

            sort(array, min, pivot-1);
            sort(array, pivot+1, max);
        }
    }

    public static void main(String args[]) {
        int arr[] = {10, 7, 8, 9, 1, 5};
        System.out.println(Arrays.toString(arr));
        Quicksort.sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

}
