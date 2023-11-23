package com.java.stuff.leetcode2023.greedy;

import java.util.Stack;

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        Stack<Integer> open = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                open.push(i);
            else if (c == ')')
                if (open.size() > 0)
                    open.pop();
                else if (star.size() > 0)
                    star.pop();
                else
                    return false;
            else
                star.push(i);
        }

        while (!open.isEmpty()) {
            int i = open.pop();
            if (star.isEmpty() || star.peek() < i)
                return false;
            else
                star.pop();
        }

        return true;
    }

    public static void main(String[] args) {
        ValidParenthesisString vps = new ValidParenthesisString();

        System.out.println(vps.checkValidString("()"));
        System.out.println(vps.checkValidString("(*)"));
        System.out.println(vps.checkValidString("(*))"));
        System.out.println(vps.checkValidString("("));
        System.out.println(vps.checkValidString(")"));
        System.out.println(vps.checkValidString("())"));
        System.out.println(vps.checkValidString("(()"));
        System.out.println(vps.checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))((((" +
                "(())*)))()))(()((*()*(*)))(*)()"));
        System.out.println(vps.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())(" +
                "(*()()(((()((()*(())*(()**)()(())"));


//        Example 1:
//        Input: s = "()"
//        Output: true
//
//        Example 2:
//        Input: s = "(*)"
//        Output: true
//
//        Example 3:
//        Input: s = "(*))"
//        Output: true
    }

    public boolean checkValidStringDoesntWork(String s) {
        int open = 0;
        int close = 0;
        int joker = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else if (c == ')') {
                close++;
                if (open >= close)
                    continue;

                if (open == close - 1) {
                    if (joker != 0) {
                        joker--;
                        open++;
                    }
                } else
                    return false;
            } else {
                joker++;
            }
        }

        if (open != close) {
            if (joker == 0)
                return false;

            if (joker < Math.abs(open - close))
                return false;
        }

        return true;
    }

}
