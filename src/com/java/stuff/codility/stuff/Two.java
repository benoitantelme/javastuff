package com.java.stuff.codility.stuff;

import java.util.Stack;

public class Two {

    public static void main(String[] args) {
        Two two = new Two();

        System.out.println(two.solution("4 5 6 - 7 +"));

        System.out.println(two.solution("13 DUP 4 POP 5 DUP + DUP + -"));
    }

    protected static final int MIN = 0;
    protected static final int MAX = (int) (Math.pow(2, 20) - 1);
    protected final Stack<Integer> stack = new Stack<>();

    public int solution(String S) {
        try {
            for (String input : S.split(" "))
                process(input);

            return pop();
        } catch (Exception e) {
            return -1;
        }
    }

    protected void process(String s) throws Exception {
        try {
            int num = Integer.parseInt(s);
            push(num);
        } catch (NumberFormatException nfe) {
            switch (s) {
                case "+":
                    plus();
                    break;
                case "-":
                    minus();
                    break;
                case "POP":
                    pop();
                    break;
                case "DUP":
                    dup();
                default:
                    //break
            }
        }

    }

    protected void push(int i) throws Exception {
        if (outOfRange(i))
            throw new Exception("out of range");

        stack.push(i);
    }

    protected void plus() throws Exception {
        if (!contains(2))
            throw new Exception("missing elements");

        push(stack.pop() + stack.pop());
    }

    protected void minus() throws Exception {
        if (!contains(2))
            throw new Exception("missing elements");

        push(stack.pop() - stack.pop());
    }

    protected int pop() {
        return stack.pop();
    }

    protected void dup() throws Exception {
        if (!contains(1))
            throw new Exception("missing elements");

        push(stack.peek());
    }

    protected boolean outOfRange(int i) {
        if (MIN > i || i > MAX)
            return true;
        else
            return false;
    }

    protected boolean contains(int i) {
        if (stack.size() < i)
            return false;
        else
            return true;
    }

}
