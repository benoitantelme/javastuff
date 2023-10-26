package com.java.stuff.leetcode2023.backtracking;

import java.util.*;

public class CombinationSumII {
    List<List<Integer>> result;

    void rec(int[] candidates, int index, List<Integer> set, int target) {
        if (0 == target) {
            result.add(new ArrayList<>(set));
            return;
        }

        if (target < 0)
            return;

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i - 1] == candidates[i])
                continue;

            set.add(candidates[i]);
            rec(candidates, i + 1, set, target - candidates[i]);
            set.remove(set.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result = new ArrayList<>();
        Arrays.sort(candidates);
        rec(candidates, 0, new ArrayList<>(), target);
        return result;
    }

    public static void main(String[] args) {
        CombinationSumII cs = new CombinationSumII();
        System.out.println(cs.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(cs.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
        System.out.println(cs.combinationSum2(new int[]{2}, 1));

//        Example 1:
//        Input: candidates = [10,1,2,7,6,1,5], target = 8
//        Output:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]
//        Example 2:
//        Input: candidates = [2,5,2,1,2], target = 5
//        Output:
//[
//[1,2,2],
//[5]
//]
    }

    List<List<Integer>> result2;
    int target2;
    Set<Map<Integer, Integer>> done2;

    void rec2(int[] candidates, int index, List<Integer> set) {
        int sum = set.stream().mapToInt(i -> i).sum();
        if (sum == target2) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int n : set)
                map.merge(n, 0, (x, y) -> x + 1);

            if (!done2.contains(map)) {
                result2.add(new ArrayList<>(set));
                done2.add(map);
            }

            return;
        }

        if (index >= candidates.length || sum > target2)
            return;

        set.add(candidates[index]);
        rec2(candidates, index + 1, set);
        set.remove(set.size() - 1);
        rec2(candidates, index + 1, set);
    }

    public List<List<Integer>> combinationSum2Slow(int[] candidates, int target) {
        result2 = new ArrayList<>();
        done2 = new HashSet<>();
        this.target2 = target;
        rec2(candidates, 0, new ArrayList<>());
        return result2;
    }

}