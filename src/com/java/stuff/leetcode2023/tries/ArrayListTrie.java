package com.java.stuff.leetcode2023.tries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListTrie {
    List<String> words;

    public ArrayListTrie() {
        words = new ArrayList<>();
    }

    public void insert(String word) {
        words.add(word);
        Collections.sort(words);
    }

    public boolean search(String word) {
        return words.contains(word);
    }

    public boolean startsWith(String prefix) {
        int first = Collections.binarySearch(words, prefix);
        int last = Collections.binarySearch(words, prefix.concat("\uFFFF"));

        return first != last;
    }

    public static void main(String[] args) {
        ArrayListTrie obj = new ArrayListTrie();
        obj.insert("apple");
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("app"));
        System.out.println(obj.startsWith("app"));
        obj.insert("app");
        System.out.println(obj.search("app"));


    }

}
