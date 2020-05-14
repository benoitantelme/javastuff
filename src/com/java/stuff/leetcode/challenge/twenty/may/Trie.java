package com.java.stuff.leetcode.challenge.twenty.may;

public class Trie {

    private class Node {
        Node[] children = new Node[26];

        boolean last;

        Node() {
            last = false;
            for (int i = 0; i < 26; i++)
                children[i] = null;
        }


    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (temp.children[index] == null)
                temp.children[index] = new Node();

            temp = temp.children[index];
        }

        temp.last = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (temp.children[index] != null)
                temp = temp.children[index];
            else
                return false;
        }

        return temp.last;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node temp = root;

        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';

            if (temp.children[index] != null)
                temp = temp.children[index];
            else
                return false;
        }

        return true;
    }

}
