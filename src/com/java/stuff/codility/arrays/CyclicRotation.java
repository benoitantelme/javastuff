package com.java.stuff.codility.arrays;

import java.util.Arrays;

public class CyclicRotation {

    public static void main(String[] args) {
        CyclicRotation cr = new CyclicRotation();

        if (!Arrays.equals(cr.solution(new int[]{0, 0, 0}, 1), new int[]{0, 0, 0}))
            System.out.println("wrong");

        if (!Arrays.equals(cr.solution(new int[]{3, 8, 9, 7, 6}, 3), new int[]{9, 7, 6, 3, 8}))
            System.out.println("wrong");

        if (!Arrays.equals(cr.solution(new int[]{1, 2, 3, 4}, 4), new int[]{1, 2, 3, 4}))
            System.out.println("wrong");

    }

    public int[] solution(int[] A, int K) {
        int n = A.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int j = i + K;
            if (j > n - 1)
                j = j - n;

            res[j] = A[i];
        }

        return res;
    }


}
