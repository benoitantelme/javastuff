package com.java.stuff.leetcode.medium;

import java.util.*;

public class GraphMedium {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
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

    public boolean canReach(int[] arr, int start) {
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

    public int countServers(int[][] grid) {
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


    private boolean isSafe(int[][] graph, int vertex, int[] visited) {
        visited[vertex] = 1;

        for (int edge : graph[vertex]) {
            if (visited[edge] == 1)
                return false;
            if (visited[edge] == 0 && !isSafe(graph, edge, visited))
                return false;
        }

        visited[vertex] = -1;
        return true;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (isSafe(graph, i, new int[graph.length]))
                result.add(i);
        }

        return result;
    }

    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            int color = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            if (colors[i] == 0)
                colors[i] = color;
            while (!queue.isEmpty()) {
                Integer current = queue.poll();
                if (colors[current] == 0)
                    colors[current] = color;
                for (int neighbour : graph[current]) {
                    if (colors[neighbour] == 0) {
                        queue.add(neighbour);
                        colors[neighbour] = -colors[current];
                    } else if (colors[neighbour] == colors[current])
                        return false;
                }
            }
        }

        return true;
    }


    public int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int[] edges = times[i];
            map.computeIfAbsent(edges[0], k -> new ArrayList()).add(new int[]{edges[1], edges[2]});
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(info -> info[1]));
        heap.offer(new int[]{K, 0});

        Map<Integer, Integer> distances = new HashMap<>();
        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int node = current[0];
            int dist = current[1];

            if(distances.containsKey(node))
                continue;

            distances.put(node, dist);
            if(!map.containsKey(node))
                continue;

            for(int[] neighbour : map.get(node)){
                int neighbourNode = neighbour[0];
                int neighbourDistance = neighbour[1];
                if(!distances.containsKey(neighbourNode))
                    heap.add(new int[]{neighbourNode, dist+neighbourDistance});
            }
        }

        if (distances.size() <= N-1)
            return -1;
        int result = 0;
        for (int dist: distances.values())
            result = Math.max(result, dist);
        return result;
    }

    public static void main(String[] args) {
        GraphMedium gm = new GraphMedium();
        System.out.println(gm.canVisitAllRooms(List.of(List.of(1), List.of(2), List.of(3), new ArrayList<>())));

        System.out.println(gm.canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));

        System.out.println(gm.countServers(new int[][]{{1, 0}, {0, 1}}));
        System.out.println(gm.countServers(new int[][]{{1, 0}, {1, 1}}));
        System.out.println(gm.countServers(new int[][]
                {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}));
        System.out.println(gm.countServers(new int[][]
                {{1, 0, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}}));

        System.out.println(gm.eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
        System.out.println(gm.eventualSafeNodes(new int[][]{{}, {0, 2, 3, 4}, {3}, {4}, {}}));

        System.out.println(gm.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        System.out.println(gm.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));

        System.out.println(gm.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(gm.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}}, 3, 1));
        System.out.println(gm.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 7}, {1, 3, 4}, {2, 1, 2}}, 3, 2));
        System.out.println(gm.networkDelayTime(new int[][]{{1, 2, 1}, {2, 1, 3}}, 2, 2));
        System.out.println(gm.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
        System.out.println(gm.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
    }

}
