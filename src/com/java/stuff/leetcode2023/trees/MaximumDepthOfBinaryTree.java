package com.java.stuff.leetcode2023.trees;

public class MaximumDepthOfBinaryTree {

    int getDepth(TreeNode node, int depth) {
        if (node != null) {
            depth += 1;
            int tmpDepth = - 1;
            if(node.left != null)
                tmpDepth = Math.max(depth, getDepth(node.left, depth));
            if(node.right != null)
                tmpDepth = Math.max(tmpDepth, getDepth(node.right, depth));

            depth = Math.max(depth, tmpDepth);
        }

        return depth;
    }

    public int maxDepth(TreeNode root) {
        return getDepth(root, 0);
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree mdbt = new MaximumDepthOfBinaryTree();

        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode two = new TreeNode(2, one, three);

        TreeNode six = new TreeNode(6, null, null);
        TreeNode nine = new TreeNode(9, null, null);
        TreeNode seven = new TreeNode(7, six, nine);

        TreeNode four = new TreeNode(4, two, seven);

        System.out.println(mdbt.maxDepth(four));

        one = new TreeNode(1, null, null);
        three = new TreeNode(3, one, null);

        six = new TreeNode(6, null, null);
        nine = new TreeNode(9, null, null);
        seven = new TreeNode(7, six, nine);

        four = new TreeNode(4, two, seven);

        System.out.println(mdbt.maxDepth(four));

        one = new TreeNode(1, null, null);
        three = new TreeNode(3, null, null);
        two = new TreeNode(2, one, three);
        System.out.println(mdbt.maxDepth(two));

        System.out.println(mdbt.maxDepth(null));

        one = new TreeNode(1, null, null);
        System.out.println(mdbt.maxDepth(one));

//        Example 1:
//        Input:
//        root = [3, 9, 20, null, null, 15, 7]
//        Output:   3
//
//        Example 2:
//        Input:
//        root = [1, null, 2]
//        Output:   2

    }


}
