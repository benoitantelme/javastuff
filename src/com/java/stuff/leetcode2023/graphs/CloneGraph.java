package com.java.stuff.leetcode2023.graphs;

import java.util.*;

public class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Node> created = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();

            if (visited.contains(tmp.val))
                continue;

            Node copy = created.containsKey(tmp.val) ? created.get(tmp.val) : new Node(tmp.val, new ArrayList<>());
            created.putIfAbsent(copy.val, copy);

            for (Node neighbour : tmp.neighbors) {
                Node nCopy = created.containsKey(neighbour.val) ? created.get(neighbour.val)
                        : new Node(neighbour.val, new ArrayList<>());
                created.putIfAbsent(neighbour.val, nCopy);

                copy.neighbors.add(nCopy);
                queue.add(neighbour);
            }

            visited.add(tmp.val);
        }

        return created.getOrDefault(1, null);
    }

    public static void main(String[] args) {
        CloneGraph cg = new CloneGraph();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors = List.of(node2, node4);
        node2.neighbors = List.of(node1, node3);
        node3.neighbors = List.of(node2, node4);
        node4.neighbors = List.of(node1, node3);

        Node cloned = cg.cloneGraph(node1);
        System.out.println(cloned);

        cloned = cg.cloneGraph(node1);
        System.out.println(cloned);

        cloned = cg.cloneGraph(null);
        System.out.println(cloned);

//        Example 1:
//        Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//        Output: [[2,4],[1,3],[2,4],[1,3]]
//        Explanation: There are 4 nodes in the graph.
//        1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//        2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//        3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//        4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//
//        Example 2:
//        Input: adjList = [[]]
//        Output: [[]]
//        Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
//
//                Example 3:
//        Input: adjList = []
//        Output: []
//        Explanation: This an empty graph, it does not have any nodes.
    }

}
