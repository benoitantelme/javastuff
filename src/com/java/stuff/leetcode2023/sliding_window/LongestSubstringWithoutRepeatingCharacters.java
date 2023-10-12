package com.java.stuff.leetcode2023.sliding_window;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    int containsAt(String s, String c) {
        if (s.contains(c)) {
            return s.indexOf(c);
        } else {
            return -1;
        }
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();

        int max = 0;

        int left = 0;
        int right = 1;

        while (left <= s.length() - 1) {
            int containedAt = containsAt(s.substring(left, right - 1), s.substring(right - 1, right));
            if (containedAt == -1) {
                String substring = s.substring(left, right);
                int length = substring.length();
                if (length > max)
                    max = length;

                if (right <= s.length() - 1)
                    right += 1;
                else
                    left += 1;
            } else {
                if (left < right)
                    left = left + containedAt + 1;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters lngst = new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(lngst.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lngst.lengthOfLongestSubstring("bbbbb"));
        System.out.println(lngst.lengthOfLongestSubstring("pwwkew"));
        System.out.println(lngst.lengthOfLongestSubstring("au"));


//        Example 1:
//        Input: s = "abcabcbb"
//        Output: 3
//        Explanation: The answer is "abc", with the length of 3.
//
//        Example 2:
//        Input: s = "bbbbb"
//        Output: 1
//        Explanation: The answer is "b", with the length of 1.
//
//        Example 3:
//        Input: s = "pwwkew"
//        Output: 3
//        Explanation: The answer is "wke", with the length of 3.
//        Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
    }

    boolean isUnique(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray())
            if (set.contains(c))
                return false;
            else
                set.add(c);

        return true;
    }

    public int bitSlowLengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();

        int max = 0;

        int left = 0;
        int right = 1;

        while (left <= s.length() - 1) {
            String substring = s.substring(left, right);
            if (isUnique(substring)) {
                int length = substring.length();
                if (length > max)
                    max = length;

                if (right <= s.length() - 1)
                    right += 1;
                else
                    left += 1;
            } else {
                if (left < right)
                    left += 1;
            }
        }

        return max;
    }

}