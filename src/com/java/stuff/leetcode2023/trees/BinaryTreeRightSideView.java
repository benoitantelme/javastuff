package com.java.stuff.leetcode2023.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {
    List<Integer> result;

    void rec(List<TreeNode> children) {
        if (children.isEmpty())
            return;
        int value = -101;
        List<TreeNode> newChildren = new ArrayList<>();
        for (TreeNode child : children) {
            if (child.left != null)
                newChildren.add(child.left);
            if (child.right != null)
                newChildren.add(child.right);

            value = child.val;
        }

        if (value != -101)
            result.add(value);

        rec(newChildren);
    }

    public List<Integer> rightSideView(TreeNode root) {
        result = new ArrayList<>();
        if (root == null)
            return result;

        rec(List.of(root));
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView rsv = new BinaryTreeRightSideView();

        TreeNode four = new TreeNode(4, null, null);
        TreeNode three = new TreeNode(3, null, four);
        TreeNode five = new TreeNode(5, null, null);
        TreeNode two = new TreeNode(2, null, five);
        TreeNode one = new TreeNode(1, two, three);
        System.out.println(rsv.rightSideView(one));

        three = new TreeNode(3, null, null);
        one = new TreeNode(1, null, three);
        System.out.println(rsv.rightSideView(one));

        System.out.println(rsv.rightSideView(null));

//        Example 1:
//        Input: root = [1,2,3,null,5,null,4]
//        Output: [1,3,4]
//
//        Example 2:
//        Input: root = [1,null,3]
//        Output: [1,3]
//
//        Example 3:
//        Input: root = []
//        Output: []
    }

}
