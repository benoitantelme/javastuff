package com.java.stuff.leetcode2023.linked_list;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedListCycle llc = new LinkedListCycle();

        ListNode one = new ListNode(3);
        ListNode two = new ListNode(2);
        one.next = two;
        two.next = new ListNode(0, new ListNode(-4, two));
        System.out.println(llc.hasCycle(one));

        one = new ListNode(1);
        one.next = new ListNode(2, one);
        System.out.println(llc.hasCycle(one));

        one = new ListNode(1, null);
        System.out.println(llc.hasCycle(one));

//        Example 1:
//        Input: head = [3,2,0,-4], pos = 1
//        Output: true
//        Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
//
//                Example 2:
//        Input: head = [1,2], pos = 0
//        Output: true
//        Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
//
//        Example 3:
//        Input: head = [1], pos = -1
//        Output: false
//        Explanation: There is no cycle in the linked list.
    }

    public boolean memoryHasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        while (head != null) {
            if (set.contains(head))
                return true;
            else
                set.add(head);

            head = head.next;
        }

        return false;
    }

}
