package com.java.stuff.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedEdgeGraph extends EdgeGraph {

    static class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
        private int weight;

        WeightedEdge(int src, int dest, int weight) {
            super(src, dest);

            this.weight = weight;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return super.toString() + "(w:" + weight + ")";
        }

        @Override
        public int compareTo(WeightedEdge other) {
            if (weight > other.weight)
                return 1;
            if (other.weight > weight)
                return -1;

            return 0;
        }
    }

    WeightedEdgeGraph(int nbrEdges, int nbrVertices) {
        super(nbrEdges, nbrVertices);
    }

    public List<IEdge> kruskalMinimumSpanningtree() {
        List<IEdge> result = new ArrayList<>();

        Arrays.sort(edges);

        for (IEdge edge : edges) {
            Subset sourceSet = find(parents, edge.getSrc());
            Subset destinationSet = find(parents, edge.getDest());

            if(!sourceSet.equals(destinationSet)){
                result.add(edge);
                unionByRank(parents, edge.getSrc(), edge.getDest());
            }

            if(result.size() >= parents.length-1)
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        WeightedEdgeGraph graph = new WeightedEdgeGraph(5, 4);
        graph.edges[0] = new WeightedEdge(0, 1, 10);
        graph.edges[1] = new WeightedEdge(0, 2, 6);
        graph.edges[2] = new WeightedEdge(0, 3, 5);
        graph.edges[3] = new WeightedEdge(1, 3, 15);
        graph.edges[4] = new WeightedEdge(2, 3, 4);
        System.out.println("Graph: ");
        graph.print();

        System.out.println("Minimum spanning tree: ");
        List<IEdge> minimumSpanningTree = graph.kruskalMinimumSpanningtree();
        for (IEdge edge : minimumSpanningTree)
            System.out.println(edge);


        graph = new WeightedEdgeGraph(14, 9);
        graph.edges[0] = new WeightedEdge(0, 1, 4);
        graph.edges[1] = new WeightedEdge(0, 7, 8);
        graph.edges[2] = new WeightedEdge(1, 2, 8);
        graph.edges[3] = new WeightedEdge(1, 7, 11);
        graph.edges[4] = new WeightedEdge(2, 8, 2);
        graph.edges[5] = new WeightedEdge(2, 5, 4);
        graph.edges[6] = new WeightedEdge(2, 3, 7);
        graph.edges[7] = new WeightedEdge(3, 5, 14);
        graph.edges[8] = new WeightedEdge(3, 4, 9);
        graph.edges[9] = new WeightedEdge(4, 5, 10);
        graph.edges[10] = new WeightedEdge(5, 6, 2);
        graph.edges[11] = new WeightedEdge(6, 8, 6);
        graph.edges[12] = new WeightedEdge(6, 7, 1);
        graph.edges[13] = new WeightedEdge(7, 8, 7);
        System.out.println("Graph: ");
        graph.print();

        System.out.println("Minimum spanning tree: ");
        minimumSpanningTree = graph.kruskalMinimumSpanningtree();
        for (IEdge edge : minimumSpanningTree)
            System.out.println(edge);
    }

}