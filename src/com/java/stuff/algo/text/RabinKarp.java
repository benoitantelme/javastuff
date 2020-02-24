package com.java.stuff.algo.text;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RabinKarp {
    String pattern;
    long hashedPattern;    // pattern hash value
    int m;                 // pattern length
    long Q;                // large prime number
    int R;                 // radix/number of characters in the alphabet used
    long RPowerMinusOne;   // R^(m-1) % Q to remove leading digits

    public RabinKarp(String pat) {
        this.pattern = pat;
        R = 256;
        m = pat.length();
        Q = BigInteger.probablePrime(31, new Random()).longValue();

        RPowerMinusOne = 1;
        for (int i = 1; i <= m-1; i++)
            RPowerMinusOne = (R * RPowerMinusOne) % Q;

        hashedPattern = hash(pat, m);
    }

    private long hash(String str, int length) {
        long h = 0;
        for (int j = 0; j < length; j++)
            h = (R * h + str.charAt(j)) % Q;

        return h;
    }

    private boolean check(String txt, int start) {
        for (int i = 0; i < m; i++)
            if (pattern.charAt(i) != txt.charAt(start + i))
                return false;

        return true;
    }

    public List<Integer> search(String txt) {
        List<Integer> result = new ArrayList<>();
        int n = txt.length();
        if (n < m){
            result.add(-1);
            return result;
        }

        long txtHash = hash(txt, m);

        // check for match at offset 0
        if ((hashedPattern == txtHash) && check(txt, 0))
            result.add(0);

        for (int i = m; i < n; i++) {
            // Remove leading digit then add trailing digit
            txtHash = (txtHash + Q - RPowerMinusOne * txt.charAt(i-m) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;

            int offset = i - m + 1;
            // if pattern match check charactersS
            if (hashedPattern == txtHash && check(txt, offset))
                result.add(offset);
        }

        return result;
    }

    public static void main(String args[]) {
        RabinKarp rbk;
        String txt = "aaaaabaaaba";
        String pattern =   "aaaa";
        System.out.println("For text: " + txt);
        System.out.println("And pattern: " + pattern);
        rbk = new RabinKarp(pattern);
        System.out.println(rbk.search(txt));

        txt = "ababdabacdababcabab";
        pattern = "ababcabab";
        System.out.println("For text: " + txt);
        System.out.println("And pattern: " + pattern);
        rbk = new RabinKarp(pattern);
        System.out.println(rbk.search(txt));
    }

}
