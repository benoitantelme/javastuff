package com.java.stuff.leetcode2023.stack;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                else {
                    char previous = stack.pop();
                    switch (c) {
                        case ')' -> {
                            if (previous != '(')
                                return false;
                        }
                        case ']' -> {
                            if (previous != '[')
                                return false;
                        }
                        case '}' -> {
                            if (previous != '{')
                                return false;
                        }
                        default -> {
                            return false;
                        }
                    }
                }
            }
        }

        return stack.isEmpty();
    }


    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();

        System.out.println(vp.isValid("()"));
        System.out.println(vp.isValid("()[]{}"));
        System.out.println(vp.isValid("(]"));
        System.out.println(vp.isValid("([)]"));
        System.out.println(vp.isValid("{[]}"));
        System.out.println(vp.isValid("["));
    }

}
