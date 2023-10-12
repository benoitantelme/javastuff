package com.java.stuff.leetcode2023.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {

    public boolean matches(String substring, Map<Integer, Integer> toFind) {
        int[] coded = new int[26];
        for (char c : substring.toCharArray())
            coded[c - 'a'] += 1;

        for (Map.Entry<Integer, Integer> entry : toFind.entrySet())
            if (coded[entry.getKey()] != entry.getValue())
                return false;

        return true;
    }

    public boolean checkInclusion(String s1, String s2) {
        boolean result = false;

        Map<Integer, Integer> toFind = new HashMap<>();
        for (char c : s1.toCharArray()) {
            toFind.merge(c - 'a', 1, (x, y) -> x + 1);
        }

        int left = 0;
        int right = left + s1.length();

        while (right <= s2.length()) {
            String substring = s2.substring(left, right);

            if (matches(substring, toFind))
                return true;
            else {
                left += 1;
                right += 1;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        PermutationInString pis = new PermutationInString();
        System.out.println(pis.checkInclusion("ab", "eidbaooo"));
        System.out.println(pis.checkInclusion("ab", "eidboaoo"));


//        Example 1:
//        Input: s1 = "ab", s2 = "eidbaooo"
//        Output: true
//        Explanation: s2 contains one permutation of s1 ("ba").
//
//                Example 2:
//        Input: s1 = "ab", s2 = "eidboaoo"
//        Output: false
    }
}
