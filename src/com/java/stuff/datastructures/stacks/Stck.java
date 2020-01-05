package com.java.stuff.datastructures.stacks;

import java.util.Arrays;
import java.util.Stack;

public class Stck {

    static int[] calculateSpan(int[] prices){
        int length = prices.length;
        int[] spans = new int[length];
        Stack<Integer> stack = new Stack();

        //initialization with first index and span
        stack.push(0);
        spans[0] = 1;

        for (int i = 1;i < length;i++) {
            //if prices to the left are lower
            while (!stack.empty() && prices[stack.peek()] <= prices[i])
                stack.pop();

            //if empty stack, price is higher than all previous ones, else higher up to the index on the stack
            spans[i] = (stack.empty()) ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }

        return spans;
    }

    public static void main(String[] args) {
        int price[] = {100, 80, 60, 70, 60, 75, 85};

        System.out.println("Spans for {100, 80, 60, 70, 60, 75, 85}: " + Arrays.toString(calculateSpan(price)));
    }
}
