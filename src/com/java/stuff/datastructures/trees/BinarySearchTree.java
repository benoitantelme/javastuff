package com.java.stuff.datastructures.trees;

import java.util.*;

public class BinarySearchTree {
    BstNode root;


    public void add(int value) {
        root = addRecursive(root, value);
    }
    private BstNode addRecursive(BstNode current, int value) {
        if (current == null) {
            return new BstNode(value);
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
    private boolean containsNodeRecursive(BstNode current, int value) {
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
    private int findSmallestValue(BstNode root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }
    private BstNode deleteRecursive(BstNode current, int value) {
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

    public void breadthFirstSearch() {
        if (root == null)
            return;

        Queue<BstNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            BstNode current = nodes.poll();
            System.out.print(current.value + " ");
            if (current.left != null)
                nodes.add(current.left);
            if (current.right != null)
                nodes.add(current.right);
        }
    }

    public void depthFirstSearchPostOrder(BstNode current) {
        if (current == null)
            return;

        if (current.left != null)
            depthFirstSearchPostOrder(current.left);
        if (current.right != null)
            depthFirstSearchPostOrder(current.right);

        System.out.print(current.value + " ");
    }

    public void depthFirstSearchPreOrder(BstNode current) {
        if (current == null)
            return;

        System.out.print(current.value + " ");
        if (current.left != null)
            depthFirstSearchPreOrder(current.left);
        if (current.right != null)
            depthFirstSearchPreOrder(current.right);
    }

    public void depthFirstSearchInOrder(BstNode current) {
        if (current == null)
            return;

        if (current.left != null)
            depthFirstSearchInOrder(current.left);
        System.out.print(current.value + " ");
        if (current.right != null)
            depthFirstSearchInOrder(current.right);
    }

    public int getMin(BstNode current) {
        if (current == null)
            return -1;

        if (current.left == null)
            return current.value;
        else
            return getMin(current.left);
    }

    public int getMax(BstNode current) {
        if (current == null)
            return -1;

        if (current.right == null)
            return current.value;
        else
            return getMax(current.right);
    }

    /**
     * @param current node, start with roots
     * @param result  a list with in order values of the tree
     */
    public static void inOrderList(BstNode current, List<Integer> result) {
        if (current == null)
            return;

        if (current.left != null)
            inOrderList(current.left, result);
        result.add(current.value);
        if (current.right != null)
            inOrderList(current.right, result);
    }
    public static int[] mergeLists(List<Integer> list1, List<Integer> list2) {
        int n1 = list1.size();
        int n2 = list2.size();
        int[] mergedArray = new int[n1 + n2];
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (list1.get(i) < list2.get(j))
                mergedArray[k++] = list1.get(i++);
            else
                mergedArray[k++] = list2.get(j++);
        }

        while (i < n1)
            mergedArray[k++] = list1.get(i++);

        while (j < n2)
            mergedArray[k++] = list2.get(j++);

        return mergedArray;
    }
    static BstNode arrayToBST(int arr[], int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BstNode node = new BstNode(arr[mid]);

        node.left = arrayToBST(arr, start, mid - 1);
        node.right = arrayToBST(arr, mid + 1, end);

        return node;
    }
    public static BinarySearchTree mergeTrees(BinarySearchTree tree1, BinarySearchTree tree2) {
        List<Integer> list1 = new ArrayList<>();
        inOrderList(tree1.root, list1);
        System.out.println("First list: " + Arrays.asList(list1));

        List<Integer> list2 = new ArrayList<>();
        inOrderList(tree2.root, list2);
        System.out.println("Second list: " + Arrays.asList(list2));

        int[] mergedArray = mergeLists(list1, list2);
        System.out.println("Merged list: " + Arrays.toString(mergedArray));

        BinarySearchTree result = new BinarySearchTree();
        result.root = arrayToBST(mergedArray, 0, mergedArray.length-1);
        return result;
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

        BinarySearchTree tree1 = new BinarySearchTree();
        tree1.add(100);
        tree1.add(50);
        tree1.add(300);
        tree1.add(20);

        BinarySearchTree tree2 = new BinarySearchTree();
        tree2.add(70);
        tree2.add(80);
        tree2.add(40);
        tree2.add(120);

        BinarySearchTree tree3 = mergeTrees(tree1, tree2);
        tree3.depthFirstSearchInOrder(tree3.root);
    }

}
