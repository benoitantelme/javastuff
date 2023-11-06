package com.java.stuff.leetcode2023.graphs;

import java.util.*;

public class NumberConnCompsInUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjacency.put(i, new ArrayList<>());
            set.add(i);
        }

        for (int[] edge : edges) {
            adjacency.get(edge[0]).add(edge[1]);
            adjacency.get(edge[1]).add(edge[0]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!set.contains(i))
                continue;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            boolean[] visited = new boolean[n];
            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                if (visited[tmp])
                    continue;

                queue.addAll(adjacency.get(tmp));
                visited[tmp] = true;
                set.remove(tmp);
            }

            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        NumberConnCompsInUndirectedGraph ncc = new NumberConnCompsInUndirectedGraph();

        System.out.println(ncc.countComponents(3, new int[][]{{0, 1}, {0, 2}}));
        System.out.println(ncc.countComponents(6, new int[][]{{0, 1}, {1, 2}, {2, 3}, {4, 5}}));

//        Example 1
//        Input:
//        3
//                [[0,1], [0,2]]
//        Output:
//        1
//
//        Example 2
//        Input:
//        6
//                [[0,1], [1,2], [2, 3], [4, 5]]
//        Output:
//        2
    }

}
