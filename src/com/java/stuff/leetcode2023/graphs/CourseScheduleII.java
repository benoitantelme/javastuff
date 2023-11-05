package com.java.stuff.leetcode2023.graphs;

import java.util.*;
import java.util.stream.IntStream;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> result = new ArrayList<>();

        if(prerequisites.length == 0 || prerequisites[0].length == 0)
            return IntStream.range(0, numCourses)
                .toArray();

        // build graph
        Node[] graph = new Node[20001];
        for (int[] edge : prerequisites) {
            Node node;
            if (graph[edge[1]] != null)
                node = graph[edge[1]];
            else {
                node = new Node(edge[1], new ArrayList<>());
                graph[edge[1]] = node;
            }

            Node neighbor = null;
            if (graph[edge[0]] != null)
                neighbor = graph[edge[0]];
            else {
                neighbor = new Node(edge[0], new ArrayList<>());
                graph[edge[0]] = neighbor;
            }
            node.neighbors.add(neighbor);
        }

        // get set of nodes
        Set<Node> nodes = new HashSet<>();
        for (int[] edge : prerequisites) {
            nodes.add(graph[edge[0]]);
            nodes.add(graph[edge[1]]);
        }

        // get starting nodes
        Map<Node, HashSet<Node>> hasIncoming = new HashMap<>();
        for (Node node : nodes)
            for (Node neighbor : node.neighbors) {
                hasIncoming.putIfAbsent(neighbor, new HashSet<>());
                hasIncoming.get(neighbor).add(node);
            }


        // starting nodes
        Set<Node> startingNodes = new HashSet<>(nodes);
        startingNodes.removeAll(hasIncoming.keySet());


        int courses = 0;
        Queue<Node> queue = new LinkedList<>(startingNodes);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();

            if (courses + 1 > numCourses)
                return new int[0];
            result.add(tmp.val);

            for (Node neighbour : tmp.neighbors) {
                hasIncoming.get(neighbour).remove(tmp);
                if (hasIncoming.get(neighbour).isEmpty())
                    queue.add(neighbour);
            }

            tmp.neighbors = new ArrayList<>();
        }

        //if graph still has edges, there is a cycle
        for (Node node : nodes)
            if (!graph[node.val].neighbors.isEmpty())
                return new int[0];


        if(result.size() < numCourses){
            List<Integer> missings = new ArrayList<>();
            for(int n : IntStream.range(0, numCourses).toArray())
                if(!result.contains(n))
                    missings.add(n);

            missings.addAll(result);
            return missings.stream().mapToInt(i -> i).toArray();
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        CourseScheduleII cs = new CourseScheduleII();

        System.out.println(Arrays.toString(cs.findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(cs.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(cs.findOrder(1, new int[][]{{}})));
        System.out.println(Arrays.toString(cs.findOrder(2, new int[][]{{}})));
        System.out.println(Arrays.toString(cs.findOrder(3, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(cs.findOrder(2, new int[][]{{0, 1}})));
        System.out.println(Arrays.toString(cs.findOrder(4, new int[][]{{3, 0}, {0, 1}})));

//        Example 1:
//        Input: numCourses = 2, prerequisites = [[1,0]]
//        Output: [0,1]
//        Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
//
//        Example 2:
//        Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//        Output: [0,2,1,3]
//        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//        So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
//
//        Example 3:
//        Input: numCourses = 1, prerequisites = []
//        Output: [0]
    }

}
