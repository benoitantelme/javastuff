package com.java.stuff.leetcode2023.stack;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {
    PriorityQueue<Integer> pq;
    Stack<Integer> stack;


    public MinStack() {
        pq = new PriorityQueue<>();
        stack = new Stack<>();
    }

    public void push(int val) {
        pq.add(val);
        stack.push(val);
    }

    public void pop() {
        pq.remove(stack.pop());
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return pq.peek();
    }




}

