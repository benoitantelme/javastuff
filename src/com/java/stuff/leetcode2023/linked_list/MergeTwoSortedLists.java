package com.java.stuff.leetcode2023.linked_list;

public class MergeTwoSortedLists {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;

        }
    }

    public static void main(String[] args) {
        MergeTwoSortedLists mtsl = new MergeTwoSortedLists();
        ListNode one = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode two = new ListNode(1, new ListNode(3, new ListNode(4, null)));
        System.out.println(mtsl.mergeTwoLists(one, two));

        one = null;
        two = null;
        System.out.println(mtsl.mergeTwoLists(one, two));

        two = new ListNode(0, null);
        System.out.println(mtsl.mergeTwoLists(one, two));

//        Input: list1 = [1,2,4], list2 = [1,3,4]
//        Output: [1,1,2,3,4,4]
//
//        Example 2:
//
//        Input: list1 = [], list2 = []
//        Output: []
//
//        Example 3:
//
//        Input: list1 = [], list2 = [0]
//        Output: [0]
    }

    public ListNode nonRecMergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode result = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                result.next = list1;
                list1 = list1.next;
            } else {
                result.next = list2;
                list2 = list2.next;
            }

            result = result.next;
        }

        if (list1 == null)
            result.next = list2;
        if (list2 == null)
            result.next = list1;

        return head.next;
    }

}
