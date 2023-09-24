package com.java.stuff.leetcode2023.arrays_hashing;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null)
            return false;

        Set<Integer> set = new HashSet<>();

        for (int n : nums) {
            if (set.contains(n))
                return true;
            else set.add(n);
        }

        return false;
    }

    public static void main(String args[]) {
        ContainsDuplicate cd = new ContainsDuplicate();

        int[] nums = {1, 2, 3, 1};
        System.out.println(cd.containsDuplicate(nums));

        nums = new int[]{1, 2, 3, 4};
        System.out.println(cd.containsDuplicate(nums));

        nums = new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(cd.containsDuplicate(nums));

        nums = new int[]{};
        System.out.println(cd.containsDuplicate(nums));

        nums = null;
        System.out.println(cd.containsDuplicate(nums));

    }

}
