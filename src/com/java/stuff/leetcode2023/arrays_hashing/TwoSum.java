package com.java.stuff.leetcode2023.arrays_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int nbr = nums[i];
            int diff = target - nbr;

            if (map.containsKey(nbr))
                return new int[]{map.get(nbr), i};

            map.put(diff, i);

        }

        return null;
    }


    public static void main(String args[]) {
        TwoSum ts = new TwoSum();

        System.out.println(Arrays.toString(ts.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{3, 3}, 6)));


    }

    public int[] betterTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++)
            map.put(target - nums[i], i);

        for (int i = 0; i < nums.length; i++)
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i)
                return new int[]{map.get(nums[i]), i};

        return null;
    }

    public int[] easyTwoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j)
                    continue;

                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
            }
        }

        return null;
    }


}
