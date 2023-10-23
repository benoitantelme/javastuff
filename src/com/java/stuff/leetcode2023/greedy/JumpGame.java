package com.java.stuff.leetcode2023.greedy;

public class JumpGame {

    public boolean canJump(int[] nums) {
        int target = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= target) {
                target = i;
            }
        }
        return target == 0;
    }


    public static void main(String[] args) {
        JumpGame jg = new JumpGame();

        System.out.println(jg.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jg.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(jg.canJump(new int[]{0}));
        System.out.println(jg.canJump(new int[]{2, 0, 0}));
        System.out.println(jg.canJump(new int[]{3, 0, 8, 2, 0, 0, 1}));
        System.out.println(jg.canJump(new int[]{1, 1, 2, 2, 0, 1, 1}));
        System.out.println(jg.canJump(new int[]{4, 2, 0, 0, 1, 1, 4, 4, 4, 0, 4, 0}));


//        Example 1:
//        Input: nums = [2,3,1,1,4]
//        Output: true
//        Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//
//                Example 2:
//        Input: nums = [3,2,1,0,4]
//        Output: false
//        Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
    }

    public boolean slowComplicatedCanJump(int[] nums) {
        if (nums.length == 1)
            return true;

        int i = 0;
        while (true) {
            int max = -1;
            int index = -1;
            for (int j = nums[i]; j > 0; j--) {
                if (i + j >= nums.length - 1)
                    return true;
                else {
                    int jmp = nums[i + j];
                    if (jmp >= nums.length - 1)
                        return true;
                    else if (j + jmp > max) {
                        max = j + jmp;
                        index = j;
                    }
                }
            }

            if (max < 1)
                return false;

            i = i + index;
        }
    }

    boolean tryJumps(int pos, int[] nums) {
        if (pos == nums.length - 1)
            return true;

        if (pos >= nums.length || nums[pos] == 0)
            return false;

        boolean trial;
        for (int i = nums[pos]; i > 0; i--) {
            trial = tryJumps(pos + i, nums);
            if (trial)
                return true;
        }

        return false;
    }

    public boolean tooSlowCanJump(int[] nums) {
        return tryJumps(0, nums);
    }


}
