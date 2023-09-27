package com.java.stuff.leetcode2023.two_pointers;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;

        int max = 0;
        while (i < j) {
            int depth = Math.min(height[i], height[j]) * (j - i);
            if (depth > max)
                max = depth;

            if (height[j] < height[i])
                j--;
            else
                i++;

        }

        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater cwmw = new ContainerWithMostWater();
        System.out.println(cwmw.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(cwmw.maxArea(new int[]{1, 1}));
        System.out.println(cwmw.maxArea(new int[]{1, 2, 4, 3}));

    }

}
