package com.java.stuff.leetcode2023.advanced_graphs;

import java.util.*;

public class MinCostToConnectAllPoints {

    public class Edge {
        int node;
        int distance;

        public Edge(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return node == edge.node && distance == edge.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, distance);
        }
    }

    public int minCostConnectPoints(int[][] points) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < points.length; j++) {
                // |xi - xj| + |yi - yj|
                if (i != j)
                    edges.add(new Edge(j,
                            Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
            graph.add(edges);
        }

        int result = 0;
//        int[] parents = new int[points.length];

        // min distance
        int[] weight = new int[points.length];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[0] = 0;

        // in the parents
        Set<Integer> processed = new HashSet<>();

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{0, weight[0]});
        while (!queue.isEmpty()) {
            int[] edge = queue.poll();
            int vertex = edge[0];
            if (processed.contains(edge[0]))
                continue;
            else
                processed.add(vertex);
            result += edge[1];

            for (Edge neighbour : graph.get(vertex))
                if (!processed.contains(neighbour.node) && weight[neighbour.node] > neighbour.distance) {
                    weight[neighbour.node] = neighbour.distance;
//                    parents[neighbour.node] = vertex;
                    queue.add(new int[]{neighbour.node, neighbour.distance});
                }
        }

        return result;
    }


    public static void main(String[] args) {
        MinCostToConnectAllPoints mct = new MinCostToConnectAllPoints();

        System.out.println(mct.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
        System.out.println(mct.minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}));


//        Example 1:
//        Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//        Output: 20
//        Explanation:
//        We can connect the points as shown above to get the minimum cost of 20.
//        Notice that there is a unique path between every pair of points.
//
//        Example 2:
//        Input: points = [[3,12],[-2,5],[-4,1]]
//        Output: 18
    }

}
