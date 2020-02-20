package com.java.stuff.leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

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

            if(zeros)
                res[i] = 0;
            else
                res[i] = 1;


            d = c;
        }

        return res;
}

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxDepthAfterSplit("(()())")));
        System.out.println(Arrays.toString(maxDepthAfterSplit("()(())()")));
        System.out.println(Arrays.toString(maxDepthAfterSplit("(((()))((())))")));
    }

}
