package com.java.stuff.leetcode2023.graphs;

import java.util.*;

public class RedundantConnection {

    boolean dfs(List<List<Integer>> graph, int i, int j, boolean[] visited) {
        // loop
        if (i == j)
            return true;

        visited[i] = true;
        for (int neighbor : graph.get(i))
            if (!visited[neighbor])
                if (dfs(graph, neighbor, j, visited))
                    return true;

        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;

            boolean[] visited = new boolean[n];
            if (dfs(graph, u, v, visited)) {
                return edge;
            }

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return new int[0];
    }

    public static void main(String[] args) {
        RedundantConnection rc = new RedundantConnection();

        System.out.println(Arrays.toString(rc.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
        System.out.println(Arrays.toString(rc.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}})));

//        Example 1:
//        Input: edges = [[1,2],[1,3],[2,3]]
//        Output: [2,3]
//
//        Example 2:
//        Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
//        Output: [1,4]
    }

}
