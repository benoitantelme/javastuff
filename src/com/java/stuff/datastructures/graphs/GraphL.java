package com.java.stuff.datastructures.graphs;

import java.util.LinkedList;

/**
 * Directed graph using an adjacency list representation
 */
public class GraphL {
    private LinkedList<Integer>[] list;

    GraphL(int vertices) {
        list = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
            list[i] = new LinkedList();
    }

    public void addEdges(int[] as, int[] bs) {
        for(int i = 0; i < as.length; i++)
            list[as[i]].add(bs[i]);
    }
    public void addEdge(int a, int b) {
        list[a].add(b);
    }

    public void bfs(int start){
        boolean[] visited = new boolean[list.length];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();

            for(Integer vertice : list[current]){
                System.out.println(current + " to " + vertice);

                if(!visited[vertice]) {
                    queue.add(vertice);
                    visited[vertice] = true;
                }
            }

        }
    }


    public static void main(String[] args) {
        GraphL g = new GraphL(4);

        g.addEdges(new int[]{0, 0, 1 , 2, 2, 3},
                   new int[]{1, 2, 2, 0, 3, 3});
        
        System.out.println("BFS: ");
        g.bfs(0);

    }

}
