package com.java.stuff.datastructures.graphs;

import java.util.*;

public class WeightedGraphPrims {
    int vertices;
    static List<Node>[] graph;

    public WeightedGraphPrims(int vertices) {
        this.vertices = vertices;
        graph = new ArrayList[vertices];
        for(int i = 0; i < vertices; i++)
            graph[i] = new ArrayList<>();
    }

    void printGraph() {
        for (int i = 0; i < graph.length; i++)
            for (Node node : graph[i])
                System.out.println("Node from " + i + " to " + node.dest + " with distance " + node.distance);
    }

    void addEdge(int src, int dest, int weight) {
        Node nodeSrc = new Node(dest, weight);
        Node nodeDest = new Node(src, weight);
        graph[src].add(nodeSrc);
        graph[dest].add(nodeDest);
    }

    void primsMST(){
        List<Integer> mst = new ArrayList<>();
        // min heap with vertices, all key = max appart from first one
        Queue<NodeKey> queue = new PriorityQueue<NodeKey>();
        queue.add(new NodeKey(0, 0));
        for(int i = 1; i < vertices; i++)
            queue.add(new NodeKey(i, Integer.MAX_VALUE));


        while(!queue.isEmpty()){
            NodeKey node = queue.poll();
            mst.add(node.vertex);






        }


    }

    public static void main(String[] args) {
        WeightedGraphPrims graphPrims = new WeightedGraphPrims(9);

        graphPrims.addEdge(0, 1, 4);
        graphPrims.addEdge(0, 7, 8);
        graphPrims.addEdge(1, 2, 8);
        graphPrims.addEdge(1, 7, 11);
        graphPrims.addEdge(2, 3, 7);
        graphPrims.addEdge(2, 8, 2);
        graphPrims.addEdge(2, 5, 4);
        graphPrims.addEdge(3, 4, 9);
        graphPrims.addEdge(3, 5, 14);
        graphPrims.addEdge(4, 5, 10);
        graphPrims.addEdge(5, 6, 2);
        graphPrims.addEdge(6, 7, 1);
        graphPrims.addEdge(6, 8, 6);
        graphPrims.addEdge(7, 8, 7);

        graphPrims.printGraph();

        System.out.println("Prims:");
        graphPrims.primsMST();


    }

    public static class Node implements Comparable<Node> {
        int dest;
        int distance = Integer.MAX_VALUE;

        public Node(int dest, int distance) {
            this.dest = dest;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            if (this.distance < other.distance)
                return -1;
            if (this.distance > other.distance)
                return 1;

            return 0;
        }

        @Override
        public String toString() {
            return "{Destination:" + dest + "|Distance:" + distance + '}';
        }
    }

    public static class NodeKey implements Comparable<NodeKey> {
        int vertex;
        int key;

        public NodeKey(int vertex) {
            this.vertex = vertex;
            this.key = Integer.MAX_VALUE;
        }

        public NodeKey(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }

        @Override
        public int compareTo(NodeKey other) {
            if (this.key < other.key)
                return -1;
            if (this.key > other.key)
                return 1;

            return 0;
        }
    }

}
