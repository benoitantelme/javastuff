package com.java.stuff.leetcode2023.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int tmp = explore(grid, i, j);
                    max = Math.max(max, tmp);
                }
            }
        }

        return max;
    }

    int explore(int[][] grid, int i, int j) {
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            if (grid[tmp[0]][tmp[1]] == 1) {
                res++;
                grid[tmp[0]][tmp[1]] = 0;
                if (isValid(grid, tmp[0] + 1, tmp[1]))
                    queue.add(new int[]{tmp[0] + 1, tmp[1]});

                if (isValid(grid, tmp[0], tmp[1] + 1))
                    queue.add(new int[]{tmp[0], tmp[1] + 1});

                if (isValid(grid, tmp[0] - 1, tmp[1]))
                    queue.add(new int[]{tmp[0] - 1, tmp[1]});

                if (isValid(grid, tmp[0], tmp[1] - 1))
                    queue.add(new int[]{tmp[0], tmp[1] - 1});
            }
        }

        return res;
    }

    boolean isValid(int[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length &&
                j >= 0 && j < grid[0].length)
            return true;

        return false;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland maoi = new MaxAreaOfIsland();


        System.out.println(maoi.maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));

        System.out.println(maoi.maxAreaOfIsland(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0}}));

//        Example 1:
//        Input: grid =
//                {{0,0,1,0,0,0,0,1,0,0,0,0,0},
//                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
//                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
//                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
//                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                        {0,0,0,0,0,0,0,1,1,0,0,0,0}}
//        Output: 6
//        Explanation: The answer is not 11, because the island must be connected 4-directionally.
//
//                Example 2:
//        Input: grid = {{0,0,0,0,0,0,0,0}}
//        Output: 0
    }
}
