package com.java.stuff.leetcode2023.linked_list;

import java.util.Stack;

public class ReorderList {

    public void reorderList(ListNode head) {
        // get middle with two pointers
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;

        // reverse second part
        ListNode previous = null;
        slow.next = previous;

        ListNode tmp = null;
        while (second != null) {
            tmp = second.next;
            second.next = previous;
            previous = second;
            second = tmp;
        }

        // merge the parts
        second = previous;
        ListNode first = head;

        while (second != null) {
            ListNode tmpFirst = first.next;
            ListNode tmpSecond = second.next;
            first.next = second;
            second.next = tmpFirst;
            first = tmpFirst;
            second = tmpSecond;
        }

    }

    public static void main(String[] args) {
        ReorderList rl = new ReorderList();

        ListNode l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        rl.reorderList(l);
        System.out.println(l);

        l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        rl.reorderList(l);
        System.out.println(l);


//        Example 1:
//
//        Input: head = [1,2,3,4]
//        Output: [1,4,2,3]
//
//        Example 2:
//
//        Input: head = [1,2,3,4,5]
//        Output: [1,5,2,4,3]
    }

    public void slowAndStackReorderList(ListNode head) {
        if (head.next == null)
            return;

        Stack<ListNode> stack = new Stack<>();
        ListNode first = head;

        while (first != null) {
            stack.push(first);
            first = first.next;
        }

        ListNode next = stack.pop();
        first = head;
        boolean fromList = true;
        while (next != first) {
            ListNode tmp = first.next;
            first.next = next;
            first = next;

            if (fromList)
                stack.push(tmp);

            next = stack.pop();
            fromList = !fromList;
        }

        next.next = null;
    }

}
