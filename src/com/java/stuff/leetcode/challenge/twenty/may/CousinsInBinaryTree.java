package com.java.stuff.leetcode.challenge.twenty.may;

public class CousinsInBinaryTree {

    public boolean isCousins(TreeNode root, int x, int y) {
        int[] xPos = recCousins(root, 0, x, -1);
        if(xPos == null)
            return false;

        int[] yPos = recCousins(root, 0, y, -1);
        if(yPos == null)
            return false;
        else if(xPos[0] == yPos[0] && xPos[1] != yPos[1])
            return true;
        else
            return false;
    }

    private int[] recCousins(TreeNode current, int depth, int cousin, int parent){
        int[] result = null;

        if(current.val == cousin)
            return new int[]{depth, parent};

        if(current.left != null)
            result = recCousins(current.left, depth+1, cousin, current.val);
        if(result != null)
            return result;

        if(current.right != null)
            result = recCousins(current.right, depth+1, cousin, current.val);

        return result;
    }


    public static void main(String[] args) {
        CousinsInBinaryTree cibt = new CousinsInBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(cibt.isCousins(root, 4, 5));

    }


    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}


