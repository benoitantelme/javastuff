package com.java.stuff.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (result.isEmpty())
                result.add(interval);
            else {
                int[] old = result.get(result.size() - 1);

                if (interval[0] < old[1]) {
                    if (interval[1] > old[1])
                        result.set(result.size() - 1, old);
                    else
                        result.set(result.size() - 1, interval);
                } else {
                    result.add(interval);
                }
            }
        }

        return intervals.length - result.size();
    }

    public static void main(String[] args) {
        NonOverlappingIntervals noi = new NonOverlappingIntervals();

        System.out.println(noi.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
        System.out.println(noi.eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}}));
        System.out.println(noi.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}}));
        System.out.println(noi.eraseOverlapIntervals(new int[][]{{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99},
                {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}}));

//        Example 1:
//        Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
//        Output: 1
//        Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
//
//                Example 2:
//        Input: intervals = [[1,2],[1,2],[1,2]]
//        Output: 2
//        Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
//
//                Example 3:
//        Input: intervals = [[1,2],[2,3]]
//        Output: 0
//        Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
    }

}
