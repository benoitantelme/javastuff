package com.java.stuff.leetcode;

import java.util.HashSet;
import java.util.Set;

public class ExercisesTwo {

    public static  int findNumbers(int[] nums) {
        int counter = 0;

        for(int nbr : nums) {
            int divided = nbr;
            int digits;
            for (digits = 0; divided>0; digits++)
                divided = divided/10;

            if(digits%2 == 0)
                counter++;
        }

        return counter;
    }

    public static int subtractProductAndSum(int n) {
        int prod = 1;
        int sum = 0;

        while(n > 0){
            int digit = n % 10;
            sum += digit;
            prod = prod * digit;

            n = n / 10;
        }

        return prod - sum;
    }

    public static int maximum69Number (int num) {
        int n = num;

        for(int i = 3; i >= 0; i--){
            int tenPower = (int) Math.pow(10,i);
            int digit = n / tenPower;
            if(digit == 6)
                return num + (3 * tenPower);
            else
                n = n - digit * tenPower;
        }

        return num;
    }

    public static int numJewelsInStones(String J, String S) {
        int result = 0;

        Set<Character> set = new HashSet<>();
        for(Character c : J.toCharArray())
            set.add(c);

        for(Character c : S.toCharArray())
            if(set.contains(c))
                result++;

        return result;
    }

    public static String toLowerCase(String str) {
        int length = str.length();
        char[] res = new char[length];
        char[] input = str.toCharArray();
        for(int i =0; i < length; i++)
            if(input[i] < 97)
                res[i] = (char) ((int) input[i]+32);
            else
                res[i] = input[i];

        return new String(res);
    }

    public static void main(String args[]) {
        int[] in = new int[]{12,345,2,6,7896};
        System.out.println(findNumbers(in));

        System.out.println(subtractProductAndSum(234));
        System.out.println(maximum69Number(9669));
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(toLowerCase("LOVELY"));
    }
}
