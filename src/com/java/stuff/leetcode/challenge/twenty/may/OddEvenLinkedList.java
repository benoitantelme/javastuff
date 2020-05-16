package com.java.stuff.leetcode.challenge.twenty.may;

public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;

        ListNode oddEnd = head;
        ListNode evenEnd = head.next;
        ListNode evenHead = evenEnd;

        while (evenEnd != null && evenEnd.next != null) {
            oddEnd.next = evenEnd.next;
            oddEnd = oddEnd.next;
            evenEnd.next = oddEnd.next;
            evenEnd = evenEnd.next;
        }

        oddEnd.next = evenHead;
        return head;
    }


    public static void main(String[] args) {
        OddEvenLinkedList oel = new OddEvenLinkedList();
        ListNode first = new ListNode(1);
        first.next = new ListNode(2);
        first.next.next = new ListNode(3);
        first.next.next.next = new ListNode(4);
        first.next.next.next.next = new ListNode(5);

        ListNode result = oel.oddEvenList(first);
        System.out.println(result);
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
