package com.java.stuff.leetcode2023.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsTwo {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> x[1] - y[1]);

        int max = 0;
        for (int[] interval : intervals) {
            while (!heap.isEmpty() && interval[0] >= heap.peek()[1])
                heap.poll();

            heap.add(interval);

            max = Math.max(max, heap.size());
        }

        return max;
    }

    public static void main(String[] args) {
        MeetingRoomsTwo mr = new MeetingRoomsTwo();

        System.out.println(mr.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(mr.minMeetingRooms(new int[][]{{5, 8}, {9, 15}}));


//        Example1
//        Input: intervals = [(0,30),(5,10),(15,20)]
//        Output: 2
//        Explanation:
//        We need two meeting rooms
//        room1: (0,30)
//        room2: (5,10),(15,20)
//
//        Example2
//        Input: intervals = [(2,7)]
//        Output: 1
//        Explanation:
//        Only need one meeting room
    }

}
