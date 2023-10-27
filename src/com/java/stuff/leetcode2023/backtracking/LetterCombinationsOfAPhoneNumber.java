package com.java.stuff.leetcode2023.backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class LetterCombinationsOfAPhoneNumber {
    static Map<Integer, List<String>> mappings;
    List<String> result;

    void rec(List<Integer> digits, int index, String set) {
        if (index >= digits.size()) {
            result.add(set);
            return;
        }

        List<String> mapping = mappings.get(digits.get(index));
        for (String l : mapping)
            rec(digits, index + 1, set + l);
    }

    public List<String> letterCombinations(String digits) {
        initMapping();
        result = new ArrayList<>();

        if(digits.isEmpty())
            return result;

        rec(digits.chars().mapToObj(c -> c - '0').collect(Collectors.toList()),
                0, "");
        return result;
    }

    void initMapping() {
        mappings = new HashMap<>();
        mappings.put(2, List.of("a", "b", "c"));
        mappings.put(3, List.of("d", "e", "f"));
        mappings.put(4, List.of("g", "h", "i"));
        mappings.put(5, List.of("j", "k", "l"));
        mappings.put(6, List.of("m", "n", "o"));
        mappings.put(7, List.of("p", "q", "r", "s"));
        mappings.put(8, List.of("t", "u", "v"));
        mappings.put(9, List.of("w", "x", "y", "z"));
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber lc = new LetterCombinationsOfAPhoneNumber();

        System.out.println(lc.letterCombinations("23"));
        System.out.println(lc.letterCombinations(""));
        System.out.println(lc.letterCombinations("2"));

//        Example 1:
//        Input: digits = "23"
//        Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//        Example 2:
//        Input: digits = ""
//        Output: []
//
//        Example 3:
//        Input: digits = "2"
//        Output: ["a","b","c"]
    }

}
