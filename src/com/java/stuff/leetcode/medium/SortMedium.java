package com.java.stuff.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class SortMedium {


    private void revert(int[] A, int r) {
        int l = 0;
        while (l < r) {
            int x = A[l];
            A[l++] = A[r];
            A[r--] = x;
        }
    }

    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList();
        int t = A.length;
        int j = A.length - 1;
        while (j > 0) {
            for (int i = 0; i <= j; i++) {
                if (A[i] == t) {
                    revert(A, i);
                    res.add(i + 1);
                    revert(A, j);
                    res.add(j + 1);
                    t--;
                    j--;
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        SortMedium sm = new SortMedium();
        System.out.println(sm.pancakeSort(new int[]{3, 2, 4, 1}));
    }


}
