package com.java.stuff.leetcode.easy;

import java.util.Arrays;

public class ExercisesOne {

    public static String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();

        for (char c : address.toCharArray())
            if (c == '.')
                sb.append("[.]");
            else
                sb.append(c);


        return sb.toString();
    }

    public static int[] decompressRLElist(int[] nums) {
        int length = 0;
        for (int i = 0; i < nums.length; i += 2)
            length += nums[i];

        int[] res = new int[length];

        int n = 0;
        for (int i = 0; i <= nums.length - 2; i += 2)
            for (int j = 0; j < nums[i]; j++)
                res[n++] = nums[i + 1];

        return res;
    }

    public static int[] sumZero(int n) {
        int[] result = new int[n];
        int i = 0;
        int k = 1;
        if (n % 2 != 0)
            result[i++] = 0;

        while (i < n - 1) {
            result[i++] = -k;
            result[i++] = k++;
        }

        return result;
    }

    public static int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        int i = 0;
        int j = A.length - 1;
        int k = A.length - 1;

        while(i <= j){
            int isquare = A[i]*A[i];
            int jsquare = A[j]*A[j];
            if(isquare > jsquare) {
                result[k--] = isquare;
                i++;
            }
            else {
                result[k--] = jsquare;
                j--;
            }
        }

        return result;
    }

    public static int fib(int N) {
        int res = 0;
        int a = 0;
        int b = 1;

        if(N == 0)
            return a;
        if(N == 1)
            return b;

        for(int i = 1; i < N; i++){
            res = a + b;
            a = b;
            b = res;
        }

        return res;
    }


    public static void main(String args[]) {
        System.out.println(defangIPaddr("1.1.1.1"));
        System.out.println(Arrays.toString(decompressRLElist(new int[]{1,2,3,4})));
        System.out.println(Arrays.toString(sumZero(1)));
        System.out.println(Arrays.toString(sortedSquares(new int[]{-7,-3,2,3,11})));
        System.out.println(fib(3));

    }


}
