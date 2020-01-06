package com.java.stuff.datastructures.stacks;

import java.util.Arrays;
import java.util.Stack;

public class Stck {

    static int[] calculateSpan(int[] prices) {
        int length = prices.length;
        int[] spans = new int[length];
        Stack<Integer> stack = new Stack();

        //initialization with first index and span
        stack.push(0);
        spans[0] = 1;

        for (int i = 1; i < length; i++) {
            //if prices to the left are lower
            while (!stack.empty() && prices[stack.peek()] <= prices[i])
                stack.pop();

            //if empty stack, price is higher than all previous ones, else higher up to the index on the stack
            spans[i] = (stack.empty()) ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }

        return spans;
    }

    static boolean isBalanced(char[] in) {
        Stack<Character> stack = new Stack<>();
        stack.push(in[0]);

        for (int i = 1; i < in.length; i++) {
            char c = in[i];
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else if (c == ')' || c == ']' || c == '}')
                if (stack.isEmpty() || closing(stack.pop(), c))
                    return false;
        }

        return true;
    }

    static boolean closing(char a, char b) {
        boolean result = false;
        switch (a) {
            case '(':
                if (b == ')')
                    result = true;
                break;
            case '[':
                if (b == ']')
                    result = true;
                break;
            case '{':
                if (b == '}')
                    result = true;
                break;
        }

        return result;
    }

    /**
     * @param a            index of first person
     * @param b            index of second person
     * @param peopleMatrix matrix of who knows who
     * @return if a knows b
     */
    static boolean knows(int a, int b, int[][] peopleMatrix) {
        boolean res = (peopleMatrix[a][b] == 1) ?
                true :
                false;
        return res;
    }

    /**
     * @param peopleMatrix matrix of who knows who at the party
     * @return who is the celebrity (the person who everyone knows but knows no one)
     */
    static int celebrityIs(int[][] peopleMatrix) {
        //initialize stack of people
        Stack<Integer> peoples = new Stack<>();
        for (int i = 0; i < peopleMatrix.length; i++)
            peoples.push(i);

        // remove people who know someone
        while (peoples.size() > 1) {
            int celebrityOne = peoples.pop();
            int celebrityTwo = peoples.pop();
            if (!knows(celebrityOne, celebrityTwo, peopleMatrix))
                peoples.push(celebrityOne);
            if (!knows(celebrityTwo, celebrityOne, peopleMatrix))
                peoples.push(celebrityTwo);
        }

        int lastPerson = peoples.pop();

        // if last person knows someone it is not the celebrity
        for (int i = 0; i < peopleMatrix.length; i++)
            if (peopleMatrix[lastPerson][i] == 1)
                return -1;

        return lastPerson;
    }

    static void towerOfHanoi(int n) {
        Stack<Integer> src = new Stack<>();
        Stack<Integer> dest = new Stack<>();
        Stack<Integer> aux = new Stack<>();

        String srcName = "src";
        String destName = "dest";
        String auxName = "aux";

        // If number of disks is even, switch destination and auxiliary poles
        if (n % 2 == 0)
        {
            destName = "aux";
            auxName  = "dest";
        }

        int moves = (int) (Math.pow(2, n) - 1);

        for (int i = n; i > 0; i--)
            src.push(i);

        for (int i = 1; i <= moves; i++) {
            if (i % 3 == 1)
                moveDisks(src, dest, srcName, destName);
            else if (i % 3 == 2)
                moveDisks(src, aux, srcName, auxName);
            else if (i % 3 == 0)
                moveDisks(aux, dest, auxName, destName);
        }
    }

    static void moveDisks(Stack<Integer> src, Stack<Integer> dest, String srcName, String destName) {
        int srcTopDisk;
        int destTopDisk;

        if (src.isEmpty()) {
            destTopDisk = dest.pop();
            src.push(destTopDisk);
            System.out.println("Moved disk " + destTopDisk + " from " + destName + " to " + srcName);
        } else if (dest.isEmpty()) {
            srcTopDisk = src.pop();
            dest.push(srcTopDisk);
            System.out.println("Moved disk " + srcTopDisk + " from " + srcName + " to " + destName);
        } else {
            srcTopDisk = src.peek();
            destTopDisk = dest.peek();

            if (srcTopDisk > destTopDisk) {
                src.push(dest.pop());
                System.out.println("Moved disk " + destTopDisk + " from " + destName + " to " + srcName);
            } else {
                dest.push(src.pop());
                System.out.println("Moved disk " + srcTopDisk + " from " + srcName + " to " + destName);
            }
        }
    }

    public static void main(String[] args) {
        //span of price: how many quotes has it been the higher price
        int price[] = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Spans for {100, 80, 60, 70, 60, 75, 85}: " + Arrays.toString(calculateSpan(price)));

        // is the expression balance: parenthesis ans such closed properly
        char[] exp = {'{', '(', ')', '}', '[', ']'};
        System.out.println("Sequence is balanced: " + isBalanced(exp));

        exp = new char[]{'{', '(', '}', '[', ']'};
        System.out.println("Sequence is balanced: " + isBalanced(exp));

        // who is the celebrity, the person who knows no one but everyone knows
        int matrix[][] = {
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0}};
        System.out.println("For: " + Arrays.deepToString(matrix));
        System.out.println("The celebrity is: " + celebrityIs(matrix));

        matrix = new int[][]{
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0}};
        System.out.println("For: " + Arrays.deepToString(matrix));
        System.out.println("The celebrity is: " + celebrityIs(matrix));

        //hanoi with a stack
        System.out.println();
        towerOfHanoi(2);
        System.out.println();
        towerOfHanoi(3);
    }

}
