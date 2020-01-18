package com.java.stuff.sort;

import java.util.Arrays;

public class Mergesort {

    private static void merge(int[] arr, int min, int mid, int max) {
        int size1 = mid - min + 1;
        int size2 = max - mid;

        // create temp arrays
        int[] tmp1 = new int[size1];
        for (int i = 0; i < size1; i++)
            tmp1[i] = arr[min + i];

        int[] tmp2 = new int[size2];
        for (int i = 0; i < size2; i++)
            tmp2[i] = arr[mid + i + 1];

        int i = 0, j = 0, k = min;
        while (i < size1 && j < size2) {
            if (tmp1[i] < tmp2[j])
                arr[k++] = tmp1[i++];
            else
                arr[k++] = tmp2[j++];
        }

        // finish the process by the elements in the remaining array
        while (i < size1)
            arr[k++] = tmp1[i++];

        while (j < size2)
            arr[k++] = tmp2[j++];
}

    public static void sort(int[] arr, int min, int max) {
        if (min < max) {
            int mid = (min + max) / 2;

            sort(arr, min, mid);
            sort(arr, mid + 1, max);
            merge(arr, min, mid, max);
        }
    }

    public static void main(String args[]) {
        int[] arr = {4, 1, 10, 23, 5};
        System.out.println(Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr));
    }
}
