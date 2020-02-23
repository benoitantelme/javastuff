package com.java.stuff.leetcode.hard;

import java.util.*;

public class MedianSlidingWindow {



    public double[] medianSlidingWindow(int[] nums, int k) {
        int last;
        double[] result = new double[nums.length - k+ 1];

        List<Integer> sorted = new ArrayList<>();

        sorted.add(nums[0]);
        last = nums[0];
        for(int i = 1; i < k; i++)
            sorted.add(nums[i]);
        Collections.sort(sorted);

        if( k % 2 == 0){
            result[0] = ((double) sorted.get(k/2)+ sorted.get(k/2-1))/2;
        }else{
            result[0] = sorted.get(k/2);
        }

        for(int i = 1; i < nums.length-k+1; i++){
            sorted.remove(Integer.valueOf(last));
            sorted.add(nums[i+k-1]);
            Collections.sort(sorted);

            if( k % 2 == 0){
                result[i] = ((double) sorted.get(k/2)+ sorted.get(k/2-1))/2;
            }else{
                result[i] = sorted.get(k/2);
            }
            last = nums[i];
        }
        
        return result;
    }

    public static void main(String[] args){
        MedianSlidingWindow msw = new MedianSlidingWindow();
        System.out.println(Arrays.toString(msw.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));

    }

}
