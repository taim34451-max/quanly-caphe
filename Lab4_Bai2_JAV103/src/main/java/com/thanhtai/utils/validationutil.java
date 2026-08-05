package com.thanhtai.utils;

import java.util.regex.Pattern;

public class validationutil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}