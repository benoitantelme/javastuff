package com.java.stuff.codility.bits;

public class BinaryGap {


    public static void main(String[] args) {
        BinaryGap gap = new BinaryGap();

        System.out.println(gap.solution(5));
        System.out.println(gap.solution(9));
        System.out.println(gap.solution(529));
        System.out.println(gap.solution(1041));
        System.out.println(gap.solution(0));


    }

    public int solution(int n) {
        while ((n & 1) == 1)
            n = n >>> 1;

        int max = 0;
        int tmp = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                tmp = 0;
            } else {
                tmp++;
                if (tmp > max)
                    max = tmp;
            }
            n = n >>> 1;
        }

        return max;
    }


}
