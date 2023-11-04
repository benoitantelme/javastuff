package com.java.stuff.leetcode2023.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int time = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty() && fresh != 0) {
            time ++;
            int sz = queue.size();
            for (int i = 0; i < sz; i ++) {
                int[] rotten = queue.poll();
                for (int[] dir : dirs) {
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                        fresh -= 1;
                    }
                }
            }
        }

        return fresh == 0 ? time : -1;
    }

    public static void main(String[] args) {
        RottingOranges ro = new RottingOranges();

        System.out.println(ro.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(ro.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        System.out.println(ro.orangesRotting(new int[][]{{0, 2}}));
        System.out.println(ro.orangesRotting(new int[][]{{1}}));
        System.out.println(ro.orangesRotting(new int[][]{{1}, {1}, {1}, {1}}));
        System.out.println(ro.orangesRotting(new int[][]{{0}}));
        System.out.println(ro.orangesRotting(new int[][]{{2}, {2}, {1}, {0}, {1}, {1}, {1}}));
        System.out.println(ro.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 1}, {0, 1, 2}}));


//        Example 1:
//        Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//        Output: 4
//
//        Example 2:
//        Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//        Output: -1
//        Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
//
//                Example 3:
//        Input: grid = [[0,2]]
//        Output: 0
//        Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
    }

    // DFS DONT WORK
    public int orangesRottingFail(int[][] grid) {
        int rotten = 0;
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rotten++;
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0)
            return 0;
        if (rotten == 0)
            return -1;


        int time = 0;
        while (true) {
            int remaining = dfsFail(grid);
            if (remaining == -1)
                return -1;

            if (remaining == 0)
                return time;
            else
                time++;
        }
    }

    int dfsFail(int[][] grid) {
        int fresh = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                    if ((i == 0 || grid[i - 1][j] == 0) && (i == grid.length - 1 || grid[i + 1][j] == 0) &&
                            (j == 0 || grid[i][j - 1] == 0) && (j == grid[0].length - 1 || grid[i][j + 1] == 0))
                        return -1;
                } else if (grid[i][j] == 2 && !visited[i][j]) {
                    if (i > 0 && grid[i - 1][j] == 1) {
                        visited[i - 1][j] = true;
                        grid[i - 1][j] = 2;
                        fresh++;
                    }
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                        visited[i + 1][j] = true;
                        grid[i + 1][j] = 2;
                        fresh++;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        visited[i][j - 1] = true;
                        grid[i][j - 1] = 2;
                        fresh++;
                    }
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                        visited[i][j + 1] = true;
                        grid[i][j + 1] = 2;
                        fresh++;
                    }
                }
            }
        }

        return fresh;
    }

}
