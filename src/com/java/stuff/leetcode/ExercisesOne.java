package com.java.stuff.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public static void main(String args[]) { ;
        System.out.println(defangIPaddr("1.1.1.1"));
        System.out.println(Arrays.toString(decompressRLElist(new int[]{1,2,3,4})));
        System.out.println(Arrays.toString(sumZero(1)));


    }


}
