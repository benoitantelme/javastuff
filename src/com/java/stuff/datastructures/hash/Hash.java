package com.java.stuff.datastructures.hash;

import java.util.*;

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

            //store first index or compare index with first index
            if (index != null)
                maxDistance = Math.max(maxDistance, i - index);
            else
                map.put(array[i], i);
        }

        return maxDistance;
    }

    public static int maxOccurrence(int[] array) {
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : array) {
            int value = map.merge(i, 1, (a, b) -> a + b);
            if (max < value)
                max = value;
        }

        return max;
    }

    /**
     * @param array
     * @param k
     * @return list of top k indices using a bucket sort like (complexity O(n))
     */
    public static List<Integer> topKOccurrences(int[] array, int k) {
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        // count occurrences and keep max
        for (int i : array) {
            int value = map.merge(i, 1, (a, b) -> a + b);
            if (max < value)
                max = value;
        }

        // Array of lists, frequency to numbers
        ArrayList<Integer>[] arr = new ArrayList[max + 1];
        for (int i = 1; i <= max; i++)
            arr[i] = new ArrayList<>();

        // filling the array
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            arr[entry.getValue()].add(entry.getKey());

        // return top k numbers
        List<Integer> result = new ArrayList<>();
        for (int j = max; j >= 1; j--) {
            if (!arr[j].isEmpty()) {
                for (int a : arr[j]) {
                    result.add(a);

                    if (result.size() == k) {
                        return result;
                    }
                }
            }
        }

        return result;
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
        System.out.println("Max occurence: " + maxOccurrence(arr));
        System.out.println("Top k: " + topKOccurrences(arr, 4));
    }
}
