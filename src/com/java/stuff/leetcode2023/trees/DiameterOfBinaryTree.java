package com.java.stuff.leetcode2023.trees;

public class DiameterOfBinaryTree {
    Integer result = -1;

    int rec(TreeNode node) {
        if (node == null)
            return -1;

        int left = 1 + rec(node.left);
        int right = 1 + rec(node.right);
        result = Math.max(result, left + right);
        return Math.max(left, right);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        result = -1;
        rec(root);
        return result;
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree dot = new DiameterOfBinaryTree();

        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode two = new TreeNode(2, one, three);

        TreeNode six;
        TreeNode nine;
        TreeNode seven = new TreeNode(7, null, null);

        TreeNode four = new TreeNode(4, two, seven);

        System.out.println(dot.diameterOfBinaryTree(four));

        one = new TreeNode(1, null, null);
        three = new TreeNode(3, null, null);
        two = new TreeNode(2, one, three);

        six = new TreeNode(6, null, null);
        nine = new TreeNode(9, null, null);
        seven = new TreeNode(7, six, nine);

        four = new TreeNode(4, two, seven);

        System.out.println(dot.diameterOfBinaryTree(four));

        one = new TreeNode(1, null, null);
        three = new TreeNode(3, null, null);
        two = new TreeNode(2, one, three);
        System.out.println(dot.diameterOfBinaryTree(two));

        System.out.println(dot.diameterOfBinaryTree(null));

        one = new TreeNode(1, null, null);
        System.out.println(dot.diameterOfBinaryTree(one));
    }


}
