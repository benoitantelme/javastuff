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
    }

    Node first;


    private void add(int nbr)
    {
        Node new_node = new Node(nbr);
        new_node.next = first;
        first = new_node;
    }

    private boolean containsLoop(){
        Node normalPointer = first;
        Node fastPointer = first;

        while(normalPointer != null && fastPointer != null){
            normalPointer = normalPointer.next;
            fastPointer = fastPointer.next.next;

            if(normalPointer == fastPointer)
                return true;
        }

        return  false;
    }

    static class Node {
        int nbr;
        Node next;

        Node(int nbr)
        {
            this.nbr = nbr;
        }
    }

}
