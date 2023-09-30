package com.java.stuff.leetcode2023.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParentheses {

    class Node {
        String str;
        int open;
        int close;

        public Node(String str, int open, int close) {
            this.str = str;
            this.open = open;
            this.close = close;
        }
    }

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        Stack<Node> stack = new Stack<>();

        stack.push(new Node("(", 1, 0));
        while (!stack.isEmpty()) {
            Node next = stack.pop();
            if (next.str.length() == n * 2) {
                result.add(next.str);
                continue;
            }

            if (next.open < n)
                stack.push(new Node(next.str + "(", next.open + 1, next.close));

            if (next.open > next.close)
                stack.push(new Node(next.str + ")", next.open, next.close + 1));
        }

        return result;
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        System.out.println(gp.generateParenthesis(3));
        System.out.println(gp.generateParenthesis(1));


//        Input: n = 3
//        Output: ["((()))","(()())","(())()","()(())","()()()"]
//
//        Example 2:
//
//        Input: n = 1
//        Output: ["()"]

    }

    public List<String> recGenerateParenthesis(int n) {
        backTrack(0, 0, "", n);

        return result;
    }

    public void backTrack(int open, int closed, String str, int n) {
        if (open == closed && closed == n) {
            result.add(str);
            return;
        }
        if (open < n) {
            backTrack(open + 1, closed, str + "(", n);
        }
        if (open > closed) {
            backTrack(open, closed + 1, str + ")", n);
        }
    }


}
