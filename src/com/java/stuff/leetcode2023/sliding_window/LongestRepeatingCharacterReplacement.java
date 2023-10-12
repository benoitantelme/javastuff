package com.java.stuff.leetcode2023.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {


    public int characterReplacement(String s, int k) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            int tmpMax = map.merge(s.charAt(right), 0, (x, y) -> x + 1);
            max = Math.max(max, tmpMax);

            if (right - left - max > k) {
                map.computeIfPresent(s.charAt(left), (x, y) -> y - 1);
                left += 1;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement lr = new LongestRepeatingCharacterReplacement();
        System.out.println(lr.characterReplacement("ABAB", 2));
        System.out.println(lr.characterReplacement("AABABBA", 1));


//        Example 1:
//        Input: s = "ABAB", k = 2
//        Output: 4
//        Explanation: Replace the two 'A's with two 'B's or vice versa.
//
//        Example 2:
//        Input: s = "AABABBA", k = 1
//        Output: 4
//        Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//                The substring "BBBB" has the longest repeating letters, which is 4.
//        There may exists other ways to achive this answer too.
    }

}
