package com.java.stuff.datastructures.graphs;

import java.util.LinkedList;
import java.util.Stack;

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
            System.out.println(current);

            for(Integer vertice : list[current]){
                if(!visited[vertice]) {
                    queue.add(vertice);
                    visited[vertice] = true;
                }
            }
        }
    }

    public void dfs(int start){
        boolean[] visited = new boolean[list.length];
        Stack<Integer> stack = new Stack<>();

        stack.add(start);
        visited[start] = true;

        while(!stack.isEmpty()){
            int current = stack.pop();
            System.out.println(current);

            for(Integer vertice : list[current]){
                if(!visited[vertice]) {
                    stack.add(vertice);
                    visited[vertice] = true;
                }
            }
        }
    }

    public int shortestDistanceBetween(int start, int finish){
        boolean[] visited = new boolean[list.length];
        LinkedList<Integer> queue = new LinkedList<>();
        int[] distances = new int[list.length];

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();

            for(Integer vertice : list[current]){
                if(!visited[vertice]) {
                    queue.add(vertice);
                    visited[vertice] = true;

                    distances[vertice] = distances[current] + 1;
                    if(vertice == finish)
                        return distances[vertice];
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        GraphL g = new GraphL(4);

        g.addEdges(new int[]{0, 0, 1 , 2, 2, 3},
                   new int[]{1, 2, 2, 0, 3, 3});

        System.out.println("BFS: ");
        g.bfs(0);

        System.out.println("DFS: ");
        g.dfs(0);

        g = new GraphL(7);
        g.addEdges(new int[]{0, 0, 0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5, 5, 6},
                   new int[]{1, 2, 4, 0, 2, 0, 1, 5, 4, 0, 3, 5, 6, 2, 4, 4});


        System.out.println("BFS new tree: ");
        g.bfs(0);

        System.out.println("Shortest distance between 1 and 5: " + g.shortestDistanceBetween(1, 5));
        System.out.println("Shortest distance between 1 and 3: " + g.shortestDistanceBetween(1, 3));
    }

}
