package com.java.stuff.leetcode2023.trees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestorOfABinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        return root;
    }


    public static void main(String[] args) {
        LowestCommonAncestorOfABinarySearchTree lca = new LowestCommonAncestorOfABinarySearchTree();


        TreeNode five = new TreeNode(5, null, null);
        TreeNode three = new TreeNode(3, null, null);

        TreeNode four = new TreeNode(4, three, five);
        TreeNode zero = new TreeNode(0, null, null);

        TreeNode two = new TreeNode(2, zero, four);

        TreeNode seven = new TreeNode(7, null, null);
        TreeNode nine = new TreeNode(9, null, null);

        TreeNode eight = new TreeNode(8, seven, nine);

        TreeNode six = new TreeNode(6, two, eight);


        System.out.println(lca.lowestCommonAncestor(six, two, eight));

        System.out.println(lca.lowestCommonAncestor(six, two, four));


        TreeNode one = new TreeNode(1, null, null);
        two = new TreeNode(2, one, null);
        System.out.println(lca.lowestCommonAncestor(two, two, one));


//        Example 1:
//        Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//        Output: 6
//        Explanation: The LCA of nodes 2 and 8 is 6.
//
//        Example 2:
//        Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//        Output: 2
//        Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
//
//                Example 3:
//        Input: root = [2,1], p = 2, q = 1
//        Output: 2

    }

    Map<TreeNode, Integer> depths;
    Map<TreeNode, Set<TreeNode>> mainAncestors;

    void rec(TreeNode root, int depth, Set<TreeNode> ancestors) {
        if (root != null) {
            depths.put(root, depth + 1);
            Set<TreeNode> updated = new HashSet<>();
            updated.addAll(ancestors);
            updated.add(root);
            mainAncestors.put(root, updated);

            rec(root.left, depth + 1, updated);
            rec(root.right, depth + 1, updated);
        }
    }

    public TreeNode bigLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        depths = new HashMap<>();
        mainAncestors = new HashMap<>();

        depths.put(root, 1);
        Set<TreeNode> initial = Set.of(root);
        mainAncestors.put(root, initial);
        rec(root, 1, initial);

        Set<TreeNode> pAncestors = mainAncestors.get(p);
        Set<TreeNode> qAncestors = mainAncestors.get(q);
        pAncestors.retainAll(qAncestors);

        TreeNode min = null;
        for (TreeNode node : pAncestors) {
            if (min == null)
                min = node;
            else if (depths.get(node) > depths.get(min))
                min = node;
        }

        return min;
    }

}