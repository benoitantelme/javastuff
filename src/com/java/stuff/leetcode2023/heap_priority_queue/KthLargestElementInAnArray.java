package com.java.stuff.leetcode2023.heap_priority_queue;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int n : nums) {
            if (heap.size() < k)
                heap.add(n);
            else if (n > heap.peek()) {
                heap.poll();
                heap.add(n);
            }
        }

        return heap.poll();
    }

    public static void main(String[] args) {
        KthLargestElementInAnArray kleia = new KthLargestElementInAnArray();

        System.out.println(kleia.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(kleia.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));

//        Example 1:
//        Input: nums = [3,2,1,5,6,4], k = 2
//        Output: 5
//
//        Example 2:
//        Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
//        Output: 4
    }

}
