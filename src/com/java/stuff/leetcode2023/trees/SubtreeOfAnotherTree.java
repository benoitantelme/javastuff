package com.java.stuff.leetcode2023.trees;

public class SubtreeOfAnotherTree {
    Boolean result;

    boolean areEquals(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null)
            return true;

        if(root == null || subRoot == null)
            return false;

        if(root.val != subRoot.val)
            return false;

        if(areEquals(root.left, subRoot.left) == false)
            return false;

        if(areEquals(root.right, subRoot.right) == false)
            return false;

        return true;
    }

    void rec(TreeNode root, TreeNode subRoot){
        if(result == true || root == null)
            return;

        if(root.val == subRoot.val)
            if(areEquals(root, subRoot))
                result = true;

        rec(root.left, subRoot);
        rec(root.right, subRoot);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        result = false;
        rec(root, subRoot);

        return result;
    }

    public static void main(String[] args) {
        SubtreeOfAnotherTree soas = new SubtreeOfAnotherTree();


        TreeNode one = new TreeNode(1, null, null);
        TreeNode two = new TreeNode(2, null, null);
        TreeNode four = new TreeNode(4, one, two);

        TreeNode five = new TreeNode(5, null, null);
        TreeNode three = new TreeNode(3, four, five);

        TreeNode twoOne = new TreeNode(1, null, null);
        TreeNode twoTwo = new TreeNode(2, null, null);
        TreeNode twoFour = new TreeNode(4, twoOne, twoTwo);

        System.out.println(soas.isSubtree(three, twoFour));


        two = new TreeNode(2, new TreeNode(0, null, null), null);
        four = new TreeNode(4, one, two);
        three = new TreeNode(3, four, five);
        System.out.println(soas.isSubtree(three, twoFour));


//        Example 1:
//        Input: root = [3,4,5,1,2], subRoot = [4,1,2]
//        Output: true
//
//        Example 2:
//        Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
//        Output: false

    }

}