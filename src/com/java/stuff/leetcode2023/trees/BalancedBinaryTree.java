package com.java.stuff.leetcode2023.trees;

public class BalancedBinaryTree {
    Boolean result;

    int rec(TreeNode root){
        if(root == null)
            return -1;

        int leftLength = 1 + rec(root.left);
        int rightLength = 1 + rec(root.right);

        if(Math.abs(leftLength - rightLength) >= 2)
            result = false;

        return Math.max(leftLength, rightLength);
    }

    public boolean isBalanced(TreeNode root) {
        result = true;
        rec(root);

        return result;
    }

    public static void main(String[] args) {
        BalancedBinaryTree bbt = new BalancedBinaryTree();

        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode two = new TreeNode(2, one, three);

        TreeNode six;
        TreeNode nine;
        TreeNode seven;

        TreeNode four = new TreeNode(4, two, null);

        System.out.println(bbt.isBalanced(four));

        one = new TreeNode(1, null, null);
        three = new TreeNode(3, null, null);
        two = new TreeNode(2, one, three);

        six = new TreeNode(6, null, null);
        nine = new TreeNode(9, null, null);
        seven = new TreeNode(7, six, nine);

        four = new TreeNode(4, two, seven);

        System.out.println(bbt.isBalanced(four));

        one = new TreeNode(1, null, null);
        three = new TreeNode(3, null, null);
        two = new TreeNode(2, one, three);
        System.out.println(bbt.isBalanced(two));

        System.out.println(bbt.isBalanced(null));

        one = new TreeNode(1, null, null);
        System.out.println(bbt.isBalanced(one));
    }

}
