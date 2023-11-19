package com.java.stuff.leetcode2023.advanced_graphs;

import java.util.*;

public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // build graph
        Map<Integer, Integer>[] adjacency = new Map[n];
        for (int i = 0; i < n; i++)
            adjacency[i] = new HashMap<>();
        for (int[] flight : flights)
            adjacency[flight[0]].put(flight[1], flight[2]);


        int[] weights = new int[n];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[src] = 0;

        int result = Integer.MAX_VALUE;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[]{src, weights[src], 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int no = node[0];
            int weight = node[1];
            int counter = node[2];

            if (counter > k + 1)
                continue;
            if (no == dst)
                result = Math.min(result, weight);

            for (Map.Entry<Integer, Integer> neighbour : adjacency[no].entrySet()) {
                int sum = weight + neighbour.getValue();
                int nNode = neighbour.getKey();
                if (sum < weights[nNode]) {
                    weights[nNode] = sum;
                    queue.add(new int[]{nNode, sum, counter + 1});
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cfwks = new CheapestFlightsWithinKStops();
        System.out.println(cfwks.findCheapestPrice(4, new int[][]{{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}}, 0, 3, 1));
        System.out.println(cfwks.findCheapestPrice(4, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
        System.out.println(cfwks.findCheapestPrice(4, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
        System.out.println(cfwks.findCheapestPrice(5, new int[][]{{1, 2, 4}}, 0, 4, 2));


//        Example 1:
//        Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
//        Output: 700
//        Explanation:
//        The graph is shown above.
//                The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
//        Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
//
//                Example 2:
//        Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
//        Output: 200
//        Explanation:
//        The graph is shown above.
//                The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
//
//        Example 3:
//        Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
//        Output: 500
//        Explanation:
//        The graph is shown above.
//                The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
    }

}
