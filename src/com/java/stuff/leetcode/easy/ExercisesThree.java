package com.java.stuff.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class ExercisesThree {

    public static int getDecimalValue(ListNode head) {
        ListNode nl = head;
        int result = 0;

        while (nl != null) {
            result = result << 1 | nl.val;
            nl = nl.next;
        }

        return result;
    }

    public static ListNode middleNode(ListNode head) {
        List<ListNode> list = new ArrayList<>();

        ListNode nl = head;

        while (nl != null) {
            list.add(nl);
            nl = nl.next;
        }

        return list.get(list.size()/2);
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode newList = new ListNode(head.val);
        newList.next = null;

        while(head.next != null){
            head = head.next;
            ListNode tmpList = new ListNode(head.val);
            tmpList.next = newList;
            newList = tmpList;
        }

        return newList;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result;

        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        if(l1.val < l2.val){
            result = new ListNode(l1.val);
            l1 = l1.next;
        }else{
            result = new ListNode(l2.val);
            l2 = l2.next;
        }

        ListNode tmp = result;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                tmp.next = new ListNode(l1.val);
                tmp = tmp.next;
                l1 = l1.next;
            }else{
                tmp.next = new ListNode(l2.val);
                tmp = tmp.next;
                l2 = l2.next;
            }
        }

        while(l1 != null){
            tmp.next = new ListNode(l1.val);
            tmp = tmp.next;
            l1 = l1.next;
        }

        while(l2 != null){
            tmp.next = new ListNode(l2.val);
            tmp = tmp.next;
            l2 = l2.next;
        }

        return result;
    }

    public static void main(String args[]) {
        ListNode ln = createList(new int[]{1, 0, 1});
        System.out.println(getDecimalValue(ln));

        ln = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println(middleNode(ln).val);

        printList(ln);
        ln = reverseList(ln);
        printList(ln);

        ListNode ln1 = createList(new int[]{1, 2, 4});
        ListNode ln2 = createList(new int[]{1, 3, 4});
        printList(mergeTwoLists(ln1, ln2));
    }

    static ListNode createList(int[] values) {
        ListNode ln = new ListNode(values[0]);
        ListNode tmp = ln;
        for (int i = 1; i < values.length; i++) {
            tmp.next = new ListNode(values[i]);
            tmp = tmp.next;
        }

        return ln;
    }

    static void printList(ListNode ln) {
        System.out.println();
        while (ln != null) {
            System.out.print(ln.val + " ");
            ln = ln.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
