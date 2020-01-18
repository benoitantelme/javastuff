package com.java.stuff.sort;

import java.util.Arrays;
import java.util.Stack;

public class Quicksort {


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static private int partition(int[] array, int min, int max){
        int pivot = array[max];
        int i = min;

        for(int j = min; j < max; j++){
            if(array[j] < pivot){
                swap(array, i, j);
                i++;
            }
        }

        swap(array, i, max);
        return i;
    }

    static void sort(int[] array, int min, int max){
        if (min < max) {
            int pivot = partition(array, min, max);

            sort(array, min, pivot-1);
            sort(array, pivot+1, max);
        }
    }

    static void iterative(int[] array){
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        stack.push(array.length-1);

        while(!stack.isEmpty()){
            int max = stack.pop();
            int min = stack.pop();

            int pivot = partition(array, min, max);


            if (pivot - 1 > min )
            {
                stack.push(min);
                stack.push(pivot -1);
            }
            if (pivot + 1 < max)
            {
                stack.push(pivot + 1);
                stack.push(max);
            }
        }
    }

    public static void main(String args[]) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        System.out.println(Arrays.toString(arr));
        Quicksort.sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{4, 1, 10, 23, 5};
        System.out.println(Arrays.toString(arr));
        Quicksort.iterative(arr);
        System.out.println(Arrays.toString(arr));
    }

}
