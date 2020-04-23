package com.java.stuff.leetcode.easy;

public class ExercisesFourteen {

    static int fiboIt(int n) {
        int a = 0;
        int b = 1;

        if (n == 0) return a;
        if (n == 1) return b;

        for (int i = 2; i < n+1; i++) {
            int tmp = b;
            b = a + b;
            a = tmp;
        }


        return b;
    }

    private static int fiboRecu(int n, int a, int b, int count) {
        if (count >= n+1)
            return b;
        else
            return fiboRecu(n, b, a + b, count + 1);
    }

    static int fiboRec(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fiboRecu(n, 0, 1, 2);
    }

    public static void main(String[] args) {
        System.out.println(fiboIt(4));
        System.out.println(fiboRec(4));


    }

}
