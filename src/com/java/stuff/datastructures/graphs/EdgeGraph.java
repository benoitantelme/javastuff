package com.java.stuff.datastructures.graphs;

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

    Edge[] edges;
    int[] parents;

    EdgeGraph(int nbrEdges, int nbrVertices) {
        edges = new Edge[nbrEdges];

        // One subset per vertex
        parents = new int[nbrVertices];
        for (int i = 0; i < nbrVertices; i++)
            parents[i] = -1;
    }

    public void print(){
        for(Edge edge : edges)
            System.out.println("Edge:{" + edge.src + "-" + edge.dest + "}");
    }

    private int find(int[] parent, int i) {
        return parent[i] == -1 ? i : find(parent, parent[i]);
    }

    private void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
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
        System.out.println("For graph: ");
        graph.print();
        System.out.println("graph contains a cycle: " + graph.unionFindCycle());
    }

}
