package com.java.stuff.leetcode2023.binary_search;

public class BinarySearch {

    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while (i <= j) {
            int pivot = (i + j) / 2;
            if (nums[pivot] == target)
                return pivot;
            else if (nums[pivot] > target)
                j = pivot - 1;
            else
                i = pivot + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        System.out.println(bs.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));

//        Example 1:
//        Input: nums = [-1,0,3,5,9,12], target = 9
//        Output: 4
//        Explanation: 9 exists in nums and its index is 4
//
//        Example 2:
//        Input: nums = [-1,0,3,5,9,12], target = 2
//        Output: -1
//        Explanation: 2 does not exist in nums so return -1
    }
}
