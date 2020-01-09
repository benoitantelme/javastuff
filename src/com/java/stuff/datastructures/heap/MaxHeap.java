package com.java.stuff.datastructures.heap;

import java.util.Arrays;

public class MaxHeap {

    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            // Move root to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // heapify the reduced heap
            heapify(arr, i, 0);
        }
    }
    private void heapify(int arr[], int heapSize, int rootIndex) {
        int largest = rootIndex;
        int l = 2*rootIndex + 1;
        int r = 2*rootIndex + 2;

        if (l < heapSize && arr[l] > arr[largest])
            largest = l;

        if (r < heapSize && arr[r] > arr[largest])
            largest = r;

        if (largest != rootIndex)
        {
            int swap = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, heapSize, largest);
        }
    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 5, 13, 6, 7};
        System.out.println("Array is: " + Arrays.toString(arr));
        MaxHeap mh = new MaxHeap();
        mh.sort(arr);

        System.out.println("Sorted array is: " + Arrays.toString(arr));
    }


}