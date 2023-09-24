package com.java.stuff.leetcode2023.arrays_hashing;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (null == s || null == t)
            return false;
        if (s.length() != t.length())
            return false;

        int[] counters = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counters[s.charAt(i) - 'a']++;
            counters[t.charAt(i) - 'a']--;
        }

        for (int n : counters)
            if (n != 0)
                return false;

        return true;
    }

    public static void main(String args[]) {
        ValidAnagram va = new ValidAnagram();

        System.out.println(va.isAnagram("anagram", "nagaram"));
        System.out.println(va.isAnagram("rat", "car"));

        System.out.println(va.isAnagram("aacc", "ccac"));


        System.out.println(va.isAnagram("r", "car"));
        System.out.println(va.isAnagram("", "car"));
        System.out.println(va.isAnagram(null, "car"));

    }

    public boolean isUglyAnagram(String s, String t) {
        if (null == s || null == t)
            return false;
        if (s.length() != t.length())
            return false;

        Map<Integer, Integer> map = new HashMap<>();

        for (int n : s.codePoints().toArray())
            map.merge(n, 1, (oldV, newV) -> oldV + 1);

        for (int n : t.codePoints().toArray()) {
            if (!map.containsKey(n)) {
                return false;
            } else {
                int newCount = map.computeIfPresent(n, (old, newV) -> newV - 1);
                if (newCount < 0)
                    return false;
            }
        }

        return true;
    }


}
