package com.java.stuff.leetcode.challenge.twenty.may;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public int majorityElement(int[] nums) {
        int limit = nums.length/2;
        Map<Integer, Integer> count = new HashMap<>();

        for(int num : nums){
            Integer times = count.getOrDefault(num, 0);
            if(times == limit)
                return num;
            else
                count.put(num, times+1);
        }

     return -1;
    }


    public int majorityElementFaster(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        MajorityElement me = new MajorityElement();
        System.out.println(me.majorityElement(new int[]{3,2,3}));
        System.out.println(me.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }


}
