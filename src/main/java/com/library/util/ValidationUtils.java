package com.library.util;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern ISBN_PATTERN = Pattern.compile("^(?:ISBN(?:-13)?:?\\s*)?(?=[0-9X]{10}$|(?=(?:[0-9]+[-\\s]){3})[0-9-\\sX]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[-\\s]){4})[0-9-\\sX]{17}$)(?:97[89][-\\s]?)?[0-9]{1,5}[-\\s]?[0-9]+[-\\s]?[0-9]+[-\\s]?[0-9X]$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidIsbn(String isbn) {
        return isbn != null && ISBN_PATTERN.matcher(isbn).matches();
    }
}