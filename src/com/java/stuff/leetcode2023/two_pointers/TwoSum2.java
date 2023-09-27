package com.java.stuff.leetcode2023.two_pointers;

import java.util.Arrays;

public class TwoSum2 {

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int length = numbers.length;
        int j = length - 1;

        int n, m;

        while (i < j) {
            n = numbers[i];
            m = numbers[j];

            if (n + m == target)
                break;

            if (n + m < target)
                i++;

            if (n + m > target)
                j--;
        }

        return new int[]{i + 1, j + 1};
    }


    public static void main(String[] args) {
        TwoSum2 ts = new TwoSum2();

        System.out.println(Arrays.toString(ts.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{-1, 0}, -1)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{-1, 0, 1}, 0)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{2, 3, 4, 5}, 6)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{0, 0, 3, 4}, 0)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{5, 25, 75}, 100)));
        System.out.println(Arrays.toString(ts.twoSum(new int[]{-1000, -1, 0, 1}, 1)));

    }


    int[] returnResult(int i, int j) {
        i++;
        j++;
        if (i > j)
            return new int[]{j, i};
        else
            return new int[]{i, j};
    }

    public int[] bigTwoSum(int[] numbers, int target) {
        int i = 0;
        int length = numbers.length;
        int j = length - 1;

        if (target == 0) {
            while (true) {
                if (Math.abs(numbers[i]) == Math.abs(numbers[j]))
                    return returnResult(i, j);

                if (Math.abs(numbers[i]) > Math.abs(numbers[j]))
                    i++;
                else
                    j--;

            }
        } else if (target >= 0) {
            while (true) {
                while (numbers[j] >= target && j > 0)
                    j--;

                int diff = target - numbers[j];
                while (diff - numbers[i] > 0 && i < length - 1)
                    i++;

                if (diff - numbers[i] == 0)
                    return returnResult(i, j);

                i = 0;
                j -= 1;
            }
        } else {
            while (true) {
                while (numbers[i] <= target && i < length - 1)
                    i++;

                int diff = target - numbers[i];
                while (diff - numbers[j] > 0 && j > 0)
                    i++;

                if (diff - numbers[j] == 0)
                    return returnResult(i, j);

                i = 0;
                j -= 1;
            }
        }
    }

}
