package com.java.stuff.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ExercisesEleven {


    public static int rangeSumBST(TreeNode root, int L, int R) {
        int sum = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (L <= node.val && node.val <= R)
                sum += node.val;

            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }

        return sum;
    }

    public static void main(String args[]) {
        TreeNode tree = new TreeNode(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(18);
        System.out.println(rangeSumBST(tree, 7, 15));

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        void add(int value) {
            addRecursive(this, value);
        }
        private TreeNode addRecursive(TreeNode current, int value) {
            if (current == null) {
                return new TreeNode(value);
            }

            if (value < current.val) {
                current.left = addRecursive(current.left, value);
            } else if (value > current.val) {
                current.right = addRecursive(current.right, value);
            } else {
                // value already exists
                return current;
            }

            return current;
        }

    }


}
