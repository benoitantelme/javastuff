package com.java.stuff.leetcode2023.arrays_hashing;

import java.util.*;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        if (null == nums || nums.length == 0)
            return new int[]{};

        Map<Integer, Integer> nbrMap = new HashMap<>();
        // in case there is only one element its frequence will be nums.length
        List<Integer>[] occurences = new ArrayList[nums.length + 1];

        for (int n : nums)
            nbrMap.merge(n, 1, (oldV, newV) -> oldV + 1);

        for (Integer key : nbrMap.keySet()) {
            int frequence = nbrMap.get(key);

            if (occurences[frequence] == null)
                occurences[frequence] = new ArrayList<>();

            occurences[frequence].add(key);
        }

        int index = 0;
        int[] result = new int[k];
        for (int i = occurences.length - 1; i > 0; i--) {
            if (occurences[i] != null)
                for (int nbr : occurences[i]) {
                    result[index++] = nbr;
                    if (index == k)
                        return result;
                }

        }

        return result;
    }


    public static void main(String[] args) {
        TopKFrequentElements tk = new TopKFrequentElements();


        System.out.println(Arrays.toString(tk.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(tk.topKFrequent(new int[]{1}, 1)));

        System.out.println(Arrays.toString(tk.topKFrequent(new int[]{}, 1)));
        System.out.println(Arrays.toString(tk.topKFrequent(null, 1)));
    }

    public int[] slowTopKFrequent(int[] nums, int k) {
        if (null == nums || nums.length == 0)
            return new int[]{};

        Map<Integer, Integer> nbrMap = new HashMap<>();
        TreeMap<Integer, List<Integer>> occurencesMap = new TreeMap<>(Collections.reverseOrder());

        for (int n : nums) {
            Integer occurrences = nbrMap.merge(n, 1, (oldV, newV) -> oldV + 1);

            if (!occurencesMap.containsKey(occurrences))
                occurencesMap.put(occurrences, new ArrayList<>());

            occurencesMap.get(occurrences).add(n);
        }

        int[] ordered = occurencesMap.values().
                stream().
                flatMap(List::stream).
                distinct().
                limit(k).
                mapToInt(i -> i).toArray();

        return ordered;
    }

}
