package com.java.stuff.leetcode2023.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> result;
    int target;


    void rec(int[] candidates, int index, List<Integer> subset) {
        int sum = subset.stream().mapToInt(i -> i).sum();
        if (sum == target) {
            result.add(new ArrayList<>(subset));
            return;
        }
        if (index > candidates.length - 1)
            return;

        if (sum < target) {
            subset.add(candidates[index]);
            rec(candidates, index, subset);
            subset.remove(subset.size() - 1);
            rec(candidates, index + 1, subset);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        this.target = target;
        rec(candidates, 0, new ArrayList<>());
        return result;
    }


    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        System.out.println(cs.combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(cs.combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(cs.combinationSum(new int[]{2}, 1));

//        Example 1:
//        Input: candidates = [2,3,6,7], target = 7
//        Output: [[2,2,3],[7]]
//        Explanation:
//        2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
//        7 is a candidate, and 7 = 7.
//        These are the only two combinations.
//
//        Example 2:
//        Input: candidates = [2,3,5], target = 8
//        Output: [[2,2,2,2],[2,3,3],[3,5]]
//
//        Example 3:
//        Input: candidates = [2], target = 1
//        Output: []
    }

}
