package com.sunknowledge.dme.rcm.application.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ApplicationDateUtility {
//	public static LocalDate convertStringToDate(String data) {
//		int year = Integer.parseInt(data.substring(0, 4));
//		int month = Integer.parseInt(data.substring(4, 6));
//		int day = Integer.parseInt(data.substring(6, 8));
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = null;
//		try {
//		    date = dateFormat.parse(year+"-"+month+"-"+day);
//		    //String output = dateFormat.format(date);
//		}
//		catch (ParseException e) {
//		    e.printStackTrace();
//		}
//		return date;
//	}

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
