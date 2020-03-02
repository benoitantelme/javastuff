package com.java.stuff.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Codec {
    private static final String HTTP_TINYURL_COM = "http://tinyurl.com/";
    private final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private AtomicLong unique = new AtomicLong(147);
    private final Map<Integer, String> map = new ConcurrentHashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        long uniqueLong = unique.getAndIncrement();

        if (uniqueLong == 0)
            return HTTP_TINYURL_COM + "0";

        List<Integer> digits = new ArrayList<>();

        int key = 0;
        while (uniqueLong > 0) {
            int rest = (int) uniqueLong % 62;
            digits.add(rest);
            key += rest;
            uniqueLong /= 62;
        }

        String result = "";
        for (Integer n : digits)
            result += alphabet.charAt(n);


        map.put(key, longUrl);

        return HTTP_TINYURL_COM + result;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        shortUrl = shortUrl.split(HTTP_TINYURL_COM)[1];
        int key = 0;
        for (char c : shortUrl.toCharArray())
            key += alphabet.indexOf(c);

        return map.get(key);
    }


    public static void main(String[] args) {
        Codec c = new Codec();

        String encoding = c.encode("https://thatisawebsiteaddress.com");
        System.out.println(c.decode(encoding));

        encoding = c.encode("https://thatisdress.com");
        System.out.println(c.decode(encoding));

        encoding = c.encode("https://46467266127621761271.com");
        System.out.println(c.decode(encoding));

        encoding = c.encode("https://46467blablabla271.com");
        System.out.println(c.decode(encoding));

    }

}
