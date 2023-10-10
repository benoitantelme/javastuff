package com.java.stuff.leetcode2023.linked_list;

import java.math.BigInteger;
import java.util.Stack;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode first, ListNode second) {
        int carry = 0;
        int result = 0;

        ListNode dummy = new ListNode();
        ListNode temp = dummy;
        while (first != null || second != null || carry > 0) {
            int sum = carry +
                    ((first != null) ? first.val : 0) +
                    ((second != null) ? second.val : 0);
            result = sum % 10;
            carry = sum / 10;

            temp.next = new ListNode(result);
            temp = temp.next;

            if (first != null)
                first = first.next;

            if (second != null)
                second = second.next;
        }

        return dummy.next;
    }


    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();

        ListNode one = new ListNode(2, new ListNode(4, new ListNode(3, null)));
        ListNode two = new ListNode(5, new ListNode(6, new ListNode(4, null)));
        System.out.println(atn.addTwoNumbers(one, two));

        one = new ListNode(0, null);
        two = new ListNode(0, null);
        System.out.println(atn.addTwoNumbers(one, two));

        one = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9,
                new ListNode(9, new ListNode(9, null)))))));
        two = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, null))));
        System.out.println(atn.addTwoNumbers(one, two));


        one = new ListNode(9, null);
        two = new ListNode(1, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9,
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9,
                        new ListNode(9, new ListNode(9, new ListNode(9,
                                new ListNode(9, new ListNode(9, null))))))))))))));
        System.out.println(atn.addTwoNumbers(one, two));


//        Example 1:
//
//        Input: l1 = [2,4,3], l2 = [5,6,4]
//        Output: [7,0,8]
//        Explanation: 342 + 465 = 807.
//
//        Example 2:
//
//        Input: l1 = [0], l2 = [0]
//        Output: [0]
//
//        Example 3:
//
//        Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//        Output: [8,9,9,9,0,0,0,1]
    }


    public BigInteger getInteger(ListNode l, Stack<ListNode> stack) {
        StringBuilder tmp = new StringBuilder();
        while (l != null) {
            stack.push(l);
            l = l.next;
        }

        while (!stack.isEmpty())
            tmp.append(stack.pop().val);

        return new BigInteger(tmp.toString());
    }

    public ListNode SlowAfAddTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger first = getInteger(l1, new Stack<>());
        BigInteger second = getInteger(l2, new Stack<>());

        String total = String.valueOf(first.add(second));

        Stack<Character> stack = new Stack<>();
        for (char c : total.toCharArray())
            stack.add(c);

        ListNode head = new ListNode(stack.pop() - '0');
        ListNode tmp = head;
        while (!stack.isEmpty()) {
            tmp.next = new ListNode(stack.pop() - '0');
            tmp = tmp.next;
        }

        return head;
    }


}
