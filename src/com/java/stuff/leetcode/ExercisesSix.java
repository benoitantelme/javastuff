package com.java.stuff.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExercisesSix {

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> hashset = new HashSet<>();
        for (int n : nums1)
            hashset.add(n);

        HashSet<Integer> result = new HashSet<>();

        for (int i : nums2)
            if (hashset.contains(i))
                result.add(i);

        return result.stream().mapToInt(Number::intValue).toArray();
    }

    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;

        while (i < nums.length) {
            int num = nums[i];
            if (num != 0) {
                if (j < i) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                i++;
                j++;
            } else
                i++;
        }
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));

        int[] a = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(a));
        moveZeroes(a);
        System.out.println(Arrays.toString(a));
    }

}
