package com.java.stuff.leetcode2023.tries;

public class WordDictionary {
    Node root;

    public WordDictionary() {
        root = new Node('0'); //dummy node
    }

    public void addWord(String word) {
        Node tmp = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);

            Node child = tmp.children[c - 'a'];
            if (child == null) {
                child = new Node(c);
                tmp.children[c - 'a'] = child;
            }

            if (i == word.length() - 1)
                child.isWord = true;

            tmp = child;
        }
    }

    public boolean search(String word) {
        return searchRec(word, root);
    }

    public boolean searchRec(String word, Node tmp) {
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);

            if (c == '.') {
                boolean result = false;
                for(Node child : tmp.children)
                    if(child != null)
                        result = result | searchRec(word.substring(i+1), child);

                return result;
            } else {
                Node child = tmp.children[c - 'a'];
                if (child == null)
                    return false;

                tmp = child;
            }
        }

        if (tmp.isWord)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }

}
