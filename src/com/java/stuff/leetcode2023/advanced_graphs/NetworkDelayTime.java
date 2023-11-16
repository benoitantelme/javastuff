package com.java.stuff.leetcode2023.advanced_graphs;

import java.util.*;

public class NetworkDelayTime {

//    function Dijkstra(Graph, source):
//            for each vertex v in Graph.Vertices:
//                dist[v] ← INFINITY
//                prev[v] ← UNDEFINED
//
//            dist[source] ← 0
//            add (source, dist) to Q
//            while Q is not empty:
//                u ← poll Q with min dist
//
//                for each neighbor v of u:
//                    alt ← dist[u] + Graph.get(u).weight(v)
//                    if alt < dist[v]:
//                        dist[v] ← alt
//                        prev[v] ← u
//                        add (v, alt) to Q
//
//            return dist[], prev[]

    public int networkDelayTime(int[][] times, int n, int k) {
        // create graph
        List<int[]>[] graph = new List[n + 1];
        for (int[] edge : times) {
            if (graph[edge[1]] == null)
                graph[edge[1]] = new ArrayList<>();
            if (graph[edge[0]] == null)
                graph[edge[0]] = new ArrayList<>();

            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // set distances from k
        int[] distances = new int[n + 1];
        for (int i = 1; i < n + 1; i++)
            distances[i] = Integer.MAX_VALUE;
        distances[k] = 0;

        // initialise queue from
        Integer[] previous = new Integer[n + 1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int vertex = tmp[0];
            for (int[] neighbour : graph[vertex]) {
                int vNeighbour = neighbour[0];

                // updated distance
                int dis = distances[vertex] + neighbour[1];
                if (dis < distances[vNeighbour]) {
                    distances[vNeighbour] = dis;
                    previous[vNeighbour] = vertex;
                    queue.add(new int[]{vNeighbour, dis});
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int distance : distances)
            result = Math.max(result, distance);

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        NetworkDelayTime ndt = new NetworkDelayTime();

        System.out.println(ndt.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(ndt.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println(ndt.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));

//        Example 1:
//        Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//        Output: 2
//
//        Example 2:
//        Input: times = [[1,2,1]], n = 2, k = 1
//        Output: 1
//
//        Example 3:
//        Input: times = [[1,2,1]], n = 2, k = 2
//        Output: -1
    }

    // original
    //    function Dijkstra(Graph, source):
//            for each vertex v in Graph.Vertices:
//                dist[v] ← INFINITY
//                prev[v] ← UNDEFINED
//                add v to Q
//
//            dist[source] ← 0
//            while Q is not empty:
//                u ← vertex in Q with min dist[u]
//                remove u from Q
//
//                for each neighbor v of u still in Q:
//                    alt ← dist[u] + Graph.Edges(u, v)
//                    if alt < dist[v]:
//                        dist[v] ← alt
//                        prev[v] ← u
//
//            return dist[], prev[]

}
