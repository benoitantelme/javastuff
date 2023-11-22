package com.java.stuff.leetcode2023.greedy;

import java.util.*;

public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lasts = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
            lasts.put(s.charAt(i), i);

        List<Integer> result = new ArrayList<>();
        int count = 0;
        int last = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count++;
            last = Math.max(last, lasts.get(c));

            if (i == last) {
                result.add(count);
                count = 0;
                last = -1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PartitionLabels pl = new PartitionLabels();

        System.out.println(pl.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(pl.partitionLabels("eccbbbbdec"));
        System.out.println(pl.partitionLabels("caedbdedda"));
        System.out.println(pl.partitionLabels("dccccbaabe"));


//        Example 1:
//        Input: s = "ababcbacadefegdehijhklij"
//        Output: [9,7,8]
//        Explanation:
//        The partition is "ababcbaca", "defegde", "hijhklij".
//                This is a partition so that each letter appears in at most one part.
//                A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
//
//        Example 2:
//        Input: s = "eccbbbbdec"
//        Output: [10]
    }

}
