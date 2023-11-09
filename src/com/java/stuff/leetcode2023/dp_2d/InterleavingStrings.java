package com.java.stuff.leetcode2023.dp_2d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterleavingStrings {

    String rec(String s1, String s2, String s3, Map<List<Integer>, String> cache) {
        if (s3.length() == 0) {
            if (s1.isEmpty() && s2.isEmpty())
                return s3;
            else if (s1.isEmpty())
                return s2;
            else
                return s1;
        }

        List<Integer> key = List.of(s1.length(), s2.length());
        if (cache.containsKey(key))
            return cache.get(key);

        char target = s3.charAt(0);
        char c1 = s1.length() > 0 ? s1.charAt(0) : '!';
        char c2 = s2.length() > 0 ? s2.charAt(0) : '!';

        String res1 = null;
        String res2 = null;
        if (target == c1)
            res1 = rec(s1.substring(1), s2, s3.substring(1), cache);
        if (target == c2)
            res2 = rec(s1, s2.substring(1), s3.substring(1), cache);

        String result;
        if (res1 == null & res2 == null)
            result = s3;
        else if (res1 == null)
            result = res2;
        else if (res2 == null)
            result = res1;
        else if (res1.length() < res2.length())
            result = res1;
        else
            result = res2;

        cache.put(key, result);
        return result;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.isEmpty() && s1.isEmpty() && s2.isEmpty())
            return true;
        if (s3.length() == s1.length() && s3.length() == s2.length() && !s3.isEmpty())
            return false;

        String result = rec(s1, s2, s3, new HashMap<>());
        return result != null && result.isEmpty();
    }

    public static void main(String[] args) {
        InterleavingStrings is = new InterleavingStrings();

        System.out.println(is.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(is.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(is.isInterleave("", "", ""));
        System.out.println(is.isInterleave("a", "b", "a"));
        System.out.println(is.isInterleave("", "abc", "ab"));

//        Example 1:
//        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//        Output: true
//        Explanation: One way to obtain s3 is:
//        Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
//                Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
//                Since s3 can be obtained by interleaving s1 and s2, we return true.
//
//                Example 2:
//        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//        Output: false
//        Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
//
//                Example 3:
//        Input: s1 = "", s2 = "", s3 = ""
//        Output: true
    }


}
