package com.java.stuff.leetcode2023.linked_list;

import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        boolean[] found = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (found[nums[i]])
                return nums[i];
            else
                found[nums[i]] = true;
        }

        return 0;
    }


    public static void main(String[] args) {
        FindTheDuplicateNumber fdn = new FindTheDuplicateNumber();

        System.out.println(fdn.findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(fdn.findDuplicate(new int[]{3, 1, 3, 4, 2}));


//        Example 1:
//        Input: nums = [1,3,4,2,2]
//        Output: 2

//        Example 2:
//        Input: nums = [3,1,3,4,2]
//        Output: 3
    }

    public int slowSetfindDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int n : nums) {
            if (set.contains(n))
                return n;
            else
                set.add(n);
        }

        return 0;
    }

}
