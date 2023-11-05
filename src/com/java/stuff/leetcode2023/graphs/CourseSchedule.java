package com.java.stuff.leetcode2023.graphs;

import java.util.*;

public class CourseSchedule {


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        int[] needed = new int[numCourses];

        // build adjency list
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];
            if (adj[prerequisite] == null)
                adj[prerequisite] = new ArrayList<>();

            adj[prerequisite].add(course);
            needed[course]++;
        }

        // add starting nodes to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (needed[i] == 0)
                queue.offer(i);

        // bfs
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            ans.add(current);

            if (adj[current] != null) {
                for (int next : adj[current]) {
                    needed[next]--;
                    if (needed[next] == 0)
                        queue.offer(next);

                }
            }
        }

        return ans.size() == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();

        System.out.println(cs.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(cs.canFinish(2, new int[][]{{1, 0}, {0, 1}}));


//        Example 1:
//        Input: numCourses = 2, prerequisites = [[1,0]]
//        Output: true
//        Explanation: There are a total of 2 courses to take.
//                To take course 1 you should have finished course 0. So it is possible.
//
//        Example 2:
//        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//        Output: false
//        Explanation: There are a total of 2 courses to take.
//                To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
    }


    //    Kahn's
//    L ← Empty list that will contain the sorted elements
//    S ← Set of all nodes with no incoming edge
//
//    while S is not empty do
//      remove a node n from S
//      add n to L
//      for each node m with an edge e from n to m do
//          remove edge e from the graph
//          if m has no other incoming edges then
//              insert m into S
//
//      if graph has edges then
//          return error   (graph has at least one cycle)
//      else
//          return L   (a topologically sorted order

    public boolean canFinisRaw(int numCourses, int[][] prerequisites) {
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
            courses++;

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
                return false;


        return courses <= numCourses;
    }

}
