package com.java.stuff.leetcode.medium;

import java.util.*;

public class StringMedium {

    static public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];

        Character d = 'a';
        boolean zeros = true;
        for (int i = 0; i < seq.length(); i++) {
            Character c = seq.charAt(i);
            if ((c == '(' && d == '(')
                    || (c == ')' && d == ')')) {
                zeros = !zeros;
            }

            if (zeros)
                res[i] = 0;
            else
                res[i] = 1;

            d = c;
        }

        return res;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character, Integer>, List<String>> occurencesToStrs = new HashMap<>();
        List<String> empties = new ArrayList<>();

        for(String str : strs){
            if(str.isEmpty())
                empties.add(str);
            else {
                Map<Character, Integer> chars = new HashMap<>();
                for (char c : str.toCharArray()) {
                    chars.put(c, chars.getOrDefault(c, 0) + 1);
                }

                occurencesToStrs.computeIfAbsent(chars, k -> new ArrayList<>()).add(str);
            }
        }

        List<List<String>> result = new ArrayList<>();

        for(List<String> v : occurencesToStrs.values())
            result.add(v);

        if(!empties.isEmpty())
            result.add(empties);

        return result;
    }

//    public static List<List<String>> groupAnagrams(String[] strs) {
//
//
//        List<List<String>> result = new ArrayList<>();
//
//
//        return result;
//    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxDepthAfterSplit("(()())")));
        System.out.println(Arrays.toString(maxDepthAfterSplit("()(())()")));
        System.out.println(Arrays.toString(maxDepthAfterSplit("(((()))((())))")));

//        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
//        System.out.println(groupAnagrams(new String[]{"","b",""}));
        System.out.println(groupAnagrams(new String[]{"tea","and","ate","eat","den"}));

    }

}
