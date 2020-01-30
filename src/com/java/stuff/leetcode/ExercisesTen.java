package com.java.stuff.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ExercisesTen {

    public static String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int countL = 0;
        int countR = 0;

        Queue<Character> queue = new LinkedList<>();
        for(char c : S.toCharArray()){
            if(c == '(')
                countL++;
            else
                countR++;

            queue.add(c);
            if(countL == countR){
                queue.poll();

                while(queue.size() > 1)
                    sb.append(queue.poll());

                queue.poll();
            }
        }

        return sb.toString();
    }

    public static String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();

        char[] sAsChars = S.toCharArray();
        stack.push(sAsChars[0]);

        for(int i = 1; i < sAsChars.length; i++){
            if(!stack.isEmpty())
                if(stack.peek() == sAsChars[i])
                    stack.pop();
                else
                    stack.push(sAsChars[i]);
            else
                stack.push(sAsChars[i]);
        }

        char[] result = new char[stack.size()];
        for(int i = stack.size()-1; i >= 0; i --)
            result[i] = stack.pop();

        return new String(result);
    }

    public static int calPoints(String[] ops) {
        int result = 0;

        Stack<String> stack = new Stack<>();
        for(String op : ops){
            if(op.equals("+")){
                String poppedFirst = stack.pop();
                String poppedSecond = stack.pop();
                Integer sum = Integer.valueOf(poppedFirst) + Integer.valueOf(poppedSecond);
                result += sum;

                stack.push(poppedSecond);
                stack.push(poppedFirst);
                stack.push(String.valueOf(sum));
            }else if(op.equals("C")){
                result -= Integer.valueOf(stack.pop());
            }else if(op.equals("D")){
                String popped = stack.pop();
                Integer doubled = Integer.valueOf(popped) * 2;
                result += doubled;

                stack.push(popped);
                stack.push(String.valueOf(doubled));
            }else{
                result += Integer.valueOf(op);

                stack.push(op);
            }
        }

        return result;
    }

    public static void main(String args[]) {
        System.out.println(removeOuterParentheses("(()())(())"));

        System.out.println(removeDuplicates("abbaca"));

        System.out.println(calPoints(new String[]{"5","2","C","D","+"}));
        System.out.println(calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));
    }

}
