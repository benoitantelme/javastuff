package com.java.stuff.leetcode2023.tries;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class TreesetTrie {
    NavigableSet<String> words;

    public TreesetTrie() {
        words = new TreeSet<>();
    }

    public void insert(String word) {
        words.add(word);
    }

    public boolean search(String word) {
        return words.contains(word);
    }

    public boolean startsWith(String prefix) {
        Set<String> search = words.subSet(prefix, prefix + Character.MAX_VALUE);

        return !search.isEmpty();
    }


    public static void main(String[] args) {
        TreesetTrie obj = new TreesetTrie();
        obj.insert("apple");
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("app"));
        System.out.println(obj.startsWith("app"));
        obj.insert("app");
        System.out.println(obj.search("app"));


    }

}
