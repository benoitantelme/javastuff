package com.java.stuff.leetcode.challenge.twenty.may;

public class MaximumSumCircularSubarray {

        //   max with kadane max subarray
        //   min with kadane min subarray
        //   if sum == min return max
        //   Otherwise, return maximum ( max, sum - min )
    public int maxSubarraySumCircular(int[] A) {
        if(A.length == 0)
            return 0;

        int sum = A[0];
        int tmpMax = sum;
        int max = sum;
        int min = sum;
        int tmpMin = sum;

        for(int i = 1; i < A.length; i++){
            int num = A[i];
            tmpMax = Math.max(num, tmpMax + num);
            max = Math.max(tmpMax, max);

            tmpMin = Math.min(num, tmpMin + num);
            min = Math.min(min, tmpMin);

            sum += num;
        }

        if(sum == tmpMin)
            return max;
        else
            return Math.max(sum - min, max);
    }

    public static void main(String[] args) {
        MaximumSumCircularSubarray max = new MaximumSumCircularSubarray();
        System.out.println(max.maxSubarraySumCircular(new int[]{1,-2,3,-2}));
        System.out.println(max.maxSubarraySumCircular(new int[]{5, -3, 5}));
        System.out.println(max.maxSubarraySumCircular(new int[]{3, -1, 2, -1}));
        System.out.println(max.maxSubarraySumCircular(new int[]{3, -2, 2, -3}));
        System.out.println(max.maxSubarraySumCircular(new int[]{-2, -3, -1}));
    }

}
