package com.java.stuff.leetcode.easy;

import java.util.*;

public class ExercisesFour {

    public static int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>();

        for(int nbr : A)
            if(set.contains(nbr))
                return nbr;
            else
                set.add(nbr);

            return -1;
    }

    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int nbr : arr)
            map.merge(nbr,1, (a, b) -> a+b);

        Set<Integer> set = new HashSet<>(map.values());

        return set.size() == map.size();
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        for(String s : cpdomains){
            String[] splittedS = s.split(" ");

            Integer freq = Integer.valueOf(splittedS[0]);
            String domains = splittedS[1];

            while(domains.contains(".")){
                map.merge(domains, freq, (a, b) -> a + freq);
                domains = domains.substring(domains.indexOf(".")+1);
            }
            map.merge(domains, freq, (a, b) -> a + freq);
        }

        for(Map.Entry<String, Integer> entry : map.entrySet())
            result.add(entry.getValue() + " " + entry.getKey());

        return result;
    }

    public static int countCharacters(String[] words, String chars) {
        Map<Character, Integer> charsmap = new HashMap<>();
        for(Character c : chars.toCharArray())
            charsmap.merge(c,1, (a, b) -> a+b);

        int res = 0;
        for(String word : words) {
            Map<Character, Integer> wordmap = new HashMap<>();
            for (Character c : word.toCharArray())
                wordmap.merge(c, 1, (a, b) -> a + b);

            boolean ok = true;
            for (Map.Entry<Character, Integer> entry : wordmap.entrySet()) {
                Integer times = charsmap.get(entry.getKey());
                if (times == null || entry.getValue() > times) {
                    ok = false;
                    break;
                }
            }

            if(ok)
                res += word.length();
        }

        return  res;
    }

    public static void main(String args[]) {
        System.out.println(repeatedNTimes(new int[]{5,1,5,2,5,3,5,4}));
        System.out.println(uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));
        System.out.println(subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));
        System.out.println(countCharacters(new String[]{"cat","bt","hat","tree"}, "atach"));
    }
}
