package com.java.stuff.leetcode2023.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {
    List<List<Integer>> result;

    void rec(List<Integer> input, List<Integer> set) {
        if (input.isEmpty()) {
            result.add(set);
            return;
        }

        for (Integer n : input) {
            List<Integer> updatedSet = new ArrayList<>(set);
            updatedSet.add(n);
            List<Integer> updatedIn = new ArrayList<>(input);
            updatedIn.remove(n);
            rec(updatedIn, updatedSet);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        rec(Arrays.stream(nums).boxed().collect(Collectors.toList()), new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();

        System.out.println(p.permute(new int[]{1, 2, 3}));
        System.out.println(p.permute(new int[]{0, 1}));
        System.out.println(p.permute(new int[]{1}));

//        Example 1:
//        Input: nums = [1,2,3]
//        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//        Example 2:
//        Input: nums = [0,1]
//        Output: [[0,1],[1,0]]
//
//        Example 3:
//        Input: nums = [1]
//        Output: [[1]]
    }

}
