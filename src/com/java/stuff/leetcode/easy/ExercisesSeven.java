package com.java.stuff.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExercisesSeven {

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();

        for(int i = left; i <= right; i++){
            int tmp = i;
            boolean passed = true;

            while(tmp > 0) {
                int digit = tmp % 10;
                if(digit == 0 || i % digit != 0) {
                    passed = false;
                    break;
                }

                tmp = tmp / 10;
            }
            if(passed)
                result.add(i);
        }

        return result;
    }

    public static int[] diStringMatch(String S) {
        int length = S.length();
        int[] result = new int[length+1];

        int l = 0;
        int r = length;
        int i = 0;

        while(l != r)
            if(S.charAt(i) == 'I')
                result[i++] = l++;
            else
                result[i++] = r--;

        result[length] = l;

        return result;
    }

    public static boolean divisorGame(int N) {
        boolean aliceWins = false;

        while (N > 1){
            if(N % 2 == 0)
                N = N - 1;
            else
                for(int i = N-1; i > 0; i--)
                    if(N % i == 0){
                        N = N - i;
                        break;
                    }

            aliceWins = !aliceWins;
        }

        return aliceWins;
    }

//    public static int minCostToMoveChips(int[] chips) {
//        int odd = 0;
//        int even = 0;
//
//        for(int n : chips)
//            if (n % 2 == 0)
//                even++;
//            else
//                odd++;
//
//        // free moves for +/-2
//        if(odd == 0 || even ==0)
//            return 0;
//
//        int cost = 0;
//        boolean maxIsEven = even > odd;
//
//        for(int i = 0; i < chips.length; i++)
//            if(maxIsEven ^ chips[i] % 2 == 0)
//                cost++;
//
//        return cost;
//    }
    public static int minCostToMoveChips(int[] chips) {
        int odd = 0;
        int even = 0;

        for(int n : chips)
            if (n % 2 == 0)
                even++;
            else
                odd++;

        return Math.min(even, odd);
    }

    public static void main(String args[]) {
        System.out.println(selfDividingNumbers(1, 22));
        System.out.println(selfDividingNumbers(119, 123));

        System.out.println(Arrays.toString(diStringMatch("DDI")));

        System.out.println("divisorGame 1 " + divisorGame(1));
        System.out.println("divisorGame 2 " + divisorGame(2));
        System.out.println("divisorGame 3 " + divisorGame(3));
        System.out.println("divisorGame 5 " + divisorGame(5));
        System.out.println("divisorGame 9 " + divisorGame(9));


        System.out.println("cost to move: " + minCostToMoveChips(new int[]{2,2,2,3,3}));
    }
}
