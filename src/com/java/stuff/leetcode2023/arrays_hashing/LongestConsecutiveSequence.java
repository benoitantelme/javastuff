package com.java.stuff.leetcode2023.arrays_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        Set<Integer> set = new HashSet<>();
        for(int n : nums)
            set.add(n);

        int max = 1;
        for (int n : nums) {
            int tmpMax = 1;
            if (!set.contains(n-1)) {
                // start of sequence
                while(set.contains(n+1)){
                    tmpMax++;
                    n++;
                }
            }

            if(max < tmpMax)
                max = tmpMax;
        }

        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();

        System.out.println(lcs.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(lcs.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(lcs.longestConsecutive(new int[]{1, 2, 0, 1}));

    }

    public int slowLongestConsecutive(int[] nums) {
        // actually faster because the sort is super fast in java ...
        if (nums.length == 0)
            return 0;

        int[] arr = Arrays.stream(nums).distinct().sorted().toArray();

        int max = 1;
        int tmpMax = 1;
        int previous = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int n = arr[i];
            if (arr[i] - previous == 1) {
                tmpMax += 1;
            } else {
                tmpMax = 1;
            }

            if (max < tmpMax)
                max = tmpMax;

            previous = n;
        }

        return max;
    }

}
