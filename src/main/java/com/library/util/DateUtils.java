package com.library.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatDate(LocalDate date) {
        return date != null ? date.format(FORMATTER) : null;
    }

    public static LocalDate parseDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, FORMATTER) : null;
    }
}