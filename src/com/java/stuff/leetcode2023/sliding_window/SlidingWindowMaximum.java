package com.java.stuff.leetcode2023.sliding_window;

import java.util.Arrays;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        int j = 0;
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // clean last elements if they are smaller
            while(!l.isEmpty() && nums[l.getLast()]<=nums[i])
                l.removeLast();

            // add next
            l.addLast(i);

            // remove first out of window element when we slide
            if(l.getFirst() == i-k)
                l.removeFirst();

            if(i>=k-1)
                result[j++] = nums[l.peek()];
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum sw = new SlidingWindowMaximum();
        System.out.println(Arrays.toString(sw.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(sw.maxSlidingWindow(new int[]{1}, 1)));
        System.out.println(Arrays.toString(sw.maxSlidingWindow(new int[]{1, -1}, 1)));

//        Example 1:
//        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//        Output: [3,3,5,5,6,7]
//        Explanation:
//        Window position                Max
//                ---------------               -----
//        [1  3  -1] -3  5  3  6  7       3
//        1 [3  -1  -3] 5  3  6  7       3
//        1  3 [-1  -3  5] 3  6  7       5
//        1  3  -1 [-3  5  3] 6  7       5
//        1  3  -1  -3 [5  3  6] 7       6
//        1  3  -1  -3  5 [3  6  7]      7
//
//        Example 2:
//        Input: nums = [1], k = 1
//        Output: [1]
    }


}
