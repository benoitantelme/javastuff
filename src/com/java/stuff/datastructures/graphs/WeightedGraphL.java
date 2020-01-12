package com.java.stuff.datastructures.graphs;

import java.util.*;

public class WeightedGraphL {
    static Set<Node> graph = new HashSet<>();

    public static class Node implements Comparable<Node> {
        String name;
        List<Node> shortestPath = new LinkedList<>();
        Integer distance = Integer.MAX_VALUE;
        boolean evaluated;

        Map<Node, Integer> adjacentNodes = new HashMap<>();

        public Node(String name) {
            this.name = name;
        }

        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }

        @Override
        public int compareTo(Node other) {
            if (this.distance < other.distance)
                return -1;
            if (this.distance > other.distance)
                return 1;

            return 0;
        }

        void updateMinimumDistance(Node evaluationNode,
                                    Integer edgeWeigh) {
            if (this.distance + edgeWeigh < evaluationNode.distance) {
                evaluationNode.distance = this.distance + edgeWeigh;
                LinkedList<Node> shortestPath = new LinkedList(this.shortestPath);
                shortestPath.add(this);
                evaluationNode.shortestPath = shortestPath;
            }
        }

        @Override
        public String toString() {
            return "{Node " + name + '}';
        }
    }

    static void calculateShortestPathFromSource(Node start) {
        start.distance = 0;

        Queue<Node> unsettledNodes = new PriorityQueue<>();
        unsettledNodes.add(start);

        while (!unsettledNodes.isEmpty()) {
            Node current = unsettledNodes.poll();

            for (Map.Entry<Node, Integer> adjacent : current.adjacentNodes.entrySet()) {
                Node nextNode = adjacent.getKey();
                int distance = adjacent.getValue();
                if (!nextNode.evaluated) {
                    current.updateMinimumDistance(nextNode, distance);
                    unsettledNodes.add(nextNode);
                }
            }

            current.evaluated = true;
        }

    }

    static void printGraph() {
        for (Node node : graph)
            System.out.println("Node " + node.name + " with distance " + node.distance + " and shortest path " + node.shortestPath);
    }

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        graph.add(nodeA);
        graph.add(nodeB);
        graph.add(nodeC);
        graph.add(nodeD);
        graph.add(nodeE);
        graph.add(nodeF);

        calculateShortestPathFromSource(nodeA);
        printGraph();
    }

}
