package com.java.stuff.leetcode2023.greedy;

public class MaximumSubarray {


    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int tmp = nums[0];

        for(int i = 1; i < nums.length; i++){
            tmp = Math.max(tmp + nums[i], nums[i]);
            max = Math.max(max, tmp);
        }

        return max;
    }

    public int notGreatMxSubArray(int[] nums) {
        int totalMax = nums[0];
        int current = 0;
        for (int n : nums) {
            if(current < 0)
                current = 0;

            current += n;
            totalMax = Math.max(totalMax, current);
        }

        return totalMax;
    }

    public static void main(String[] args) {
        MaximumSubarray ms = new MaximumSubarray();

        System.out.println(ms.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(ms.maxSubArray(new int[]{1}));
        System.out.println(ms.maxSubArray(new int[]{5, 4, -1, 7, 8}));

//        Example 1:
//        Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//        Output: 6
//        Explanation: The subarray [4,-1,2,1] has the largest sum 6.
//
//        Example 2:
//        Input: nums = [1]
//        Output: 1
//        Explanation: The subarray [1] has the largest sum 1.
//
//        Example 3:
//        Input: nums = [5,4,-1,7,8]
//        Output: 23
//        Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
    }

    public int quadraticMaxSubArray(int[] nums) {
        int totalMax = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int curMax = nums[i];
            if (curMax > totalMax)
                totalMax = curMax;


            int tmpMax = curMax;
            for (int j = i+1; j < nums.length; j++) {
                tmpMax += nums[j];
                if (tmpMax > totalMax)
                    totalMax = tmpMax;
            }
        }

        return totalMax;
    }

}
