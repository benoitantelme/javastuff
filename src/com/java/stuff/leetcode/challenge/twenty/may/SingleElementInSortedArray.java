package com.java.stuff.leetcode.challenge.twenty.may;

public class SingleElementInSortedArray {


    public int singleNonDuplicateSlow(int[] nums) {
        int previous = nums[0];
        for (int i = 1; i < nums.length; i += 2) {
            if (nums[i] != previous)
                return previous;

            previous = nums[i + 1];
        }

        return nums[nums.length - 1];
    }

    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] == nums[left + 1])
                left = left + 2;
            else
                return nums[left];

            if (nums[right] == nums[right - 1])
                right = right - 2;
            else
                return nums[right];

        }
        return -1;
    }

    public static void main(String[] args) {
        SingleElementInSortedArray seisa = new SingleElementInSortedArray();
        System.out.println(seisa.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(seisa.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
        System.out.println(seisa.singleNonDuplicate(new int[]{1, 1, 2, 3, 3}));
    }


}
