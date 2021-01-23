package com.java.stuff.leetcode.hard;

import java.util.List;

public class WordSearchTwo {

    public static void main(String[] args) {
        WordSearchTwo ws = new WordSearchTwo();

        if(! ws.findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},
                new String[]{"oath","pea","eat","rain"}).equals(List.of("eat","oath")))
            System.out.println("wrong");

        if(! ws.findWords(new char[][]{{'a'},{'b'},{'c'},{'d'}},
                new String[]{"abcb",}).equals(List.of()))
            System.out.println("wrong");

    }

    class TrieNode{

        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();

        for(String w : words){
            TrieNode current = root;
            for(char c : w.toCharArray()){
                int i = c - 'a';

                if(current.children[i] == null)
                    current.children[i] = new TrieNode();

                current = current.children[i];
            }

            current.word = w;
        }

        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);


        return List.of();
    }

}
