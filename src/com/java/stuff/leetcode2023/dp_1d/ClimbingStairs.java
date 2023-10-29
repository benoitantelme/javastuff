package com.java.stuff.leetcode2023.dp_1d;

import java.util.ArrayList;
import java.util.List;

public class ClimbingStairs {

    public int climbStairs(int n) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(1);

        while (l.size() < n + 1) {
            l.add(l.get(l.size() - 1) + l.get(l.size() - 2));
        }

        return l.get(n);
    }

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();

        System.out.println(cs.climbStairs(2));
        System.out.println(cs.climbStairs(3));
        System.out.println(cs.climbStairs(44));

//        Example 1:
//        Input: n = 2
//        Output: 2
//        Explanation: There are two ways to climb to the top.
//        1. 1 step + 1 step
//        2. 2 steps
//
//        Example 2:
//        Input: n = 3
//        Output: 3
//        Explanation: There are three ways to climb to the top.
//        1. 1 step + 1 step + 1 step
//        2. 1 step + 2 steps
//        3. 2 steps + 1 step
    }

}
