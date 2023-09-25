package com.java.stuff.leetcode2023.arrays_hashing;

import java.util.Arrays;

public class ProductofArrayExceptSelf {


    public int[] productExceptSelf(int[] nums) {
        int[] products = new int[nums.length];

        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            products[i] = left;
            left *= nums[i];
        }

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            products[i] *= right;
            right *= nums[i];
        }

        return products;
    }

    public static void main(String[] args) {
        ProductofArrayExceptSelf poa = new ProductofArrayExceptSelf();

        System.out.println(Arrays.toString(poa.productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(poa.productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
    }

}
