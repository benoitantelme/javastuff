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


    private static int addNextValue(String s, int i, Stack<Character> stack){
        while(i < s.length() && s.charAt(i) == ' ')
            i++;
        if(i < s.length())
            stack.push(s.charAt(i++));

        return i;
    }
    public static int calculate(String s) {
        if(s.length() == 1)
            return Integer.valueOf(s);

        int res = 0;
        Stack<Character> stack = new Stack<>();

        int i = 0;
        i = addNextValue(s, i, stack);
        while (i < s.length()) {
            char c = stack.peek();
            if (c == '*' || c == '/') {
                //priority for mult or div
                char operator = stack.pop();
                int first = Character.getNumericValue(stack.pop());
                i = addNextValue(s, i, stack);
                int second = Character.getNumericValue(stack.pop());

                int tmp;
                if (operator == '*')
                    tmp = first * second;
                else
                    tmp = first / second;

                //check for chained mult or div
                while(i < s.length() && (s.charAt(i) == '*' || s.charAt(i) == '/')){
                    operator = s.charAt(i++);
                    i = addNextValue(s, i, stack);
                    second = Character.getNumericValue(stack.pop());
                    if (operator == '*')
                        tmp = tmp * second;
                    else
                        tmp = tmp / second;
                }

                //add all mult/div to the result
                res += tmp;

                // add all the previous ones to the result
                while (!stack.isEmpty()) {
                    char plusorminus = stack.pop();
                    int num = Character.getNumericValue(stack.pop());
                    if (plusorminus == '+')
                        res += num;
                    else
                        res -= num;
                }

                if(i < s.length())
                    i = addNextValue(s, i, stack);
            } else {
                i = addNextValue(s, i, stack);
            }
        }

        // finish the stack
        while (!stack.isEmpty()) {
            int num = Character.getNumericValue(stack.pop());
            char plusorminus = stack.pop();
            if (plusorminus == '+')
                res += num;
            else
                res -= num;
        }

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

        System.out.println(calculate("3+ 2 * 2+2"));
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));

    }

}
