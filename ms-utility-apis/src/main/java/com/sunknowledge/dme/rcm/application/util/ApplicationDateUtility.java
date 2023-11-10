package com.sunknowledge.dme.rcm.application.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApplicationDateUtility {
    public static LocalDate convertStringToDateOnSpecific(String data) {
        String year = data.substring(0, 4);
        System.out.println("Year=>"+year);
        String month = data.substring(4, 6);
        System.out.println("Month=>"+month);
        String day = data.substring(6, 8);
        System.out.println("Day=>"+day);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String d = year+"-"+month+"-"+day;
        LocalDate outputDate = LocalDate.parse(d, formatter);
        System.out.println(outputDate.toString()); // Wed Dec 04 00:00:00 CST 2013

        String output = formatter.format(outputDate);
        System.out.println(output); // 2013-12-04
        return outputDate;
    }
}
