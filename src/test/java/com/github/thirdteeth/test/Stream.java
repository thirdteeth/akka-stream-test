package com.github.thirdteeth.test;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Stream {

    @Test
    public void test1() {
        String formatter = "MMddyyHHmm";
        DateFormat df = getDateFormat(formatter, TimeZone.getTimeZone("UTC"));
        try {
            Date date = df.parse("0802180357");
            DateFormat df1 = getDateFormat(formatter, null);
            DateFormat df2 = getDateFormat("HHmm", null);
            DateFormat df3 = getDateFormat("MMddyy", null);
            System.out.println(TimeZone.getDefault());
            System.out.println(df1.format(date));
            System.out.println(df2.format(date));
            System.out.println(df3.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private DateFormat getDateFormat(String pattern, TimeZone timeZone) {
        DateFormat df = new SimpleDateFormat(pattern);
        if (timeZone != null)
            df.setTimeZone(timeZone);
        return df;
    }

    @Test
    public void test2() {
        String start = "0802182215";
        DateFormat format = getDateFormat("MMddyyHHmm", null);
        try {
            Date parsedTime = format.parse(start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedTime);
            calendar.add(Calendar.HOUR_OF_DAY, 3);
            System.out.println(format.format(calendar.getTime()));
        } catch (ParseException e) {
        }
    }
}
