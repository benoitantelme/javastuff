package com.java.stuff.leetcode2023.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (result.isEmpty())
                result.add(interval);

            int[] old = result.get(result.size() - 1);
            if (old[1] < interval[0])
                result.add(interval);
            else if (interval[1] < old[0]) {
                result.set(result.size() - 1, interval);
                result.add(old);
            } else {
                result.set(result.size() - 1, new int[]{Math.min(old[0], interval[0]), Math.max(old[1], interval[1])});
            }
        }

        return result.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();

        System.out.println(Arrays.deepToString(mi.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(mi.merge(new int[][]{{1, 4}, {4, 5}})));


//        Example 1:
//        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//        Output: [[1,6],[8,10],[15,18]]
//        Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//
//        Example 2:
//        Input: intervals = [[1,4],[4,5]]
//        Output: [[1,5]]
//        Explanation: Intervals [1,4] and [4,5] are considered overlapping.
    }
}
