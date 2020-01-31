package com.java.stuff.leetcode;

import java.util.*;

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

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null)
            return t2;
        else if(t2 == null)
            return t1;

        TreeNode t3 = new TreeNode(t1.val + t2.val);
        t3.left = mergeTrees(t1.left, t2.left);
        t3.right = mergeTrees(t1.right, t2.right);

        return t3;
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        return searchBSTRecursive(root, val);
    }
    private static TreeNode searchBSTRecursive(TreeNode current, int value) {
        if (current == null) {
            return null;
        }

        if (value < current.val) {
            return searchBSTRecursive(current.left, value);
        } else if (value > current.val) {
            return searchBSTRecursive(current.right, value);
        } else {
            return current;
        }
    }

    public static int maxDepthStack(TreeNode root) {
        if(root == null)
            return 0;

        HashMap<TreeNode, Integer> map = new HashMap<>();
        int maxDepth;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        map.put(root, 1);
        maxDepth = 1;

        while(!stack.isEmpty()){
            TreeNode current = stack.pop();

            if(current.left != null){
                int newMax = map.get(current) + 1;
                if(newMax > maxDepth)
                    maxDepth = newMax;

                stack.push(current.left);
                map.put(current.left, newMax);
            }
            if(current.right != null){
                int newMax = map.get(current) + 1;
                if(newMax > maxDepth)
                    maxDepth = newMax;

                stack.push(current.right);
                map.put(current.right, newMax);
            }
        }

        return maxDepth;
    }

    private static int maxDepthRecursive(TreeNode current, int max, int previousDepth) {
        if(current == null)
            return max;

        previousDepth ++;
        if(max < previousDepth)
            max = previousDepth;

        if(current.left != null)
            max = maxDepthRecursive(current.left, max, previousDepth);

        if(current.right != null)
            max = maxDepthRecursive(current.right, max, previousDepth);

        return max;
    }
    public static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        return maxDepthRecursive(root, 0, 0);
    }

    private static void invertTreeRecursive(TreeNode current) {
        if(current == null)
            return;

        TreeNode temp = current.left;
        current.left = current.right;
        current.right = temp;

        if(current.left != null)
            invertTreeRecursive(current.left);

        if(current.right != null)
            invertTreeRecursive(current.right);
    }
    public static TreeNode invertTree(TreeNode root) {
        invertTreeRecursive(root);
        return root;
    }

    /**
     * @param root
     * @return the leaf sequence, a list containing the leaves values from left to right
     */
    private static List<Integer> getLeafSequence(TreeNode root){
        List<Integer> seq = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode current = stack.pop();

            if(current.left != null)
                stack.push(current.left);
            if(current.right != null)
                stack.push(current.right);

            if(current.left == null && current.right == null)
                seq.add(current.val);
        }

        return seq;
    }
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if(getLeafSequence(root1).equals(getLeafSequence(root2)))
            return true;

        return false;
    }

    public static void main(String args[]) {
        TreeNode tree = new TreeNode(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(18);
        System.out.println(rangeSumBST(tree, 7, 15));

        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(3);
        tree1.right = new TreeNode(2);
        tree1.left.left = new TreeNode(5);

        TreeNode tree2 = new TreeNode(2);
        tree2.left = new TreeNode(1);
        tree2.right = new TreeNode(3);
        tree2.left.right = new TreeNode(4);
        tree2.right.right = new TreeNode(7);
        TreeNode t3 = mergeTrees(tree1, tree2);

        tree = new TreeNode(4);
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(7);
        tree = searchBST(tree, 2);

        tree = new TreeNode(7);
        tree.add(3);
        tree.add(9);
        tree.add(20);
        tree.add(15);
        System.out.println(maxDepth(tree));

        tree2 = new TreeNode(4);
        tree2.left = new TreeNode(2);
        tree2.right = new TreeNode(7);
        tree2.left.left = new TreeNode(1);
        tree2.left.right = new TreeNode(3);
        tree2.right.left = new TreeNode(6);
        tree2.right.right = new TreeNode(9);
        tree2 = invertTree(tree2);
        System.out.println();

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
                return current;
            }

            return current;
        }

    }


}
