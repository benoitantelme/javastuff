package com.java.stuff.codility.stuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Three {

    public static void main(String[] args) throws ParseException {
        Three three = new Three();

        String input = "admin  -wx 29 Sep 1983        833 source.h\n" +
                "admin  r-x 23 Jun 2003     854016 blockbuster.mpeg\n" +
                "admin  --x 02 Jul 1997        821 delete-this.py\n" +
                "admin  -w- 15 Feb 1971      23552 library.dll\n" +
                "admin  --x 15 May 1979  645922816 logs.zip\n" +
                "jane   --x 04 Dec 2010      93184 old-photos.rar\n" +
                "jane   -w- 08 Feb 1982  681574400 important.java\n" +
                "admin  rwx 26 Dec 1952   14680064 to-do-list.txt";


        System.out.println(three.solution(input));
    }

    protected final String EOL = Character.toString((char) 10);
    protected final int owner_length = 6;
    protected final int perm_length = 3;
    protected final int date_length = 11;
    protected final int size_length = 10;
    protected final String EMPTY = "";

    protected final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

    public String solution(String S) {
        List<Date> dates = new ArrayList<>();
        for (String line : S.split(EOL)) {
            String dateString = processLine(line);
            if (!dateString.isEmpty()) {
                try {
                    dates.add(format.parse(dateString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if (dates.isEmpty())
            return "NO FILES";

        Date minDate = dates.stream().min(Date::compareTo).get();
        return format.format(minDate);
    }

    protected String processLine(String line) {
        String owner = line.substring(0, owner_length).trim();
        if (!owner.equals("admin"))
            return EMPTY;

        String rest = line.substring(owner_length + 1);
        String perm = rest.substring(0, perm_length);
        if (!perm.contains("x"))
            return EMPTY;

        rest = rest.substring(perm_length + 1);
        String date = rest.substring(0, date_length);
        rest = rest.substring(date_length + 1);

        String size = rest.substring(0, size_length).trim();
        if (Integer.parseInt(size) >= (int) (Math.pow(2, 20) * 14))
            return EMPTY;

        return date;
    }


}
