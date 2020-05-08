package com.java.stuff.leetcode.challenge.twenty.may;

public class CheckStraightLine {


    public boolean checkStraightLine(int[][] coordinates) {
        if(coordinates.length == 2)
            return true;

        //   m = (ya -yb)/(xa-xb)
        double m = (double)(coordinates[1][1] - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][0]);

        for(int i = 1; i < coordinates.length-1; i++)
            if(Math.abs(m - (double)(coordinates[i+1][1] - coordinates[i][1])/(coordinates[i+1][0] - coordinates[i][0])) > 0.1d)
                return false;

        return true;
    }


    public static void main(String[] args) {
        CheckStraightLine cst = new CheckStraightLine();
        System.out.println(cst.checkStraightLine( new int[][]{{1,2},{2,3}}));
        System.out.println(cst.checkStraightLine(new int[][]{{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}}));
        System.out.println(cst.checkStraightLine(new int[][]{{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}}));
        System.out.println(cst.checkStraightLine(new int[][]{{-4,-3},{1,0},{3,-1},{0,-1},{-5,2}}));
    }

}
