package com.java.stuff.leetcode.challenge.twenty.may;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RansomNote {

    // super slow
    public boolean canConstructSreams(String ransomNote, String magazine) {
        Map<Integer, Long> magazineCount = magazine.chars().boxed().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Integer, Long> ransomNoteCount = ransomNote.chars().boxed().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long errors = ransomNoteCount.entrySet().stream().
                filter(entry -> !magazineCount.containsKey(entry.getKey()) ||
                        entry.getValue() > magazineCount.get(entry.getKey())).
                count();

        return errors == 0;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineCount = new HashMap<>();
        for(char c : magazine.toCharArray())
            magazineCount.compute(c, (key, val) -> val == null ? 1 : val+1);

        for(char c : ransomNote.toCharArray()){
            if(!magazineCount.containsKey(c))
                return false;
            else{
                Integer val = magazineCount.get(c);
                if(val == 0)
                    return false;
                else
                    magazineCount.put(c, val-1);
            }
        }

        return true;
    }

    public static void main(String[] args){
        RansomNote rn= new RansomNote();
        System.out.println(rn.canConstruct("aa", "aab"));
        System.out.println(rn.canConstruct("aabb", "aab"));
        System.out.println(rn.canConstruct("", ""));

    }
}
