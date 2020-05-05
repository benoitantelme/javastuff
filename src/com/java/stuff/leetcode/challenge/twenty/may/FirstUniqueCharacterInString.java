package com.java.stuff.leetcode.challenge.twenty.may;

public class FirstUniqueCharacterInString {


    public int firstUniqChar(String s) {
        int[] chars = new int[26];
        s.chars().forEach(code -> chars[code - 'a']++);

        for(int i =0; i < s.length(); i++)
            if(chars[s.charAt(i)-'a'] == 1)
                return i;

        return -1;
    }

    public static void main(String[] args){
        FirstUniqueCharacterInString first = new FirstUniqueCharacterInString();
        System.out.println(first.firstUniqChar("loveleetcode"));
        System.out.println(first.firstUniqChar("leetcode"));

    }
}
