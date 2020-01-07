package com.java.stuff.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    Node root;


    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }


    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }

        if (value == current.value) {
            return true;
        }

        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }


    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }

            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }


    public void breadthFirstSearch() {
        if (root == null)
            return;

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            System.out.print(current.value + " ");
            if (current.left != null)
                nodes.add(current.left);
            if (current.right != null)
                nodes.add(current.right);
        }
    }

    public void depthFirstSearchPostOrder(Node current) {
        if (current == null)
            return;

        if (current.left != null)
            depthFirstSearchPostOrder(current.left);
        if (current.right != null)
            depthFirstSearchPostOrder(current.right);

        System.out.print(current.value + " ");
    }

    public void depthFirstSearchPreOrder(Node current) {
        if (current == null)
            return;

        System.out.print(current.value + " ");
        if (current.left != null)
            depthFirstSearchPreOrder(current.left);
        if (current.right != null)
            depthFirstSearchPreOrder(current.right);
    }

    public void depthFirstSearchInOrder(Node current) {
        if (current == null)
            return;

        if (current.left != null)
            depthFirstSearchInOrder(current.left);
        System.out.print(current.value + " ");
        if (current.right != null)
            depthFirstSearchInOrder(current.right);
    }

    public int getMin(Node current){
        if(current == null)
            return -1;

        if(current.left == null)
            return current.value;
        else
            return getMin(current.left);
    }

    public int getMax(Node current){
        if(current == null)
            return -1;

        if(current.right == null)
            return current.value;
        else
            return getMax(current.right);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(6);
        bst.add(4);
        bst.add(8);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(9);

        System.out.println("BFS");
        bst.breadthFirstSearch();
        System.out.println("\nDFS Post");
        bst.depthFirstSearchPostOrder(bst.root);
        System.out.println("\nDFS In");
        bst.depthFirstSearchInOrder(bst.root);
        System.out.println("\nDFS Pre");
        bst.depthFirstSearchPreOrder(bst.root);

        System.out.println("\nMin");
        System.out.print(bst.getMin(bst.root));
        System.out.println("\nMax");
        System.out.print(bst.getMax(bst.root));

    }

}
