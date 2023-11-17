package com.java.stuff.leetcode2023.advanced_graphs;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] selected = new boolean[n][n];
        int[][] distances = new int[n][n];
        for (int[] row : distances)
            Arrays.fill(row, Integer.MAX_VALUE);
        distances[0][0] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{0, 0, grid[0][0]});
        int max = 0;
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int i = tmp[0];
            int j = tmp[1];
            max = Math.max(max, tmp[2]);

            if (i == n - 1 && j == n - 1)
                break;

            if (i > 0)
                if (!selected[i - 1][j]) {
                    int move = distances[i][j] + grid[i - 1][j];
                    if (move < distances[i - 1][j]) {
                        distances[i - 1][j] = move;
                        queue.add(new int[]{i - 1, j, grid[i - 1][j]});
                    }
                }
            if (i < n - 1)
                if (!selected[i + 1][j]) {
                    int move = distances[i][j] + grid[i + 1][j];
                    if (move < distances[i + 1][j]) {
                        distances[i + 1][j] = move;
                        queue.add(new int[]{i + 1, j, grid[i + 1][j]});
                    }
                }
            if (j > 0)
                if (!selected[i][j - 1]) {
                    int move = distances[i][j] + grid[i][j - 1];
                    if (move < distances[i][j - 1]) {
                        distances[i][j - 1] = move;
                        queue.add(new int[]{i, j - 1, grid[i][j - 1]});
                    }
                }
            if (j < n - 1)
                if (!selected[i][j + 1]) {
                    int move = distances[i][j] + grid[i][j + 1];
                    if (move < distances[i][j + 1]) {
                        distances[i][j + 1] = move;
                        queue.add(new int[]{i, j + 1, grid[i][j + 1]});
                    }
                }

            selected[i][j] = true;
        }

        return max;
    }

    public static void main(String[] args) {
        SwimInRisingWater sirw = new SwimInRisingWater();

        System.out.println(sirw.swimInWater(new int[][]{{0, 2}, {1, 3}}));
        System.out.println(sirw.swimInWater(new int[][]{
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}}));
        System.out.println(sirw.swimInWater(new int[][]{{3, 2}, {0, 1}}));
        System.out.println(sirw.swimInWater(new int[][]{
                {7, 23, 21, 9, 5},
                {3, 20, 8, 18, 15},
                {14, 13, 1, 0, 22},
                {2, 10, 24, 17, 12},
                {6, 16, 19, 4, 11}}));


//        Example 1:
//        Input:
//        grid = [[0, 2],[1, 3]]
//        Output:
//        3
//        Explanation:
//        At time 0, you are in grid location (0, 0).
//        You cannot go anywhere else because 4 - directionally adjacent neighbors have a higher elevation than t = 0.
//        You cannot reach point (1, 1)until time 3.
//        When the depth of water is 3, we can swim anywhere inside the grid.
//
//        Example 2:
//        Input:
//        grid = [[0, 1, 2, 3, 4],[24, 23, 22, 21, 5],[12, 13, 14, 15, 16],[11, 17, 18, 19, 20],[10, 9, 8, 7, 6]]
//        Output:
//        16
//        Explanation:
//        The final route is shown.
//                We need to wait until time 16 so that (0, 0)and(4, 4) are connected.
    }

}
