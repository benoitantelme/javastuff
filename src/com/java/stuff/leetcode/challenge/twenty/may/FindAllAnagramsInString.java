package com.java.stuff.leetcode.challenge.twenty.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        Map<Character, Integer> occurences = new HashMap<>();
        for(char c : p.toCharArray())
            occurences.put(c, occurences.getOrDefault(c, 0)+1);

        Map<Character, Integer> tmp = new HashMap<>();
        for(int i = 0; i < s.length()-p.length()+1; i++) {
            // first pass
            if(i == 0){
                for(int j = i; j < i+p.length(); j++){
                    char c = s.charAt(j);
                    tmp.put(c, tmp.getOrDefault(c, 0)+1);
                }
            }else{
                // manage first char of last string
                char prev = s.charAt(i - 1);
                int times = tmp.get(prev);
                if(times == 1)
                    tmp.remove(prev);
                else
                    tmp.put(prev, times -1);

                char c = s.charAt(i+ p.length()-1);
                tmp.put(c, tmp.getOrDefault(c, 0)+1);
            }

            if(tmp.equals(occurences))
                res.add(i);
        }

        return res;
    }

}
