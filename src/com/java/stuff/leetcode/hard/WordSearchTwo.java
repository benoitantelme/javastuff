package com.java.stuff.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class WordSearchTwo {

    public static void main(String[] args) {
        WordSearchTwo ws = new WordSearchTwo();

        if(! ws.findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},
                new String[]{"oath","pea","eat","rain"}).containsAll(List.of("eat","oath")))
            System.out.println("wrong");

        if(! ws.findWords(new char[][]{{'a'},{'b'},{'c'},{'d'}},
                new String[]{"abcb",}).containsAll(List.of()))
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
        List<String> result = new ArrayList<>();

        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
                search(board, i, j, root, result);

        return result;
    }

    public void search(char[][] board, int i, int j, TrieNode node, List<String> result){
        char c = board[i][j];

        if(c == '*' || node.children[c - 'a'] == null)
            return;

        node = node.children[c - 'a'];
        if(node.word != null){
            result.add(node.word);
            node.word = null;
        }

        board[i][j] = '*';
        if(i > 0)
            search(board, i-1, j, node, result);
        if(j > 0)
            search(board, i, j-1, node, result);
        if(i < board.length-1)
            search(board, i+1, j, node, result);
        if(j < board[0].length-1)
            search(board, i, j+1, node, result);

        board[i][j] = c;
    }

}
