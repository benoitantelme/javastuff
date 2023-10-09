package com.java.stuff.leetcode2023.linked_list;

import java.util.Stack;

public class RemoveNthNodeFromEndofList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // set dummy to -1
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = head;
        for (int i = 0; i < n; i++)
            second = second.next;

        while (second != null) {
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;
        return dummy.next;
    }


    public static void main(String[] args) {
        RemoveNthNodeFromEndofList rem = new RemoveNthNodeFromEndofList();

        ListNode l = rem.removeNthFromEnd(new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5, null))))), 2);
        System.out.println(l);

        l = rem.removeNthFromEnd(new ListNode(1, null), 1);
        System.out.println(l);

        l = rem.removeNthFromEnd(new ListNode(1,
                new ListNode(2, null)), 1);
        System.out.println(l);

        l = rem.removeNthFromEnd(new ListNode(1,
                new ListNode(2, null)), 2);
        System.out.println(l);


//        Example 1:
//
//        Input: head = [1,2,3,4,5], n = 2
//        Output: [1,2,3,5]
//
//        Example 2:
//
//        Input: head = [1], n = 1
//        Output: []
//
//        Example 3:
//
//        Input: head = [1,2], n = 1
//        Output: [1]
    }

    public ListNode slowRemoveNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode first = head;

        while (first != null) {
            stack.push(first);
            first = first.next;
        }

        if (n == 1 && stack.size() == n)
            return null;

        ListNode nLast = null;
        for (int i = 0; i < n; i++) {
            nLast = stack.pop();
        }

        if (nLast != null && stack.isEmpty()) {
            return nLast.next;
        } else {
            ListNode previous = stack.pop();
            previous.next = nLast.next;
            return head;
        }
    }

}
