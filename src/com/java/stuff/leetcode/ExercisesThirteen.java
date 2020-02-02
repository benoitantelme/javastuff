package com.java.stuff.leetcode;

public class ExercisesThirteen {

    public static int hammingDistance(int x, int y) {
        int res = 0;

        int z = x ^ y;

        while(z > 0){
            res += z & 1;
            z = z >> 1;
        }

        return res;
    }

    public static void main(String args[]) {
        System.out.println(hammingDistance(1, 4));
    }

}
