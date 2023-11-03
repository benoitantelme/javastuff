package com.java.stuff.leetcode2023.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // up and left
        Set<List<Integer>> pacific = new HashSet<>();
        for (int x = 0; x < heights.length; x++) {
            for (int y = 0; y < heights[0].length; y++) {
                int a = heights[x][y];
                if (x == 0 ||
                        (heights[x][y] >= heights[x - 1][y] && pacific.contains(List.of(x - 1, y)))) {
                    pacific.add(List.of(x, y));
                    continue;
                }
                if (y == 0 ||
                        (heights[x][y] >= heights[x][y - 1] && pacific.contains(List.of(x, y - 1))))
                    pacific.add(List.of(x, y));
            }
        }

        // down and right
        Set<List<Integer>> atlantic = new HashSet<>();
        for (int x = heights.length - 1; x >= 0; x--) {
            for (int y = heights[0].length - 1; y >= 0; y--) {
                int a = heights[x][y];
                if (x == heights[0].length - 1 ||
                        (heights[x][y] >= heights[x + 1][y] && atlantic.contains(List.of(x + 1, y)))) {
                    atlantic.add(List.of(x, y));
                    continue;
                }
                if (y == heights.length - 1 ||
                        (heights[x][y] >= heights[x][y + 1] && atlantic.contains(List.of(x, y + 1))))
                    atlantic.add(List.of(x, y));
            }
        }

        pacific.retainAll(atlantic);


        System.out.println(heights[0][2]);
        System.out.println(heights[1][0]);
        System.out.println(heights[1][1]);
        System.out.println(heights[1][2]);
        System.out.println(heights[2][0]);
        System.out.println(heights[2][1]);
        System.out.println(heights[2][2]);

        return new ArrayList<>(pacific);
    }


    public static void main(String[] args) {
        PacificAtlanticWaterFlow pawf = new PacificAtlanticWaterFlow();

        System.out.println(pawf.pacificAtlantic(new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}}));

        System.out.println(pawf.pacificAtlantic(new int[][]{
                {1}}));

        // wtf tfymf ff fffff 6
        System.out.println(pawf.pacificAtlantic(new int[][]{
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}}));

//        Example 1:
//        Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
//        Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
//        Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
//[0,4]: [0,4] -> Pacific Ocean
//       [0,4] -> Atlantic Ocean
//[1,3]: [1,3] -> [0,3] -> Pacific Ocean
//       [1,3] -> [1,4] -> Atlantic Ocean
//[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
//       [1,4] -> Atlantic Ocean
//[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
//       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
//[3,0]: [3,0] -> Pacific Ocean
//       [3,0] -> [4,0] -> Atlantic Ocean
//[3,1]: [3,1] -> [3,0] -> Pacific Ocean
//       [3,1] -> [4,1] -> Atlantic Ocean
//[4,0]: [4,0] -> Pacific Ocean
//       [4,0] -> Atlantic Ocean
//        Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
//
//        Example 2:
//        Input: heights = [[1]]
//        Output: [[0,0]]
//        Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
    }

}
