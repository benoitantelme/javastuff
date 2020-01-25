package com.java.stuff.leetcode;

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
            if(i < s.length()-2 && array[i+2] == '#'){
                sb.append((char) ((Integer.parseInt("" + array[i] + array[i+1])) + 96));
                i +=2;
            }else {
                sb.append((char) (Character.getNumericValue(array[i]) + 96));
            }
        }

        return sb.toString();
    }

    public static void main(String args[]) {
        System.out.println(balancedStringSplit("RLLLLRRRLR"));
        System.out.println(freqAlphabets("1326#"));

    }

}
