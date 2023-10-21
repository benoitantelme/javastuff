package com.java.stuff.leetcode2023.heap_priority_queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {


    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1)
            return stones[0];

        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int n : stones)
            heap.add(n);

        while (heap.size() > 1) {
            int first = heap.poll();
            int second = heap.poll();
            ;

            if (first == second)
                continue;

            int res = first - second;
            if (res != 0)
                heap.add(res);
        }

        if (heap.isEmpty())
            return 0;
        else
            return heap.poll();
    }

    public static void main(String[] args) {
        LastStoneWeight lsw = new LastStoneWeight();


        System.out.println(lsw.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lsw.lastStoneWeight(new int[]{1}));

//        Example 1:
//        Input: stones = [2,7,4,1,8,1]
//        Output: 1
//        Explanation:
//        We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
//                we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
//                we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
//                we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
//
//        Example 2:
//        Input: stones = [1]
//        Output: 1
    }

}
