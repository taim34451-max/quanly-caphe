package lab4.bai1.utils;

import java.util.regex.Pattern;

public class ValidationUtil {
    // Regex chuẩn cho Email
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    // Regex chuẩn cho Số điện thoại VN (bắt đầu bằng 0 hoặc +84, theo sau là 9 số hợp lệ)
    private static final String PHONE_VN_REGEX = "^(0|\\+84)(3|5|7|8|9)[0-9]{8}$";

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) return false;
        return Pattern.matches(PHONE_VN_REGEX, phone);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}