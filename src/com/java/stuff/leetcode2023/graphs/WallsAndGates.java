package com.java.stuff.leetcode2023.graphs;

import java.util.*;

//    Description
//    You are given a m x n 2D grid initialized with these three possible values.
//
//          -1 - A wall or an obstacle.
//          0 - A gate.
//          INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume
//              that the distance to a gate is less than 2147483647.
//    Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate,
//    that room should remain filled with INF
public class WallsAndGates {

    public void wallsAndGates(int[][] rooms) {
        // find gates
        List<int[]> gates = new ArrayList<>();
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0)
                    gates.add(new int[]{i, j, 0});

        for (int[] gate : gates) {
            boolean[][] visited = new boolean[rooms.length][rooms[0].length];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(gate);

            while (!queue.isEmpty()) {
                int[] tmp = queue.poll();
                int i = tmp[0];
                int j = tmp[1];
                int counter = tmp[2];
                rooms[i][j] = Math.min(rooms[i][j], counter);
                visited[i][j] = true;

                updateQueue(queue, rooms, visited, i + 1, j, counter);
                updateQueue(queue, rooms, visited, i - 1, j, counter);
                updateQueue(queue, rooms, visited, i, j + 1, counter);
                updateQueue(queue, rooms, visited, i, j - 1, counter);
            }
        }
    }

    void updateQueue(Queue<int[]> queue, int[][] rooms, boolean[][] visited, int i, int j, int counter) {
        if (i >= 0 && i < rooms.length &&
                j >= 0 && j < rooms[0].length &&
                !visited[i][j] && rooms[i][j] != -1)
            queue.add(new int[]{i, j, counter + 1});
    }


    public static void main(String[] args) {
        WallsAndGates wag = new WallsAndGates();

        int[][] rooms = new int[][]{
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}};
        wag.wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));

        rooms = new int[][]{
                {0, -1},
                {2147483647, 2147483647}};
        wag.wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));


//        Example1
//        Input:
//[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
//        Output:
//[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
//
//        Explanation:
//        the 2D grid is:
//        INF  -1  0  INF
//        INF INF INF  -1
//        INF  -1 INF  -1
//        0  -1 INF INF
//        the answer is:
//        3  -1   0   1
//        2   2   1  -1
//        1  -1   2  -1
//        0  -1   3   4
//
//        Example2
//        Input:
//[[0,-1],[2147483647,2147483647]]
//        Output:
//[[0,-1],[1,2]]
    }

}
