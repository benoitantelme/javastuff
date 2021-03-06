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

        for (String str : strs) {
            if (str.isEmpty())
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

        for (List<String> v : occurencesToStrs.values())
            result.add(v);

        if (!empties.isEmpty())
            result.add(empties);

        return result;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        Map<Character, Integer> occurences = new HashMap<>();
        for (char c : p.toCharArray())
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);

        Map<Character, Integer> tmp = new HashMap<>();
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            // first pass
            if (i == 0) {
                for (int j = i; j < i + p.length(); j++) {
                    char c = s.charAt(j);
                    tmp.put(c, tmp.getOrDefault(c, 0) + 1);
                }
            } else {
                // manage first char of last string
                char prev = s.charAt(i - 1);
                int times = tmp.get(prev);
                if (times == 1)
                    tmp.remove(prev);
                else
                    tmp.put(prev, times - 1);

                char c = s.charAt(i + p.length() - 1);
                tmp.put(c, tmp.getOrDefault(c, 0) + 1);
            }

            if (tmp.equals(occurences))
                res.add(i);
        }

        return res;
    }



    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int nbr = 0;
        char sign = '+';

        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c))
                nbr = nbr * 10 + (c - '0');
            if((!Character.isDigit(c) && c != ' ') || i == s.length()-1){
                if(sign == '+')
                    stack.add(nbr);
                if(sign == '-')
                    stack.add(-nbr);
                if(sign == '*' || sign == '/') {
                    stack.add((sign == '*') ? stack.pop() * nbr : stack.pop() / nbr);
                }

                sign = c;
                nbr = 0;
            }
        }

        for(int n : stack)
            res += n;

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxDepthAfterSplit("(()())")));
        System.out.println(Arrays.toString(maxDepthAfterSplit("()(())()")));
        System.out.println(Arrays.toString(maxDepthAfterSplit("(((()))((())))")));

        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{"", "b", ""}));
        System.out.println(groupAnagrams(new String[]{"tea", "and", "ate", "eat", "den"}));

        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));

        System.out.println(calculate("3*7+2"));
        System.out.println(calculate("3+ 2 * 2+2"));
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
    }

}
