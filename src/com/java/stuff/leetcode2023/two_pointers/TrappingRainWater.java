package com.java.stuff.leetcode2023.two_pointers;

public class TrappingRainWater {

    public int trap(int[] height) {
        int result = 0;

        int i = 0;
        int j = height.length - 1;
        int maxLeft = height[i];
        int maxRight = height[j];

        while (i < j) {
            int depth;
            if (maxLeft > maxRight) {
                j--;
                maxRight = Math.max(maxRight, height[j]);
                depth = maxRight - height[j];
            } else {
                i++;
                maxLeft = Math.max(maxLeft, height[i]);
                depth = maxLeft - height[i];
            }

            if (depth > 0)
                result += depth;
        }

        return result;
    }

    public static void main(String[] args) {
        TrappingRainWater trw = new TrappingRainWater();


        System.out.println(trw.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        // 6
        System.out.println(trw.trap(new int[]{4, 2, 0, 3, 2, 5}));
        // 9
    }

    public int OnStructTrap(int[] height) {
        int result = 0;

        int length = height.length;
        int[] maxLeft = new int[length];
        maxLeft[0] = height[0];
        int[] maxRight = new int[length];
        maxRight[length - 1] = height[length - 1];

        for (int i = 1; i <= length - 1; i++)
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);

        for (int i = length - 2; i >= 0; i--)
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);

        for (int i = 0; i < length; i++) {
            int depth = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (depth > 0)
                result += depth;
        }

        return result;
    }

}
