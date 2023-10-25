package com.java.stuff.leetcode2023.backtracking;

import java.util.*;

public class SubsetsII {
    List<List<Integer>> result;

    void rec(int[] nums, int index, List<Integer> subset) {
        result.add(new ArrayList<>(subset));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1])
                continue;

            subset.add(nums[i]);
            rec(nums, i + 1, subset);
            subset.remove(subset.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result = new ArrayList<>();
        Arrays.sort(nums);
        rec(nums, 0, new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        SubsetsII s2 = new SubsetsII();

        System.out.println(s2.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(s2.subsetsWithDup(new int[]{0}));
        System.out.println(s2.subsetsWithDup(new int[]{4, 4, 4, 1, 4}));

//        Example 1:
//        Input: nums = [1,2,2]
//        Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
//
//        Example 2:
//        Input: nums = [0]
//        Output: [[],[0]]
    }

    Set<List<Integer>> result2;
    Set<Map<Integer, Integer>> present;

    void recOld(int[] nums, int index, List<Integer> subset) {
        if (index >= nums.length) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer i : subset)
                map.merge(i, 0, (x, y) -> x + 1);

            if (!present.contains(map)) {
                result2.add(new ArrayList<>(subset));
                present.add(map);
            }
        } else {
            subset.add(nums[index]);
            recOld(nums, index + 1, subset);
            subset.remove(subset.size() - 1);
            recOld(nums, index + 1, subset);
        }
    }

    public List<List<Integer>> slowSubsetsWithDup(int[] nums) {
        result2 = new HashSet<>();
        present = new HashSet<>();
        recOld(nums, 0, new ArrayList<>());
        return result2.stream().toList();
    }

}
