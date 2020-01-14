package com.java.stuff.datastructures.graphs;

import java.util.Objects;

/**
 * Graph represented with edges and stored in an array
 */
public class EdgeGraph {

    static class Edge {
        int src, dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    static class Subset {
        int parent, rank;

        Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Subset)) return false;
            Subset subset = (Subset) o;

            return parent == subset.parent && parent != -1
                    && rank == subset.rank;
        }

        @Override
        public int hashCode() {
            return Objects.hash(parent, rank);
        }
    }

    Edge[] edges;
    Subset[] parents;

    EdgeGraph(int nbrEdges, int nbrVertices) {
        edges = new Edge[nbrEdges];

        // One subset per vertex
        parents = new Subset[nbrVertices];
        for (int i = 0; i < nbrVertices; i++)
            parents[i] = new Subset(-1, -1);
    }

    public void print() {
        for (Edge edge : edges)
            System.out.println("Edge:{" + edge.src + "-" + edge.dest + "}");
    }

    private Subset find(Subset[] parent, int i) {
        return parent[i].parent == -1 ? parent[i] : find(parent, parent[i].parent);
    }

    private void union(Subset parent[], int x, int y) {
        Subset xroot = find(parent, x);
        Subset yroot = find(parent, y);

        // same set
        if (xroot.equals(yroot))
            return;

        if (xroot.rank < yroot.rank)
            xroot.parent = y;
        else if (yroot.rank < xroot.rank)
            yroot.parent = x;
        else {
            xroot.parent = y;
            yroot.rank += 1;
        }

    }

    public boolean unionFindCycle() {
        for (Edge edge : edges)
            if (find(parents, edge.src) != find(parents, edge.dest))
                union(parents, edge.src, edge.dest);
            else
                return true;

        return false;
    }


    public static void main(String[] args) {
        EdgeGraph graph = new EdgeGraph(3, 3);
        graph.edges[0] = new Edge(0, 1);
        graph.edges[1] = new Edge(1, 2);
        graph.edges[2] = new Edge(0, 2);
        System.out.println("For graph: ");
        graph.print();
        System.out.println("graph contains a cycle: " + graph.unionFindCycle());

        graph = new EdgeGraph(3, 4);
        graph.edges[0] = new Edge(0, 1);
        graph.edges[1] = new Edge(1, 2);
        graph.edges[2] = new Edge(1, 3);
        System.out.println("\nFor graph: ");
        graph.print();
        System.out.println("graph contains a cycle: " + graph.unionFindCycle());
    }

}
