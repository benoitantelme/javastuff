package com.java.stuff.datastructures.matrix;

import java.util.*;

public class Matrix {

    public static void rotateMatrix(int[][] mtr){
        int n = mtr.length;
        int rotations = n/2;
        int x = 0, y = 0, times = 0;

        int tmp, curr;
        while(rotations >= times){
            tmp = mtr[x+1][y];

            for(; y < n; y ++){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }
            x++;
            y--;
            for(; x < n; x++){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }
            y--;
            x--;
            for(; y >= times; y --){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }
            x--;
            y++;
            for(; x >= times; x --){
                curr = mtr[x][y];
                mtr[x][y] = tmp;
                tmp = curr;
            }

            x = y = ++times;
            n--;
        }
    }

    static class Node{
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Node{" + x + "," + y + ":" + distance + '}';
        }

        boolean isValid(int xmax, int ymax){
            if(this.x <= xmax && this.x >= 0 && this.y <= ymax && this.y >= 0)
                return true;
            else
                return false;
        }

        List<Node> getNeighbours(){
            List<Node> neighbours = new ArrayList<>();
            neighbours.add(new Node(this.x+1, this.y, Integer.MAX_VALUE));
            neighbours.add(new Node(this.x-1, this.y, Integer.MAX_VALUE));
            neighbours.add(new Node(this.x, this.y-1, Integer.MAX_VALUE));
            neighbours.add(new Node(this.x, this.y+1, Integer.MAX_VALUE));

            return neighbours;
        }
    }

    static int bfs(int[][] matrix, Node start, Node destination){
        int xmax = matrix.length-1;
        int ymax = matrix[0].length-1;

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[start.x][start.y] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            Node current = queue.poll();

            if(current.equals(destination))
                return current.distance;

            for(Node neighbour : current.getNeighbours()){
                if(neighbour.isValid(xmax, ymax) &&
                        matrix[neighbour.x][neighbour.y] == 1 &&
                        !visited[neighbour.x][neighbour.y]){
                    neighbour.distance = current.distance +1;
                    queue.add(neighbour);
                    visited[neighbour.x][neighbour.y] = true;
                }

            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int mtr[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        System.out.println("Matrix:");
        for (int[] array : mtr)
            System.out.println(Arrays.toString(array));

        rotateMatrix(mtr);
        System.out.println("Rotated:");
        for (int[] array : mtr)
            System.out.println(Arrays.toString(array));


        int mat[][] = {
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 0, 1, 1, 0, 0, 1 }};

        Node source = new Node(0, 0, 0);
        Node dest = new Node(8, 9, Integer.MAX_VALUE);

        System.out.println("\nMaze:");
        for (int[] array : mat)
            System.out.println(Arrays.toString(array));

        int dist = bfs(mat, source, dest);
        System.out.println("Shortest Path is " + dist);
    }

}
