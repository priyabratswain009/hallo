package com.sunknowledge.dme.rcm.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public interface CommonUtil {
    static LocalDate dateconverter(String inputDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate outputDate = LocalDate.parse(inputDate, formatter);
        return outputDate;
    }
}
