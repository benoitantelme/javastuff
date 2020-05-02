package com.java.stuff.leetcode.challenge.twenty.may;

import java.util.Set;
import java.util.stream.Collectors;


public class JewelsAndStones {

    public int numJewelsInStones(String J, String S) {
        Set<Integer> set = J.chars().boxed().collect(Collectors.toSet());

        return (int) S.chars().filter(chr -> set.contains(chr)).count();
    }

    public static void main(String[] args){
        JewelsAndStones jas = new JewelsAndStones();
        System.out.println(jas.numJewelsInStones("aA", "aAAbbbb"));

        System.out.println(jas.numJewelsInStones("", ""));
    }


}
