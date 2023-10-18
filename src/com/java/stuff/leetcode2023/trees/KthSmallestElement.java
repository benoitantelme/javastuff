package com.java.stuff.leetcode2023.trees;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElement {
    List<Integer> values;

    void rec(TreeNode node) {
        if (node == null)
            return;

        rec(node.left);
        values.add(node.val);
        rec(node.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        values = new ArrayList<>();
        rec(root);
        return values.get(k - 1);
    }

    public static void main(String[] args) {
        KthSmallestElement kth = new KthSmallestElement();

        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode two = new TreeNode(2, one, three);

        TreeNode six = new TreeNode(6, null, null);
        TreeNode nine = new TreeNode(9, null, null);
        TreeNode seven = new TreeNode(7, six, nine);

        TreeNode four = new TreeNode(4, two, seven);

        System.out.println(kth.kthSmallest(four, 1));

    }

}