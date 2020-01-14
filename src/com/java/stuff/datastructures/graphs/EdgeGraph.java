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

        // One subset per vertex with rank 0 and its own parent
        parents = new Subset[nbrVertices];
        for (int i = 0; i < nbrVertices; i++)
            parents[i] = new Subset(i, 0);
    }

    public void print() {
        for (Edge edge : edges)
            System.out.println("Edge:{" + edge.src + "-" + edge.dest + "}");
    }

    /**
     * @param parent
     * @param i
     * @return and set the parent subset of this subset if it exists, recursively
     */
    private Subset find(Subset[] parent, int i) {
        if (parents[i].parent != i)
            parents[i].parent = find(parents, parent[i].parent).parent;

        return parents[i];
    }

    /**
     * @param parent
     * @param x
     * @param y      This function is called when the two vertices are not in the same subset
     *               we set their relation depending on which subset has the highest rank
     */
    private void unionByRank(Subset parent[], int x, int y) {
        Subset xroot = find(parent, x);
        Subset yroot = find(parent, y);

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
        for (Edge edge : edges) {
            Subset sourceSet = find(parents, edge.src);
            Subset destinationSet = find(parents, edge.dest);

            if (sourceSet.equals(destinationSet))
                return true;
            else
                unionByRank(parents, edge.src, edge.dest);
        }

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
