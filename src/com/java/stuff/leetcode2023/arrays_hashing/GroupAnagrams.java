package com.java.stuff.leetcode2023.arrays_hashing;

import com.java.stuff.datastructures.hash.Hash;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);

            if (map.containsKey(sortedString))
                map.get(sortedString).add(s);
            else
                map.put(sortedString, new ArrayList<>(List.of(s)));
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();

        System.out.println(ga.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(ga.groupAnagrams(new String[]{""}));
        System.out.println(ga.groupAnagrams(new String[]{"a"}));
    }

    public List<List<String>> slowGroupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            String sortedString = s.codePoints().
                    sorted().
                    mapToObj(String::valueOf).
                    collect(Collectors.joining(""));

            if (map.containsKey(sortedString))
                map.get(sortedString).add(s);
            else
                map.put(sortedString, new ArrayList<>(List.of(s)));
        }

        return new ArrayList<>(map.values());
    }


}
