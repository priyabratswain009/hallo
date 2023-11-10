package com.sunknowledge.dme.rcm.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.format.DateTimeParseException;
import java.time.YearMonth;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public interface ValidationUtil {
    static boolean isValidDate(String dateAsString) {
        try {
            DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[[dd-]MM-]yyyy");
            TemporalAccessor parsedDate = FORMATTER.parseBest(dateAsString, LocalDate::from, YearMonth::from, Year::from);
            return FORMATTER.format(parsedDate).equals(dateAsString);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    static boolean isValidStatus(String status) {
        ArrayList<String> statuses = new ArrayList<String>() {
            {
                add("active");
                add("deactive");
            }
        };
        return statuses.contains(status.trim());
    }

    static boolean isIntegerValue(String value) {
        try {
            int a = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static boolean isPersonName(String value) {
        return Pattern.compile(new String("^[a-zA-Z\\s]*$")).matcher(value).matches();
    }

    static boolean isValidEmail(String value) {
        return Pattern.compile(new String("^(.+)@(.+)$")).matcher(value).matches();
    }
}
