package com.java.stuff.leetcode2023.greedy;

public class JumpGameII {

    public int jump(int[] nums) {
        if (nums.length < 2)
            return 0;

        int left = 0;
        int right = 0;
        int res = 0;

        while(right < nums.length-1){
            int max = right;
            while(left <= max){
                right = Math.max(right, left + nums[left]);
                left += 1;
            }
            res += 1;
        }

        return res;
    }


    public static void main(String[] args) {
        JumpGameII jg = new JumpGameII();

        System.out.println(jg.jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jg.jump(new int[]{2, 3, 0, 1, 4}));
        System.out.println(jg.jump(new int[]{0}));
        System.out.println(jg.jump(new int[]{1, 2}));
        System.out.println(jg.jump(new int[]{1, 2, 3}));
        System.out.println(jg.jump(new int[]{2, 1}));
        System.out.println(jg.jump(new int[]{2, 3, 1}));
        System.out.println(jg.jump(new int[]{1, 1, 1, 1}));
        System.out.println(jg.jump(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0}));


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

    public int dirtyJump(int[] nums) {
        int result = 0;

        if (nums.length == 1)
            return result;

        int pos = 0;
        while (pos < nums.length - 1) {
            int max = -1;
            int step = -1;
            for (int i = pos + 1; i <= pos + nums[pos]; i++) {
                if (i >= nums.length - 1) {
                    step = i - pos;
                    break;
                } else if (max <= i + nums[i]) {
                    max = i + nums[i];
                    step = i - pos;
                }
            }

            result++;
            pos = pos + step;
        }

        return result;
    }


}
