package com.java.stuff.leetcode2023.dp_1d;

public class DecodeWays {

    public int numDecodings(String s) {
        int[] cache = new int[s.length() + 1];

        //at 1 in case the first two digits are valid
        cache[0] = 1;
        if (s.charAt(0) != '0')
            cache[1] = 1;

        for (int i = 2; i < s.length() + 1; i++) {
            if (s.charAt(i - 1) != '0')
                cache[i] += cache[i - 1];

            if (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')
                cache[i] += cache[i - 2];
        }

        return cache[s.length()];
    }

    public static void main(String[] args) {
        DecodeWays dw = new DecodeWays();

        System.out.println(dw.numDecodings("12"));
        System.out.println(dw.numDecodings("226"));
        System.out.println(dw.numDecodings("06"));
        System.out.println(dw.numDecodings("10"));
        System.out.println(dw.numDecodings("123123"));
        System.out.println(dw.numDecodings("2611055971756562"));

//        Example 1:
//        Input: s = "12"
//        Output: 2
//        Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
//
//                Example 2:
//        Input: s = "226"
//        Output: 3
//        Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
//
//        Example 3:
//        Input: s = "06"
//        Output: 0
//        Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
    }


    int rec(String s, int index, Integer[] cache) {
        if (index == s.length())
            return 1;

        if (s.charAt(index) == '0')
            return 0;

        if (cache[index] != null)
            return cache[index];

        int count = 0;
        count += rec(s, index + 1, cache);

        if (index < s.length() - 1 &&
                (s.charAt(index) == '1' || s.charAt(index) == '2' && s.charAt(index + 1) < '7'))
            count += rec(s, index + 2, cache);

        cache[index] = count;
        return count;
    }

    public int numDecodingsTopDownMemo(String s) {
        return rec(s, 0, new Integer[s.length()]);
    }

    // SLOW without mem

    int result2;

    void rec2(String s) {
        if (s.length() == 1 && !s.equals("0")) {
            result2++;
            return;
        }

        if (!s.substring(0, 1).equals("0"))
            rec2(s.substring(1, s.length()));

        if (s.length() >= 2 && !s.substring(0, 1).equals("0") && Integer.valueOf(s.substring(0, 2)) <= 26) {
            if (s.length() == 2)
                result2++;
            else
                rec2(s.substring(2, s.length()));
        }
    }


    public int numDecodings2(String s) {
        result2 = 0;
        rec2(s);

        return result2;
    }


}
