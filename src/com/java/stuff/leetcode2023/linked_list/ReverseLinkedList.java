package com.java.stuff.leetcode2023.linked_list;

public class ReverseLinkedList {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode reverseList(ListNode head) {
        ListNode old = null;
        if(head == null)
            return head;

        return recursion(head, old);
    }

    public ListNode recursion(ListNode head, ListNode old) {
        ListNode tmp = head.next;
        head.next = old;
        old = head;
        head = tmp;
        if(head != null)
            return recursion(head, old);

        return old;
    }

    public ListNode nonRecuReverseList(ListNode head) {
        ListNode old = null;

        while(head != null){
            ListNode tmp = head.next;
            head.next = old;
            old = head;
            head = tmp;
        }

        return old;
    }

    public static void main(String[] args) {
        ReverseLinkedList rll = new ReverseLinkedList();
        ListNode one = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));

        System.out.println(rll.reverseList(one));
    }


}
