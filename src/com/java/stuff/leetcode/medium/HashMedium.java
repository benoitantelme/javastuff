package com.java.stuff.leetcode.medium;

import java.util.*;

public class HashMedium {

    public int minSetSize(int[] arr) {
        Map<Integer, Integer> occurences = new HashMap<>();
        for (int n : arr)
            occurences.put(n, occurences.getOrDefault(n, 0) + 1);

        List<Integer> ordered = new ArrayList<>();
        ordered.addAll(occurences.values());
        Collections.sort(ordered, Comparator.reverseOrder());

        int res = 1;
        int tmpSum = ordered.get(0);
        for (int i = 1; i < ordered.size(); i++) {
            if (tmpSum >= arr.length / 2)
                break;

            tmpSum = tmpSum + ordered.get(i);
            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        HashMedium hm = new HashMedium();

        System.out.println(hm.minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
        System.out.println(hm.minSetSize(new int[]{1, 9}));
        System.out.println(hm.minSetSize(new int[]{5, 5, 1, 9}));
    }

}
