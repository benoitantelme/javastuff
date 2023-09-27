package com.java.stuff.leetcode2023.two_pointers;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length - 1;

        for (int k = 0; k < length - 1; k++) {
            if (k > 0 && nums[k - 1] == nums[k])
                continue;

            int first = nums[k];
            int i = k + 1;
            int j = length;

            int n, m;

            while (i < j) {
                n = nums[i];
                m = nums[j];

                if (n + m == -first) {
                    result.add(List.of(first, n, m));

                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }

                    i++;
                    j--;
                } else if (n + m < -first)
                    i++;
                else
                    j--;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();

        System.out.println(ts.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(ts.threeSum(new int[]{0, 1, 1}));
        System.out.println(ts.threeSum(new int[]{0, 0, 0}));
        System.out.println(ts.threeSum(new int[]{0, 0, 0, 0}));
    }

    public List<List<Integer>> withSetThreeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        Arrays.sort(nums);
        int length = nums.length - 1;

        for (int k = 0; k < length - 1; k++) {
            if (k > 0)
                while (nums[k - 1] == nums[k] && k < length - 1)
                    k++;

            int first = nums[k];
            int i = k + 1;
            int j = length;

            int n, m;

            while (i < j) {
                n = nums[i];
                m = nums[j];

                if (n + m == -first) {
                    List<Integer> l = List.of(first, n, m);
                    if (!set.contains(l)) {
                        result.add(l);
                        set.add(l);
                    }
                    i++;
                }

                if (n + m < -first) i++;

                if (n + m > -first) j--;
            }
        }

        return result;
    }

}
