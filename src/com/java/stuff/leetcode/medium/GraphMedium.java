package com.java.stuff.leetcode.medium;

import java.util.*;

public class GraphMedium {

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < rooms.size(); i++)
            set.add(i);

        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            if (visited[current])
                continue;

            set.remove(current);
            visited[current] = true;

            if (!rooms.get(current).isEmpty())
                for (Integer room : rooms.get(current))
                    queue.add(room);

        }

        return set.isEmpty();
    }

    public static boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            if (visited[current])
                continue;

            visited[current] = true;
            int move = arr[current];
            if (move == 0)
                return true;

            if (current + move <= arr.length - 1)
                queue.add(current + move);
            if (current - move >= 0)
                queue.add(current - move);
        }

        return false;
    }

    public static int countServers(int[][] grid) {
        int[] row = new int[grid.length];
        int[] column = new int[grid[0].length];
        Set<int[]> servers = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    row[i] += 1;
                    column[j] += 1;
                    servers.add(new int[]{i, j});
                }
            }
        }

        int result = 0;

        for (int[] server : servers)
            if (row[server[0]] > 1 || column[server[1]] > 1)
                result++;

        return result;
    }


    private static boolean isSafe(int[][] graph, int vertex, int[] visited) {
        visited[vertex] = 1;

        for(int edge : graph[vertex]){
            if(visited[edge] == 1)
                return false;
            if(visited[edge] == 0 && !isSafe(graph, edge, visited))
                return false;
        }

        visited[vertex] = -1;
        return true;
    }
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (isSafe(graph, i, new int[graph.length]))
                result.add(i);
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(canVisitAllRooms(List.of(List.of(1), List.of(2), List.of(3), new ArrayList<>())));

        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));

        System.out.println(countServers(new int[][]{{1, 0}, {0, 1}}));
        System.out.println(countServers(new int[][]{{1, 0}, {1, 1}}));
        System.out.println(countServers(new int[][]
                {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}));
        System.out.println(countServers(new int[][]
                {{1, 0, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}}));

        System.out.println(eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
        System.out.println(eventualSafeNodes(new int[][]{{},{0,2,3,4},{3},{4},{}}));

    }


}
