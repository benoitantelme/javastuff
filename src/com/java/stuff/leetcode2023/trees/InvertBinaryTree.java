package com.java.stuff.leetcode2023.trees;

import java.util.Stack;

public class InvertBinaryTree {

    void invert(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (stack.size() > 0) {
            TreeNode tmp = stack.pop();
            invert(tmp);
            if (tmp.left != null)
                stack.push(tmp.left);
            if (tmp.right != null)
                stack.push(tmp.right);
        }

        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree ibt = new InvertBinaryTree();

        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode two = new TreeNode(2, one, three);

        TreeNode six = new TreeNode(6, null, null);
        TreeNode nine = new TreeNode(9, null, null);
        TreeNode seven = new TreeNode(7, six, nine);

        TreeNode four = new TreeNode(4, two, seven);

        System.out.println(ibt.invertTree(four));

        one = new TreeNode(1, null, null);
        three = new TreeNode(3, null, null);
        two = new TreeNode(2, one, three);
        System.out.println(ibt.invertTree(two));

        System.out.println(ibt.invertTree(null));

        one = new TreeNode(1, null, null);
        System.out.println(ibt.invertTree(one));


//        Example 1:
//        Input: root = [4,2,7,1,3,6,9]
//        Output: [4,7,2,9,6,3,1]
//
//        Example 2:
//        Input: root = [2,1,3]
//        Output: [2,3,1]
//
//        Example 3:
//        Input: root = []
//        Output: []
//
//        Example 4:
//        Input: root = [1]
//        Output: [1]
    }

    void recInvert(TreeNode tmp) {
        if (tmp != null) {
            invert(tmp);
            if (tmp.left != null)
                recInvert(tmp.left);
            if (tmp.right != null)
                recInvert(tmp.right);
        }
    }

    public TreeNode invertRecTree(TreeNode root) {
        if (root == null)
            return root;

        recInvert(root);
        return root;
    }

}
