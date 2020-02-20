package com.java.stuff.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

public class TreeMedium {

    static public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList<>();

        int depht = (int) (Math.log(label)/ Math.log(2));
        int move;

        while (depht > 0){
            int pow = (int) Math.pow(2, depht);
            move = label - pow;
            res.addFirst(label);
            int a = (int) Math.ceil(move / 2.0);
            label = pow - (int) Math.ceil(move /2) - 1;
            depht -= 1;
        }

        res.addFirst(1);

        return res;
    }


    public static void main(String args[]){
        System.out.println(pathInZigZagTree(16));
        System.out.println(pathInZigZagTree(14));
        System.out.println(pathInZigZagTree(26));
    }



}
