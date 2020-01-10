package com.java.stuff.datastructures.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Hash {
    public static boolean isSubset(int[] array1, int[] array2) {
        HashSet<Integer> hashset = new HashSet<>();
        for (int i : array1)
            hashset.add(i);

        for (int i : array2)
            if (!hashset.contains(i))
                return false;

        return true;
    }

    public static HashSet<Integer> intersection(int[] array1, int[] array2) {
        HashSet<Integer> hashset = new HashSet<>();
        for (int i : array1)
            hashset.add(i);

        HashSet<Integer> result = new HashSet<>();

        for (int i : array2)
            if (hashset.contains(i))
                result.add(i);

        return result;
    }

    public static Integer maxDistanceBetweenTwoValues(int[] array) {
        int maxDistance = -1;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            Integer index = map.get(array[i]);
            if (index != null)
                maxDistance = Math.max(maxDistance, i - index);
            else
                map.put(array[i], i);
        }

        return maxDistance;
    }

    public static int topOccurence(int[] array){
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i : array){
            int value = map.merge(i, 1, (a, b) -> a+b);
            if(max < value)
                max = value;
        }

        return max;
    }

    public static int[] topKOccurence(int[] array, int k){
        int[] top = new int[k];
        Map<Integer, Integer> map = new HashMap<>();

        //TODO finish topk
//        for(int i : array)
//            map.merge(i, 1, (k, v) -> v == null ? 1 : v+1);


        return top;
    }

    public static void main(String[] args) {
        int arr1[] = {11, 15, 1, 13, 60, 21, 55, 3, 7, 10};
        int arr2[] = {60, 3, 7, 55, 1, 11};
        int arr3[] = {60, 3, 7, 55, 1, 11, 77};

        System.out.println("Array 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));
        System.out.println("Array 3: " + Arrays.toString(arr3));
        System.out.println("array 2 is a subset of array 1: " + isSubset(arr1, arr2));
        System.out.println("array 3 is a subset of array 1: " + isSubset(arr1, arr3));
        System.out.println("Intersection between arra 1 and array 3: " + intersection(arr1, arr3));

        int[] arr = {3, 2, 1, 2, 1, 4, 5, 8, 6, 7, 4, 2};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Max distance between two values in array: " + maxDistanceBetweenTwoValues(arr));
        System.out.println("Top occurence: " + topOccurence(arr));
//        System.out.println(Arrays.toString(topKOccurency(arr)));
    }
}
