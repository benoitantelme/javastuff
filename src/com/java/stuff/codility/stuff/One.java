package com.java.stuff.codility.stuff;

import java.util.HashSet;
import java.util.Set;

public class One {

    public static void main(String[] args) {
        One one = new One();
        one.solution(new int[]{1, 3, 6, 4, 1, 2});

    }

    public int solution(int[] A) {
        // write your code in Java SE 11

        Set<Integer> set = new HashSet<>();
        for(int a : A)
            if(a > 0)
                set.add(a);

        for(int i = 1; i < A.length; i++)
            if(!set.contains(i))
                return i;

        return A.length+1;
    }

}
