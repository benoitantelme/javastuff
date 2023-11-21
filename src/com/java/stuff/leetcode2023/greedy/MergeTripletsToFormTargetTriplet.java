package com.java.stuff.leetcode2023.greedy;

public class MergeTripletsToFormTargetTriplet {

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean[] founds = new boolean[3];

        for (int[] triplet : triplets) {
            boolean higher = false;
            boolean[] tmpFounds = new boolean[3];
            for (int i = 0; i < 3; i++) {
                int val = triplet[i];
                if (val > target[i]) {
                    higher = true;
                    break;
                }

                if (val == target[i])
                    tmpFounds[i] = true;
            }

            if (!higher) {
                for (int i = 0; i < 3; i++)
                    if (tmpFounds[i] == true)
                        founds[i] = true;
            }
        }


        boolean result = true;
        for (boolean found : founds)
            result &= found;

        return result;
    }

    public static void main(String[] args) {
        MergeTripletsToFormTargetTriplet mt = new MergeTripletsToFormTargetTriplet();
        System.out.println(mt.mergeTriplets(new int[][]{{2, 5, 3}, {1, 8, 4}, {1, 7, 5}}, new int[]{2, 7, 5}));
        System.out.println(mt.mergeTriplets(new int[][]{{3, 4, 5}, {4, 5, 6}}, new int[]{3, 2, 5}));
        System.out.println(mt.mergeTriplets(new int[][]{{2, 5, 3}, {2, 3, 4}, {1, 2, 5}, {5, 2, 3}}, new int[]{5, 5, 5}));

//        Example 1:
//        Input: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
//        Output: true
//        Explanation: Perform the following operations:
//        - Choose the first and last triplets [[2,5,3],[1,8,4],[1,7,5]]. Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],[2,7,5]]
//        The target triplet [2,7,5] is now an element of triplets.
//
//        Example 2:
//        Input: triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
//        Output: false
//        Explanation: It is impossible to have [3,2,5] as an element because there is no 2 in any of the triplets.
//
//                Example 3:
//        Input: triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
//        Output: true
//        Explanation: Perform the following operations:
//        - Choose the first and third triplets [[2,5,3],[2,3,4],[1,2,5],[5,2,3]]. Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
//        - Choose the third and fourth triplets [[2,5,3],[2,3,4],[2,5,5],[5,2,3]]. Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,5,5]].
//        The target triplet [5,5,5] is now an element of triplets.
    }

}
