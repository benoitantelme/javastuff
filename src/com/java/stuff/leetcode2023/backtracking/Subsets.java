package com.java.stuff.leetcode2023.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    List<List<Integer>> result;

    void rec(int[] nums, List<Integer> subset, int index) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(subset));
        } else {
            subset.add(nums[index]);
            rec(nums, subset, index + 1);
            subset.remove(subset.size() - 1);
            rec(nums, subset, index + 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        rec(nums, new ArrayList<>(), 0);
        return result;
    }

    public static void main(String[] args) {
        Subsets s = new Subsets();


        System.out.println(s.subsets(new int[]{1, 2, 3}));
        System.out.println(s.subsets(new int[]{0}));
    }

    void recBof(int[] nums, List<Integer> subset, int index) {
        result.add(new ArrayList<>(subset));

        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            rec(nums, subset, i + 1);
            subset.remove(subset.size() - 1);
        }

    }

}
