package com.java.stuff.leetcode2023.trees;

public class ValidateBinarySearchTree {
    boolean result;

    void rec(TreeNode node, long min, long max) {
        if (node == null || !result)
            return;

        if (node.val >= max || node.val <= min) {
            result = false;
            return;
        }

        rec(node.left, min, node.val);
        rec(node.right, node.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        result = true;
        rec(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return result;
    }


    public static void main(String[] args) {
        ValidateBinarySearchTree vbst = new ValidateBinarySearchTree();

        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode two = new TreeNode(2, one, three);
        System.out.println(vbst.isValidBST(two));


        one = new TreeNode(1, null, null);
        three = new TreeNode(3, null, null);
        TreeNode six = new TreeNode(6, null, null);
        TreeNode four = new TreeNode(4, three, six);
        TreeNode five = new TreeNode(5, one, four);
        System.out.println(vbst.isValidBST(five));

        System.out.println(vbst.isValidBST(new TreeNode(2147483647, null, null)));

//        Example 1:
//        Input: root = [2,1,3]
//        Output: true
//
//        Example 2:
//        Input: root = [5,1,4,null,null,3,6]
//        Output: false
//        Explanation: The root node's value is 5 but its right child's value is 4.
    }

}
