package com.java.stuff.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

public class ExercisesThirteen {

    public static int hammingDistance(int x, int y) {
        int res = 0;

        int z = x ^ y;

        while(z > 0){
            res += z & 1;
            z = z >> 1;
        }

        return res;
    }

    public static int findComplement(int num) {
        int temp = num;
        int length = 0;
        int mask = 0;
        while(temp > 0){
            temp = temp >> 1;
            mask += (int) Math.pow(2, length++);
        }

        return num ^ mask;
    }

    public static int singleNumber2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums)
            if(set.contains(num))
                set.remove(num);
            else
                set.add(num);

        return (int) set.toArray()[0];
    }
    public static int singleNumber(int[] nums) {
        int res = 0;

        for(int num : nums)
            res = res ^ num;

        return res;
    }

    public static char findTheDifference(String s, String t) {
        int sum = 0;

        for(char c : t.toCharArray())
            sum += c;

        for(char c : s.toCharArray())
            sum -= c;

        return (char) sum;
    }
    public static char findTheDifference2(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray())
            map.merge(c, 1, (a,b) -> a+1);

        for(char c : t.toCharArray())
            if(map.containsKey(c)) {
                Integer val = map.get(c);
                if(val == 0)
                    return c;
                else
                    map.put(c, val-1);
            }
            else
                return c;

        return '0';
    }

    public static void main(String args[]) {
        System.out.println(hammingDistance(1, 4));

        System.out.println(findComplement(5));

        System.out.println(singleNumber(new int[]{4,1,2,1,2}));

        System.out.println(findTheDifference("abcd", "abcde"));
        System.out.println(findTheDifference("a", "aa"));
    }

}
