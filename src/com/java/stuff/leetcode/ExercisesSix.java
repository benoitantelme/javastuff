package com.java.stuff.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.java.stuff.leetcode.ExercisesThree.createList;

public class ExercisesSix {

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> hashset = new HashSet<>();
        for (int n : nums1)
            hashset.add(n);

        HashSet<Integer> result = new HashSet<>();

        for (int i : nums2)
            if (hashset.contains(i))
                result.add(i);

        return result.stream().mapToInt(Number::intValue).toArray();
    }

    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;

        while (i < nums.length) {
            int num = nums[i];
            if (num != 0) {
                if (j < i) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                i++;
                j++;
            } else
                i++;
        }
    }

    private static String backspaceCompareUtil(String S) {
        int i = 0, j = 0;
        char[] result = new char[S.length()];
        char[] Schar = S.toCharArray();

        for (char c : Schar) {
            if (c == '#') {
                i++;

                if (j > 0)
                    j--;
            } else {
                result[j++] = Schar[i++];
            }
        }

        return new String(Arrays.copyOfRange(result, 0, j));
    }

    public static boolean backspaceCompare(String S, String T) {
        String s1 = backspaceCompareUtil(S);
        String s2 = backspaceCompareUtil(T);

        return s1.equals(s2);
    }


    public static boolean hasCycle(ExercisesThree.ListNode head) {
        ExercisesThree.ListNode slow = head;
        ExercisesThree.ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(fast.val == slow.val)
                return true;
        }

        return false;
    }


    public static void main(String args[]) {
        System.out.println(Arrays.toString(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));

        int[] a = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(a));
        moveZeroes(a);
        System.out.println(Arrays.toString(a));

        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));

        ExercisesThree.ListNode ln1 = createList(new int[]{3, 2, 0, 4});
        ln1.next.next.next = ln1.next;
        System.out.println(hasCycle(ln1));
        ln1 = createList(new int[]{3, 2, 0, 4});
        System.out.println(hasCycle(ln1));
        ln1 = createList(new int[]{1});
        System.out.println(hasCycle(ln1));
    }

}
