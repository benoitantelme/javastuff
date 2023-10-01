package com.java.stuff.leetcode2023.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length == 1)
            return 1;

        int[][] positionSpeed = new int[position.length][];
        for (int i = 0; i < position.length; i++)
            positionSpeed[i] = new int[]{position[i], speed[i]};
        Arrays.sort(positionSpeed, (x, y) -> y[0] - x[0]);

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < positionSpeed.length; i++) {
            double time = (double) (target - positionSpeed[i][0]) / positionSpeed[i][1];
            if (!stack.isEmpty() && stack.peek() >= time)
                continue;
            else
                stack.push(time);
        }
        return stack.size();
    }

    public static void main(String[] args) {
        CarFleet cf = new CarFleet();

        System.out.println(cf.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
        System.out.println(cf.carFleet(10, new int[]{3}, new int[]{3}));
        System.out.println(cf.carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}));

//        Example 1:
//
//        Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
//        Output: 3
//        Explanation:
//        The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
//        The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
//        The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
//        Note that no other cars meet these fleets before the destination, so the answer is 3.
//
//        Example 2:
//
//        Input: target = 10, position = [3], speed = [3]
//        Output: 1
//        Explanation: There is only one car, hence there is only one fleet.
//
//                Example 3:
//
//        Input: target = 100, position = [0,2,4], speed = [4,2,1]
//        Output: 1
//        Explanation:
//        The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The fleet moves at speed 2.
//        Then, the fleet (speed 2) and the car starting at 4 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.


    }


    Stack<int[]> initStack(int[] position, int[] speed) {
        int[][] positionSpeed = new int[position.length][];
        for (int i = 0; i < position.length; i++)
            positionSpeed[i] = new int[]{position[i], speed[i]};
        Arrays.sort(positionSpeed, (x, y) -> y[0] - x[0]);

        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < position.length; i++)
            stack.push(new int[]{positionSpeed[i][0], positionSpeed[i][1]});

        return stack;
    }

    int updateStack(Stack<int[]> stack, int target) {
        int updated = 0;
        List<int[]> list = new ArrayList<>();

        int[] lastOne = stack.pop();
        lastOne[0] += lastOne[1];
        while (!stack.isEmpty()) {
            int[] previous = stack.pop();
            previous[0] += previous[1];

            if (lastOne[0] >= previous[0]) {
                if (lastOne[0] > previous[0])
                    lastOne[0] = previous[0];

                //merge in one fleet
                int slowest = Math.min(lastOne[1], previous[1]);
                lastOne = new int[]{lastOne[0], slowest};
            } else {
                if (lastOne[0] < target)
                    list.add(lastOne);
                else
                    updated++;
                lastOne = previous;
            }
        }

        if (lastOne[0] < target)
            stack.push(lastOne);
        else
            updated++;

        for (int i = list.size() - 1; i >= 0; i--)
            stack.push(list.get(i));

        return updated;
    }


    public int failedCarFleet(int target, int[] position, int[] speed) {
        if (position.length == 1)
            return 1;
        int fleets = 0;
        Stack<int[]> stack = initStack(position, speed);

        while (!stack.isEmpty()) {
            fleets += updateStack(stack, target);
        }

        return fleets;
    }

}
