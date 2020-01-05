package com.java.stuff.datastructures.linkedlists;

public class LkdList {

    public static void main(String[] args) {
        LkdList list = new LkdList();

        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        System.out.println("The list has a loop: " + list.containsLoop());
        list.first.next.next.next.next = list.first.next;
        System.out.println("The list has a loop: " + list.containsLoop());

        list = new LkdList();
        list.add(1);
        list.add(10);
        list.add(100);
        list.add(1000);
        System.out.println("List: ");
        list.printList();

        list.reverse();
        System.out.println("\nReversed list: ");
        list.printList();
    }

    Node first;


    void add(int nbr) {
        Node new_node = new Node(nbr);
        new_node.next = first;
        first = new_node;
    }

    boolean containsLoop() {
        Node normalPointer = first;
        Node fastPointer = first;

        while (normalPointer != null && fastPointer != null) {
            normalPointer = normalPointer.next;
            fastPointer = fastPointer.next.next;

            if (normalPointer == fastPointer)
                return true;
        }

        return false;
    }

    void reverse() {
        Node previous = null, next = null;
        Node current = first;

        while (current != null) {
            next = current.next;
            current.next = previous;

            previous = current;
            current = next;
        }

        first = previous;
    }

    void printList() {
        if (containsLoop())
            return;

        Node node = first;
        while (node != null) {
            System.out.print(node.nbr + " ");
            node = node.next;
        }
    }

    static class Node {
        int nbr;
        Node next;

        Node(int nbr) {
            this.nbr = nbr;
        }
    }

}
