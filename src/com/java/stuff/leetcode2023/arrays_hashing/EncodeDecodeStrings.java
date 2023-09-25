package com.java.stuff.leetcode2023.arrays_hashing;

import java.util.ArrayList;
import java.util.List;

public class EncodeDecodeStrings {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(str.length()).append("#").append(str);

        return sb.toString();
    }


    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();

        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') j++;

            int size = Integer.parseInt(str.substring(i, j));
            String part = str.substring(j + 1, j + 1 + size);
            result.add(part);
            i = j + 1 + size;
        }

        return result;
    }


    public static void main(String[] args) {
        EncodeDecodeStrings eds = new EncodeDecodeStrings();

        List<String> list = List.of("the", "code", "love", "you");
        String str = eds.encode(list);
        System.out.println(eds.decode(str));

        list = List.of("one", "two", "three", "four");
        str = eds.encode(list);
        System.out.println(eds.decode(str));
    }

}
