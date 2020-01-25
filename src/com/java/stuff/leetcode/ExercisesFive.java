package com.java.stuff.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExercisesFive {

    public static int balancedStringSplit(String s) {
        int count = 0;
        int result = 0;
        for(char c : s.toCharArray()){
            if(c == 'L')
                count -=1;
            else
                count +=1;

            if(count == 0)
                result +=1;
        }

        return result;
    }

    public static String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] array = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            int charoffset;
            if(i < s.length()-2 && array[i+2] == '#'){
                charoffset = Integer.parseInt("" + array[i] + array[i+1]);
                i +=2;
            }else
                charoffset = Character.getNumericValue(array[i]);


            sb.append((char) (charoffset + 96));
        }

        return sb.toString();
    }

    public static void reverseString(char[] s) {
        int i = 0;
        int j = s.length-1;

        while(i < j){
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }

    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for(String email : emails){
            String[] splittedEmail = email.split("@");

            String localName = splittedEmail[0];
            if(localName.contains("+"))
                localName = localName.substring(0, localName.indexOf("+"));

            set.add(localName.replace(".", "") + "@" + splittedEmail[1]);
        }

        return set.size();
    }

    public static int findLUSlength(String a, String b) {
        int lengtha = a.length();
        if(a.equals(b))
            return -1;

        int lengthb = b.length();
        if(lengtha == lengthb)
            return lengtha;
        else if(lengtha > lengthb)
            return lengtha;
        else
            return lengthb;
    }

    public static void main(String args[]) {
        System.out.println(balancedStringSplit("RLLLLRRRLR"));
        System.out.println(freqAlphabets("1326#"));

        char[] a = new char[]{'h','e','l','l','o'};
        System.out.println(Arrays.toString(a));
        reverseString(a);
        System.out.println(Arrays.toString(a));

        System.out.println(numUniqueEmails(new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com",
                "testemail+david@lee.tcode.com"}));

        System.out.println(findLUSlength("aaa", "aaa"));
    }

}
