package com.java.stuff.leetcode2023.advanced_graphs;

import java.util.*;

public class AlienDictionary {


    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adjacencyMap = new HashMap<>();
        Map<Character, Integer> incoming = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            char[] edge = getEdge(words[i], words[i + 1]);
            char source = edge[0];
            char target = edge[1];

            adjacencyMap.putIfAbsent(source, new ArrayList<>());
            adjacencyMap.putIfAbsent(target, new ArrayList<>());
            adjacencyMap.get(source).add(target);

            incoming.putIfAbsent(source, 0);
            incoming.merge(target, 1, Integer::sum);
        }

        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> e : incoming.entrySet())
            if (e.getValue() == 0)
                queue.add(e.getKey());

        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            result.append(c);

            for (Character neighbour : adjacencyMap.get(c)) {
                incoming.merge(neighbour, -1, Integer::sum);

                if (incoming.get(neighbour) == 0)
                    queue.add(neighbour);
            }
        }

        return result.toString();
    }

    char[] getEdge(String a, String b) {
        int length = Math.min(a.length(), b.length());
        for (int i = 0; i < length; i++)
            if (a.charAt(i) != b.charAt(i))
                return new char[]{a.charAt(i), b.charAt(i)};

        return null;
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();


        System.out.println(ad.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(ad.alienOrder(new String[]{"z", "x"}));

//        Example 1:
//
//        Input：["wrt","wrf","er","ett","rftt"]
//        Output："wertf"
//
//        Explanation：
//        from "wrt"and"wrf" ,we can get 't'<'f'
//        from "wrt"and"er" ,we can get 'w'<'e'
//        from "er"and"ett" ,we can get 'r'<'t'
//        from "ett"and"rftt" ,we can get 'e'<'r'
//        So return "wertf"
//
//        Example 2:
//
//        Input：["z","x"]
//        Output："zx"
//
//        Explanation：
//        from "z" and "x"，we can get 'z' < 'x'
//        So return "zx"
    }

}
