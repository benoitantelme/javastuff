package com.java.stuff.leetcode2023.greedy;

import java.util.Arrays;
import java.util.TreeMap;

public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0)
            return false;

        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        for (int nbr : hand)
            treemap.merge(nbr, 1, (o, n) -> o + n);

        while (!treemap.isEmpty()) {
            int first = treemap.firstKey();

            for (int i = first; i < first + groupSize; i++) {
                if (!treemap.containsKey(i))
                    return false;

                int updated = treemap.merge(i, -1, (o, n) -> o + n);
                if(updated == 0)
                    treemap.remove(i);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        HandOfStraights hos = new HandOfStraights();

        System.out.println(hos.isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        System.out.println(hos.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(hos.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 2));
        System.out.println(hos.isNStraightHand(new int[]{2, 1}, 2));
        System.out.println(hos.isNStraightHand(new int[]{8, 10, 12}, 3));

//        Example 1:
//        Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//        Output: true
//        Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
//
//        Example 2:
//        Input: hand = [1,2,3,4,5], groupSize = 4
//        Output: false
//        Explanation: Alice's hand can not be rearranged into groups of 4.
    }

    public boolean isNStraightHandSlow(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0)
            return false;

        Arrays.sort(hand);
        boolean[] used = new boolean[n];
        int usedNumber = 0;
        while (usedNumber < n) {
            int previous = -1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (count < groupSize && !used[i] &&
                        (previous == -1 ? true : hand[i] == previous + 1)) {
                    previous = hand[i];
                    used[i] = true;
                    usedNumber++;
                    count++;
                }
            }

            if (count < groupSize)
                return false;
        }

        return true;
    }

}
