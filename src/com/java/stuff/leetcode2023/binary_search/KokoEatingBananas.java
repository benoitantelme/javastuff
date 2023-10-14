package com.java.stuff.leetcode2023.binary_search;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;

        //get max
        int r = Integer.MIN_VALUE;
        for (int n : piles)
            if (n > r)
                r = n;

        // binary search with l chasing back the min value
        while (l < r) {
            int p = (l + r) / 2;

            // ceiling for full pile
            int total = 0;
            for (int n : piles)
                total += Math.ceil((double) n / p);

            // get to pivot if more or equals, else left going up one
            if (total <= h)
                r = p;
            else
                l = p + 1;
        }

        return r;
    }


    public static void main(String[] args) {
        KokoEatingBananas keb = new KokoEatingBananas();

        System.out.println(keb.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(keb.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println(keb.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
        System.out.println(keb.minEatingSpeed(new int[]{5}, 4));
        System.out.println(keb.minEatingSpeed(new int[]{312884470}, 312884469));


//        Example 1:
//        Input: piles = [3,6,7,11], h = 8
//        Output: 4
//
//        Example 2:
//        Input: piles = [30,11,23,4,20], h = 5
//        Output: 30
//
//        Example 3:
//        Input: piles = [30,11,23,4,20], h = 6
//        Output: 23
    }

}
