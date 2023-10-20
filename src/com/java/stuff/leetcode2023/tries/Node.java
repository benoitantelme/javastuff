package com.java.stuff.leetcode2023.tries;

public class Node {

    public char value;
    public boolean isWord;
    public Node[] children;

    public Node(char val) {
        this.value = val;
        this.isWord = false;
        this.children = new Node[26];
    }
}
