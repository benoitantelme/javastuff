package com.java.stuff.leetcode.challenge.twenty.may;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visited = new boolean[image.length][];
        for(int i = 0; i < image.length; i++)
            visited[i] = new boolean[image[0].length];

        int initialColor = image[sr][sc];

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{sr, sc});

        while(!toVisit.isEmpty()){
            int[] pixel = toVisit.poll();
            if(!visited[pixel[0]][pixel[1]] && image[pixel[0]][pixel[1]] == initialColor){
                image[pixel[0]][pixel[1]] = newColor;
                visited[pixel[0]][pixel[1]] = true;

                if(pixel[1]+1 >= 0 &&  pixel[1]+1 <= image[0].length-1 && image[pixel[0]][pixel[1]+1] == initialColor){
                    toVisit.add(new int[]{pixel[0], pixel[1]+1});
                }
                if(pixel[1]-1 >= 0 &&  pixel[1]-1 <= image[0].length-1 && image[pixel[0]][pixel[1]-1] == initialColor){
                    toVisit.add(new int[]{pixel[0], pixel[1]-1});
                }
                if(pixel[0]+1 >= 0 &&  pixel[0]+1 <= image.length-1 && image[pixel[0]+1][pixel[1]] == initialColor){
                    toVisit.add(new int[]{pixel[0]+1, pixel[1]});
                }
                if(pixel[0]-1 >= 0 &&  pixel[0]-1 <= image.length-1 && image[pixel[0]-1][pixel[1]] == initialColor){
                    toVisit.add(new int[]{pixel[0]-1, pixel[1]});
                }
            }

        }

        return image;
    }


    public static void main(String[] args) {
        FloodFill ff = new FloodFill();
        System.out.println(ff.floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}}, 1, 1, 2));

    }

}
