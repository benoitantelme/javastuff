package com.java.stuff.leetcode.medium;

public class NonOverlappingIntervals {

    public static void main(String[] args) {
        NonOverlappingIntervals intervals = new NonOverlappingIntervals();

        if(intervals.eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}) != 1)
            System.out.println("wrong");

        if(intervals.eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}) != 2)
            System.out.println("wrong");

        if(intervals.eraseOverlapIntervals(new int[][]{{1,2},{2,3}}) != 0)
            System.out.println("wrong");

    }




    public int eraseOverlapIntervals(int[][] intervals) {

        return -1;
    }


//    Example 1:
//
//    Input: {{1,2},{2,3},{3,4},{1,3}}
//    Output: 1
//    Explanation: {1,3} can be removed and the rest of intervals are non-overlapping.
//            Example 2:
//
//    Input: {{1,2},{1,2},{1,2}}
//    Output: 2
//    Explanation: You need to remove two {1,2} to make the rest of intervals non-overlapping.
//            Example 3:
//
//    Input: {{1,2},{2,3}}
//    Output: 0
//    Explanation: You don't need to remove any of the intervals since they're already non-overlapping.



}
