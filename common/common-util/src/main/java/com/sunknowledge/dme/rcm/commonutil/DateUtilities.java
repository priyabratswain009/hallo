package com.sunknowledge.dme.rcm.commonutil;

public class DateUtilities {
    public static String dateStringConverter(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        return (day+"/"+month+"/"+year);
    }
}
