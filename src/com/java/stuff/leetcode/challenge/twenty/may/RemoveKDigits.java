package com.java.stuff.leetcode.challenge.twenty.may;

import java.util.Stack;

public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if(num.length() == k) return "0";
        Stack<Character> stack = new Stack<>();
        for(int i=0;i< num.length();i++) {
            while(k>0 && !stack.isEmpty() && stack.peek()>num.charAt(i)){
                stack.pop();
                k--;
            }

            stack.push(num.charAt(i));
        }

        while(k>0) {
            stack.pop();
            k--;
        }

        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) builder.append(stack.pop());
        builder.reverse();

        while(builder.length() > 1 && builder.charAt(0) == '0') builder.deleteCharAt(0);
        return builder.toString();
    }

    public static void main(String[] args) {
        RemoveKDigits rkd = new RemoveKDigits();
        System.out.println(rkd.removeKdigits("1432219", 3));
        System.out.println(rkd.removeKdigits("10200", 1));
        System.out.println(rkd.removeKdigits("49", 2));
        System.out.println(rkd.removeKdigits("112", 1));
        System.out.println(rkd.removeKdigits("1111111", 3));

    }

}
