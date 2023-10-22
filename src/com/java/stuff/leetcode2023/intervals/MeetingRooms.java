package com.java.stuff.leetcode2023.intervals;

import java.util.Arrays;

public class MeetingRooms {

    public boolean canAttendAll(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        int[] previous = null;
        for (int[] interval : intervals) {
            if (previous != null) {
                if (interval[0] < previous[1])
                    return false;
            }

            previous = interval;
        }

        return true;
    }

    public static void main(String[] args) {
        MeetingRooms mr = new MeetingRooms();

        System.out.println(mr.canAttendAll(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(mr.canAttendAll(new int[][]{{5, 8}, {9, 15}}));


//        Example1
//        Input: intervals = [(0,30),(5,10),(15,20)]
//        Output: false
//        Explanation:
//        (0,30), (5,10) and (0,30),(15,20) will conflict
//
//        Example2
//        Input: intervals = [(5,8),(9,15)]
//        Output: true
//        Explanation:
//        Two times will not conflict
    }

}
