package com.java.stuff.leetcode2023.dp_1d;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] cache = new boolean[s.length() + 1];
        cache[s.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (String word : wordDict) {
                int endIndex = i + word.length();
                if (endIndex <= s.length() && s.startsWith(word, i)) {
                    cache[i] = cache[endIndex];
                }

                // solved already
                if (cache[i])
                    break;
            }
        }

        return cache[0];
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();

        System.out.println(wb.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wb.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(wb.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(wb.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa",
                        "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
        System.out.println(wb.wordBreak("cars", Arrays.asList("car", "ca", "rs")));


//        Example 1:
//        Input: s = "leetcode", wordDict = ["leet","code"]
//        Output: true
//        Explanation: Return true because "leetcode" can be segmented as "leet code".
//
//                Example 2:
//        Input: s = "applepenapple", wordDict = ["apple","pen"]
//        Output: true
//        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//                Note that you are allowed to reuse a dictionary word.
//
//                Example 3:
//        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//        Output: false
    }

    public boolean tooSlowWordBreak(String s, List<String> wordDict) {
        int maxLeft = 0;

        LinkedList<Integer> indexes = new LinkedList<>();
        indexes.add(0);
        while (maxLeft < s.length() && !indexes.isEmpty()) {
            int index = indexes.removeLast();
            for (String word : wordDict) {
                if (index + word.length() <= s.length() &&
                        s.substring(index, index + word.length()).equals(word)) {
                    indexes.add(index + word.length());
                    maxLeft = Math.max(maxLeft, index + word.length());
                }
            }
        }

        return maxLeft == s.length();
    }

}
