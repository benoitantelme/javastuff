package com.java.stuff.leetcode2023.greedy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sGas = 0, sCost = 0;
        for (int i = 0; i < gas.length; i++) {
            sGas += gas[i];
            sCost += cost[i];
        }

        if (sGas < sCost)
            return -1;

        int res = 0, total = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (total < 0) {
                total = 0;
                res = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GasStation gs = new GasStation();

        System.out.println(gs.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(gs.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(gs.canCompleteCircuit(new int[]{4}, new int[]{5}));
        System.out.println(gs.canCompleteCircuit(new int[]{2}, new int[]{2}));
        System.out.println(gs.canCompleteCircuit(new int[]{6, 1, 4, 3, 5}, new int[]{3, 8, 2, 4, 2}));


//        Example 1:
//        Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//        Output: 3
//        Explanation:
//        Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//        Travel to station 4. Your tank = 4 - 1 + 5 = 8
//        Travel to station 0. Your tank = 8 - 2 + 1 = 7
//        Travel to station 1. Your tank = 7 - 3 + 2 = 6
//        Travel to station 2. Your tank = 6 - 4 + 3 = 5
//        Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
//        Therefore, return 3 as the starting index.
//
//        Example 2:
//        Input: gas = [2,3,4], cost = [3,4,3]
//        Output: -1
//        Explanation:
//        You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
//        Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//        Travel to station 0. Your tank = 4 - 3 + 2 = 3
//        Travel to station 1. Your tank = 3 - 3 + 3 = 3
//        You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
//        Therefore, you can't travel around the circuit once no matter where you start.
    }

    List<Integer> order(int[] gas, int[] cost) {
        int worst = Integer.MIN_VALUE;

        for (int i = 0; i < gas.length; i++)
            if (cost[i] - gas[i] > worst)
                worst = i;

        int best = worst == gas.length - 1 ? 0 : worst + 1;

        List<Integer> order;
        if (best == 0)
            order = IntStream.
                    range(0, gas.length)
                    .boxed()
                    .collect(Collectors.toList());
        else {
            order = IntStream.
                    range(best, gas.length)
                    .boxed()
                    .collect(Collectors.toList());
            order.addAll(IntStream.
                    range(0, best)
                    .boxed()
                    .collect(Collectors.toList()));
        }

        return order;
    }

    public int canAlmostCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 1)
            return gas[0] >= cost[0] ? 0 : -1;

        List<Integer> order = order(gas, cost);

        int start = order.get(0);
        order.add(start);
        boolean ok = true;
        int fuel = gas[start];
        for (Integer n : order) {
            fuel = fuel - cost[n];
            if (fuel < 0) {
                ok = false;
                break;
            }
            fuel = fuel + gas[n + 1 < gas.length ? n + 1 : 0];
        }


        return ok == true ? start : -1;
    }

}
