package com.java.stuff.leetcode2023.stack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
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


}
