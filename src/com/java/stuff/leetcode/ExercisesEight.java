package com.java.stuff.leetcode;

import java.util.Arrays;

public class ExercisesEight {

    public static int peakIndexInMountainArray(int[] A) {
        int i = A.length / 2;

        if (A[i - 1] > A[i])
            while (A[i - 1] > A[i])
                i--;
        else if (A[i + 1] > A[i])
            while (A[i + 1] > A[i])
                i++;

        return i;
    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int j;
        int i = numbers.length / 2;

        //find first element less than target
        if (numbers[i] > target) {
            while (numbers[i - 1] > target)
                i--;

            j = i;
            i = 0;
        }
        else {
            while (i < numbers.length-1 && target > numbers[i+1])
                i++;

            if(i == numbers.length-1) {
                i = 0;
                j = numbers.length - 1;
            }else{
                j = i;
                i = 0;
            }
        }

        //zero in on the sum
        while(numbers[i] + numbers[j] != target){
            if(numbers[i] + numbers[j] > target)
                j--;
            else
                i++;
        }

        result[0] = ++i;
        result[1] = ++j;
        return result;
    }

    public static void main(String args[]) {
        System.out.println(peakIndexInMountainArray(new int[]{0, 2, 1, 0}));

        System.out.println(Arrays.toString(twoSum(new int[]{3,24,50,79,88,150,345}, 200)));

    }
}
