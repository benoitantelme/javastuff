package com.java.stuff.codility.arrays;

public class OddOccurrencesInArray {

    public static void main(String[] args) {
        OddOccurrencesInArray ooia = new OddOccurrencesInArray();

        if (ooia.solution(new int[]{0, 0, 1}) != 1)
            System.out.println("wrong");

        if (ooia.solution(new int[]{9, 3, 9, 3, 9, 7, 9}) != 7)
            System.out.println("wrong");

    }

    public int solution(int[] input) {
        int tmp = 0;

        for (int i = 0; i < input.length; i++)
            tmp = tmp ^ input[i];

        return tmp;
    }

}
