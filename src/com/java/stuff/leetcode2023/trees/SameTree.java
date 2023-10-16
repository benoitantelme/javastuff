package com.java.stuff.leetcode2023.trees;

public class SameTree {
    Boolean result;

    void rec(TreeNode l, TreeNode r){
        if( l == null && r == null)
            return;

        if(l == null || r == null) {
            result = false;
            return;
        }
        if(l.val != r.val)
            result = false;

        rec(l.left, r.left);
        rec(l.right, r.right);
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        result = true;
        rec(p, q);
        return result;
    }

    public static void main(String[] args) {
        SameTree st = new SameTree();

        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(2, null, null);
        TreeNode two = new TreeNode(3, one, three);

        TreeNode seven = new TreeNode(3, one, three);
        System.out.println(st.isSameTree(two, seven));

        one = new TreeNode(1, null, null);
        three = new TreeNode(2, null, null);
        two = new TreeNode(3, one, three);

        seven = new TreeNode(3, three, one);
        System.out.println(st.isSameTree(two, seven));

        one = new TreeNode(1, null, null);
        two = new TreeNode(2, one, null);

        seven = new TreeNode(2, null, one);
        System.out.println(st.isSameTree(two, seven));

        System.out.println(st.isSameTree(null, null));

        one = new TreeNode(1, null, null);
        System.out.println(st.isSameTree(one, null));


//        Example 1:
//        Input: p = [1,2,3], q = [1,2,3]
//        Output: true
//
//        Example 2:
//        Input: p = [1,2], q = [1,null,2]
//        Output: false
//
//        Example 3:
//        Input: p = [1,2,1], q = [1,1,2]
//        Output: false
    }


}
