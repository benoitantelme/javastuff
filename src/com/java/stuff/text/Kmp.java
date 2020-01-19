package com.java.stuff.text;

import java.util.Arrays;

/**
 * Find occurences of a pattern in a string by using a longer proper prefix suffix
 * not to start at every single character
 */
public class Kmp {

    private static int[] computeLongerProperPrefixSuffix(String pattern) {
        int[] lpps = new int[pattern.length()];
        lpps[0] = 0;

        int length = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lpps[i] = length;
                i++;
            } else if (length > 0)
                //search for a previously seen character in the pattern
                length = lpps[--length];
            else {
                //reset on the pattern and move on
                lpps[i] = 0;
                i++;
            }
        }

        return lpps;
    }
    static void KMPSearch(String pattern, String text) {
        int[] lpps = computeLongerProperPrefixSuffix(pattern);
        System.out.println("Longer proper prefix suffix: " + Arrays.toString(lpps));

        int i = 0, j = 0;
        while(i < text.length()){
            if(pattern.charAt(j) == text.charAt(i)){
                i++;
                j++;
            }else{
                if(j > 0)
                    j = lpps[j-1];
                else{
                    i++;
                }
            }

            if(j == pattern.length()){
                System.out.println("Pattern found starting at " + (i-j));
                j = lpps[j-1];
            }
        }

    }

    public static void main(String args[]) {
        String txt = "AAAAABAAABA";
        String pattern =   "AAAA";
        System.out.println("For text: " + txt);
        System.out.println("And pattern: " + pattern);
        new Kmp().KMPSearch(pattern, txt);

        txt = "ABABDABACDABABCABAB";
        pattern = "ABABCABAB";
        System.out.println("For text: " + txt);
        System.out.println("And pattern: " + pattern);
        new Kmp().KMPSearch(pattern, txt);

    }
}
