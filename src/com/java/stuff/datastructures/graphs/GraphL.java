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
        for (int i = 0; i < as.length; i++)
            list[as[i]].add(bs[i]);
    }

    public void addEdge(int a, int b) {
        list[a].add(b);
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[list.length];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println(current);

            for (Integer vertex : list[current]) {
                if (!visited[vertex]) {
                    queue.add(vertex);
                    visited[vertex] = true;
                }
            }
        }
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[list.length];
        Stack<Integer> stack = new Stack<>();

        stack.add(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            System.out.println(current);

            for (Integer vertex : list[current]) {
                if (!visited[vertex]) {
                    stack.add(vertex);
                    visited[vertex] = true;
                }
            }
        }
    }

    /**
     * @param start
     * @param finish
     * @return the shortest distance between the two vertices using BFS, -1 if they are not connected
     */
    public int shortestDistanceBetween(int start, int finish) {
        boolean[] visited = new boolean[list.length];
        LinkedList<Integer> queue = new LinkedList<>();
        int[] distances = new int[list.length];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Integer vertex : list[current]) {
                if (!visited[vertex]) {
                    queue.add(vertex);
                    visited[vertex] = true;

                    distances[vertex] = distances[current] + 1;
                    if (vertex == finish)
                        return distances[vertex];
                }
            }
        }

        return -1;
    }

    enum STATE{
        NEW, VISITING, VISITED
    }
    /**
     * @param states
     * @param vertex
     * @return if the vertex is connected to a vertex being visited to double check the loop respect direction
     */
    private boolean isConnectedToAVisitingVertex(STATE[] states, int vertex){
        for(Integer neighbour : list[vertex])
            if(states[neighbour] == STATE.VISITING)
                return true;

        return false;
    }
    /**
     * @param start
     * @return if there is a cycle using DFS
     */
    public boolean detectCycleDirected(int start) {
        STATE[] states = new STATE[list.length];
        for(int i = 0; i < list.length; i++)
            states[i] = STATE.NEW;

        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        states[start] = STATE.VISITING;

        while (!stack.isEmpty()) {
            int current = stack.peek();

            // no neighbours, end of the path
            if(list[current].isEmpty()){
                states[current] = STATE.VISITED;
                stack.pop();
            }

            for (Integer vertex : list[current]) {
                if(states[vertex] == STATE.VISITING && isConnectedToAVisitingVertex(states, vertex))
                    return true;
                else if(states[vertex] == STATE.NEW){
                    stack.add(vertex);
                    states[vertex] = STATE.VISITING;
                }else{
                    states[current] = STATE.VISITED;
                    stack.pop();
                }
            }
        }

        return false;
    }

    public void printGraph(){
        for(int i = 0 ; i < list.length; i++)
            for(Integer arrival : list[i])
                System.out.println("From " + i + " to " + arrival);
    }

    public static void main(String[] args) {
        GraphL g = new GraphL(4);

        g.addEdges(new int[]{0, 0, 1, 2, 2, 3},
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

        g = new GraphL(5);
        g.addEdges(new int[]{0, 1, 2, 2, 4},
                   new int[]{1, 2, 3, 4, 3});
        System.out.println("For graph: ");
        g.printGraph();
        System.out.println("There is a cycle: " + g.detectCycleDirected(0));

        g = new GraphL(5);
        g.addEdges(new int[]{0, 1, 2, 3, 4},
                   new int[]{1, 2, 4, 2, 3});
        System.out.println("For graph: ");
        g.printGraph();
        System.out.println("There is a cycle: " + g.detectCycleDirected(0));

    }

}
