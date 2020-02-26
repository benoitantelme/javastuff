package com.java.stuff.leetcode.medium;

import java.util.*;

public class TreeMedium {

    static public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList<>();

        int depht = (int) (Math.log(label) / Math.log(2));
        int move;

        while (depht > 0) {
            int pow = (int) Math.pow(2, depht);
            move = label - pow;
            res.addFirst(label);
            int a = (int) Math.ceil(move / 2.0);
            label = pow - (int) Math.ceil(move / 2) - 1;
            depht -= 1;
        }

        res.addFirst(1);

        return res;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void recMaxLevelSum(TreeNode node, int level, Map<Integer, Integer> levelToSum){
        levelToSum.put(level, levelToSum.getOrDefault(level, 0)+node.val);

        if(node.left != null)
            recMaxLevelSum(node.left, level + 1, levelToSum);
        if(node.right != null)
            recMaxLevelSum(node.right, level + 1, levelToSum);
    }

    public static int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> levelToSum = new HashMap<>();
        recMaxLevelSum(root, 1, levelToSum);

        int max = Integer.MIN_VALUE;
        int level = -1;
        for(Map.Entry<Integer, Integer> entry : levelToSum.entrySet()){
            if(max < entry.getValue()){
                max = entry.getValue();
                level = entry.getKey();
            }
        }

        return level;
    }

    public static int recDistributeCoins(TreeNode node, int[] res) {
        int moves = 0;
        if(node.left != null)
            moves += recDistributeCoins(node.left, res);
        if(node.right != null)
            moves += recDistributeCoins(node.right, res);

        moves += node.val - 1;
        res[0] += Math.abs(moves);

        return moves;
    }
    public static int distributeCoins(TreeNode root) {
        int[] res = new int[]{0};
        recDistributeCoins(root, res);
        return res[0];
    }


    public static void main(String args[]) {
        System.out.println(pathInZigZagTree(16));
        System.out.println(pathInZigZagTree(14));
        System.out.println(pathInZigZagTree(26));

        System.out.println("Max level sums:");
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-5);
        System.out.println(maxLevelSum(root));

        root = new TreeNode(989);
        root.right = new TreeNode(10250);
        root.right.left = new TreeNode(98693);
        root.right.right = new TreeNode(-89388);
        root.right.right.left = new TreeNode(-32127);
        System.out.println(maxLevelSum(root));

        System.out.println("Moves:");
        root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        System.out.println(distributeCoins(root));
        root = new TreeNode(0);
        root.left = new TreeNode(3);
        root.right = new TreeNode(0);
        System.out.println(distributeCoins(root));
        root = new TreeNode(4);
        root.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.left.right.right = new TreeNode(0);
        System.out.println(distributeCoins(root));
        root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.right = new TreeNode(3);
        System.out.println(distributeCoins(root));
    }


}
