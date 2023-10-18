package com.java.stuff.leetcode2023.trees;

public class CountGoodNodesInBinaryTree {
    int correct;

    void rec(TreeNode root, int max) {
        if (root == null)
            return;

        if (root.val >= max) {
            correct += 1;
            max = root.val;
        }

        rec(root.left, max);
        rec(root.right, max);
    }

    public int goodNodes(TreeNode root) {
        correct = 0;
        rec(root, Integer.MIN_VALUE);
        return correct;
    }

    public static void main(String[] args) {
        CountGoodNodesInBinaryTree cgn = new CountGoodNodesInBinaryTree();

        TreeNode one = new TreeNode(1,
                new TreeNode(3, null, null), null);
        TreeNode four = new TreeNode(4,
                new TreeNode(1, null, null),
                new TreeNode(5, null, null));
        TreeNode root = new TreeNode(3, one, four);


        System.out.println(cgn.goodNodes(root));

        root = new TreeNode(3,
                new TreeNode(3,
                        new TreeNode(4, null, null),
                        new TreeNode(2, null, null)),
                null);
        System.out.println(cgn.goodNodes(root));

        one = new TreeNode(1, null, null);
        System.out.println(cgn.goodNodes(one));

//        Example 1:
//        Input: root = [3,1,4,3,null,1,5]
//        Output: 4
//        Explanation: Nodes in blue are good.
//                Root Node (3) is always a good node.
//                Node 4 -> (3,4) is the maximum value in the path starting from the root.
//                Node 5 -> (3,4,5) is the maximum value in the path
//        Node 3 -> (3,1,3) is the maximum value in the path.
//
//                Example 2:
//        Input: root = [3,3,null,4,2]
//        Output: 3
//        Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
//
//        Example 3:
//        Input: root = [1]
//        Output: 1
//        Explanation: Root is considered as good.
    }

}

