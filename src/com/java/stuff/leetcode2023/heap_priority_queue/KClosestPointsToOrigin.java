package com.java.stuff.leetcode2023.heap_priority_queue;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    class Point {
        int[] point;
        double distance;

        public Point(int[] point, double distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<Point> heap = new PriorityQueue<>((x, y) -> Double.compare(x.distance, y.distance));

        for (int[] point : points)
            heap.add(new Point(point,
                    Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2))));

        int[][] result = new int[k][];
        for (int i = 0; i < k; i++)
            result[i] = heap.poll().point;

        return result;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin kc = new KClosestPointsToOrigin();
        System.out.println(Arrays.deepToString(kc.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));
        System.out.println(Arrays.deepToString(kc.kClosest(new int[][]{{3, 3}, {-2, 4}, {5, 1}}, 2)));
        System.out.println(Arrays.deepToString(kc.kClosest(
                new int[][]{{2,10},{-9,-9},{0,8},{-2,-2},{8,9},{-10,-7},{-5,2},{-4,-9}}, 7)));

//        Example 1:
//        Input: points = [[1,3],[-2,2]], k = 1
//        Output: [[-2,2]]
//        Explanation:
//        The distance between (1, 3) and the origin is sqrt(10).
//                The distance between (-2, 2) and the origin is sqrt(8).
//                Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
//                We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
//
//        Example 2:
//        Input: points = [[3,3],[5,-1],[-2,4]], k = 2
//        Output: [[3,3],[-2,4]]
//        Explanation: The answer [[-2,4],[3,3]] would also be accepted.
    }

}
