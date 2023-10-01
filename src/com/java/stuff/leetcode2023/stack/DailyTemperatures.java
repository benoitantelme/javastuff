package com.java.stuff.leetcode2023.stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int previous = stack.pop();
                result[previous] = i - previous;
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        System.out.println(Arrays.toString(dt.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(dt.dailyTemperatures(new int[]{30, 40, 50, 60})));
        System.out.println(Arrays.toString(dt.dailyTemperatures(new int[]{30, 60, 90})));


//        Example 1:
//
//        Input: temperatures = [73,74,75,71,69,72,76,73]
//        Output: [1,1,4,2,1,1,0,0]
//
//        Example 2:
//
//        Input: temperatures = [30,40,50,60]
//        Output: [1,1,1,0]
//
//        Example 3:
//
//        Input: temperatures = [30,60,90]
//        Output: [1,1,0]

    }

    class Temperature {
        int value;
        int position;

        public Temperature(int value, int position) {
            this.value = value;
            this.position = position;
        }
    }

    public int[] slowDailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Temperature> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty())
                stack.push(new Temperature(temperatures[i], i));
            else {
                Temperature temp = new Temperature(temperatures[i], i);
                while (!stack.isEmpty() && stack.peek().value < temp.value) {
                    result[stack.peek().position] = temp.position - stack.peek().position;
                    stack.pop();
                }
                stack.push(temp);
            }
        }

        return result;
    }


}
