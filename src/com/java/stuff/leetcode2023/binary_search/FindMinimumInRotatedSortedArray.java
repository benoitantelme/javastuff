package com.java.stuff.leetcode2023.binary_search;

public class FindMinimumInRotatedSortedArray {


    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            if (nums[l] <= nums[r])
                return nums[l];
            int pivot = (l + r) / 2;

            if (nums[l] <= nums[pivot])
                l = pivot + 1;
            else
                r = pivot;
        }

        return 0;
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray fm = new FindMinimumInRotatedSortedArray();

        System.out.println(fm.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(fm.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(fm.findMin(new int[]{11, 13, 15, 17}));
        System.out.println(fm.findMin(new int[]{1}));

//        Example 1:
//        Input: nums = [3,4,5,1,2]
//        Output: 1
//        Explanation: The original array was [1,2,3,4,5] rotated 3 times.
//
//                Exaple 2:
//        Input: nums = [4,5,6,7,0,1,2]
//        Output: 0
//        Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
//
//                Example 3:
//        Input: nums = [11,13,15,17]
//        Output: 11
//        Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
    }
}
