package com.java.stuff.leetcode2023.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    exploreAround(grid, new int[]{i, j});
                    result++;
                }
            }
        }

        return result;
    }

    void exploreAround(char[][] grid, int[] pos) {
        Queue<int[]> islands = new LinkedList<>();
        islands.add(pos);
        while (!islands.isEmpty()) {
            int[] loc = islands.poll();
            if (isValid(grid, loc[0], loc[1]) && grid[loc[0]][loc[1]] == '1') {
                grid[loc[0]][loc[1]] = '0';
                islands.add(new int[]{loc[0] + 1, loc[1]});
                islands.add(new int[]{loc[0], loc[1] + 1});
                islands.add(new int[]{loc[0] - 1, loc[1]});
                islands.add(new int[]{loc[0], loc[1] - 1});
            }
        }
    }

    boolean isValid(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    public static void main(String[] args) {
        NumberOfIslands noi = new NumberOfIslands();

        System.out.println(noi.numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}}));
        System.out.println(noi.numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}}));


//        Example 1:
//        Input: grid = [
//  ['1','1','1','1','0'],
//  ['1','1','0','1','0'],
//  ['1','1','0','0','0'],
//  ['0','0','0','0','0']
//]
//        Output: 1
//
//        Example 2:
//        Input: grid = [
//  ['1','1','0','0','0'],
//  ['1','1','0','0','0'],
//  ['0','0','1','0','0'],
//  ['0','0','0','1','1']
//]
//        Output: 3
    }

}
