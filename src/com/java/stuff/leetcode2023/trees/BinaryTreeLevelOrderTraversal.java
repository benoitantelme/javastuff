package com.java.stuff.leetcode2023.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreeLevelOrderTraversal {
    List<List<Integer>> result = new ArrayList<>();

    void rec(List<TreeNode> children){
        if(children.isEmpty())
            return;

        result.add(children.stream().map(node -> node.val).collect(Collectors.toList()));
        List<TreeNode> newChildren = new ArrayList<>();
        for(TreeNode node : children){
            if(node.left != null)
                newChildren.add(node.left);
            if(node.right != null)
                newChildren.add(node.right);
        }

        rec(newChildren);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        result = new ArrayList<>();
        if(root != null)
            rec(List.of(root));

        return result;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal lo = new BinaryTreeLevelOrderTraversal();


        TreeNode seven = new TreeNode(7, null, null);
        TreeNode fifteen = new TreeNode(15, null, null);
        TreeNode twenty = new TreeNode(20, fifteen, seven);
        TreeNode nine = new TreeNode(9, null, null);
        TreeNode three = new TreeNode(3, nine, twenty);

        System.out.println(lo.levelOrder(three));
        System.out.println(lo.levelOrder(new TreeNode(1, null, null)));
        System.out.println(lo.levelOrder(null));

//        Example 1:
//        Input: root = [3,9,20,null,null,15,7]
//        Output: [[3],[9,20],[15,7]]
//
//        Example 2:
//        Input: root = [1]
//        Output: [[1]]
//
//        Example 3:
//        Input: root = []
//        Output: []
    }

}
