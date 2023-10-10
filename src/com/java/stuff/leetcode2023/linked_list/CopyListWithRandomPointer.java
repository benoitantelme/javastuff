package com.java.stuff.leetcode2023.linked_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Node tmp = head;
        List<Node> results = new ArrayList<>();
        Map<Node, Node> map = new HashMap<>();
        while (tmp != null) {
            Node newNode = new Node(tmp.val);
            results.add(newNode);
            map.put(tmp, newNode);
            tmp = tmp.next;
        }

        tmp = head;
        Node previousNode = new Node(0);
        for (int i = 0; i < results.size(); i++) {
            Node newNode = results.get(i);
            newNode.random = map.get(tmp.random);
            tmp = tmp.next;
            previousNode.next = newNode;
            previousNode = newNode;
        }

        return results.get(0);
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer cl = new CopyListWithRandomPointer();

        Node first = new Node(7);
        Node second = new Node(13);
        Node third = new Node(11);
        Node fourth = new Node(10);
        Node fifth = new Node(1);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        first.random = null;
        second.random = first;
        third.random = fifth;
        fourth.random = third;
        fifth.random = first;
        System.out.println((cl.copyRandomList(first)));


        first = new Node(1);
        second = new Node(2);

        first.next = second;
        second.next = null;

        first.random = second;
        second.random = second;
        System.out.println((cl.copyRandomList(first)));


        first = new Node(3);
        second = new Node(3);
        third = new Node(3);

        first.next = second;
        second.next = third;
        third.next = null;

        first.random = null;
        second.random = first;
        third.random = null;
        System.out.println((cl.copyRandomList(first)));

//        Example 1:
//        Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//        Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
//
//        Example 2:
//        Input: head = [[1,1],[2,1]]
//        Output: [[1,1],[2,1]]
//
//        Example 3:
//        Input: head = [[3,null],[3,0],[3,null]]
//        Output: [[3,null],[3,0],[3,null]]
    }

}
