package com.java.stuff.others;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ChessKnightMoves {

    public static void main(String[] args) {
        ChessKnightMoves ckm = new ChessKnightMoves();

        if(ckm.minMoves(new int[]{2, 1}) != 1)
            System.out.println("wrong");

        if(ckm.minMoves(new int[]{5, 5}) != 4)
            System.out.println("wrong");

//        Input: x = 2, y = 1
//        Output: 1
//        Explanation: [0, 0] -> [2, 1]
//        Input: x = 5, y = 5
//        Output: 4
//        Explanation: [0, 0] -> [2, 1] -> [4, 2] -> [3, 4] -> [5, 5]

    }


    int minMoves(int[] target) {
        int n = 8;
        int[][] moves = new int[][]{
                {1, 2},{2,1},
                {-1, -2},{-2,-1},
                {-1, 2},{-2,1},
                {1, -2},{2,-1}
        };
        int[][] times = new int[n][n];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});

        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];

            if(Arrays.equals(pos, target))
                return times[x][y];

            for(int[] move : moves){
                int destX = move[0]+x;
                int destY = move[1]+y;
                if(destX > 0 && destX < n && destY > 0 && destY < n && times[destX][destY] == 0){
                    q.add(new int[]{destX, destY});
                    times[destX][destY] = times[x][y]+1;
                }
            }
        }

        return -1;
    }

}
