package com.java.stuff.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode result = null;
        ListNode current = result;

        // total size
        int size = 0;
        for (ListNode l : lists) {
            while (l != null) {
                size++;
                l = l.next;
            }
        }

        int n = 0;
        while (n < size) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    index = i;
                }
            }

            if (n == 0) {
                result = new ListNode(lists[index].val);
                current = result;
            } else {
                current.next = new ListNode(lists[index].val);
                current = current.next;
            }

            lists[index] = lists[index].next;
            n++;
        }

        return result;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode current : lists)
            while (current != null) {
                queue.add(current);
                current = current.next;
            }

        if(queue.isEmpty())
            return null;

        ListNode result = new ListNode(queue.poll().val);
        ListNode current = result;

        while (!queue.isEmpty()) {
            current.next = new ListNode(queue.poll().val);
            current = current.next;
        }

        return result;
    }


    public static void main(String[] args) {
        ListNode[] in = new ListNode[3];
        ListNode one = new ListNode(1);
        one.next = new ListNode(4);
        one.next.next = new ListNode(5);
        in[0] = one;
        ListNode two = new ListNode(1);
        two.next = new ListNode(3);
        two.next.next = new ListNode(4);
        in[1] = two;
        ListNode three = new ListNode(2);
        three.next = new ListNode(6);
        in[2] = three;

        //  1->4->5,
        //  1->3->4,
        //  2->6

        MergeKSortedLists m = new MergeKSortedLists();
        System.out.println(m.mergeKLists(in));

        in = new ListNode[2];
        one = new ListNode(-2);
        one.next = new ListNode(-1);
        one.next.next = new ListNode(-1);
        in[0] = one;
//        two = new ListNode(1);
//        two.next = new ListNode(3);
//        two.next.next = new ListNode(4);
//        [[-2,-1,-1,-1],[]]
        System.out.println(m.mergeKLists(in));

        System.out.println(m.mergeKLists(new ListNode[0]));
    }
}
