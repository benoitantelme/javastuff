package com.java.stuff.leetcode2023.binary_search;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int result = -1;

        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int p = (l + r) / 2;

            if (target == nums[p])
                return p;

            // sorted left
            if (nums[l] <= nums[p]) {
                if (target > nums[p] || target < nums[l]) {
                    l = p + 1;
                } else {
                    r = p - 1;
                }
            } else {
                // sorted right
                if (target < nums[p] || target > nums[r]) {
                    r = p - 1;
                } else {
                    l = p + 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray sir = new SearchInRotatedSortedArray();

        System.out.println(sir.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(sir.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(sir.search(new int[]{1}, -1));
        System.out.println(sir.search(new int[]{1}, 1));
        System.out.println(sir.search(new int[]{5, 1, 3}, 3));
        System.out.println(sir.search(new int[]{3, 1}, 1));

//        Example 1:
//        Input: nums = [4,5,6,7,0,1,2], target = 0
//        Output: 4
//
//        Example 2:
//        Input: nums = [4,5,6,7,0,1,2], target = 3
//        Output: -1
//
//        Example 3:
//        Input: nums = [1], target = 0
//        Output: -1
    }

}
