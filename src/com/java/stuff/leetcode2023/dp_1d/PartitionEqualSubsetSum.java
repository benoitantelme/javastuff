package com.java.stuff.leetcode2023.dp_1d;

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums)
            total += num;

        if (total % 2 != 0)
            return false;

        int target = total / 2;

        boolean[] cache = new boolean[target];
        cache[0] = true;

        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                if (cache[i - num]) {
                    if (i == target)
                        return true;

                    cache[i] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum pes = new PartitionEqualSubsetSum();

        System.out.println(pes.canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(pes.canPartition(new int[]{1, 2, 3, 5}));


//        Example 1:
//        Input: nums = [1,5,11,5]
//        Output: true
//        Explanation: The array can be partitioned as [1, 5, 5] and [11].
//
//                Example 2:
//        Input: nums = [1,2,3,5]
//        Output: false
//        Explanation: The array cannot be partitioned into equal sum subsets.
    }

    boolean rec(int[] nums, int index, int target, int remains) {
        if (remains == 0)
            return true;

        if (index < 0)
            return false;

        if (rec(nums, index - 1, target, remains - nums[index]))
            return true;

        return rec(nums, index - 1, target, remains);
    }

    public boolean canPartitionTopDownTooSLow(int[] nums) {
        int total = 0;
        for (int num : nums)
            total += num;

        if (total % 2 != 0)
            return false;

        return rec(nums, nums.length - 1, total / 2, total / 2);
    }

}