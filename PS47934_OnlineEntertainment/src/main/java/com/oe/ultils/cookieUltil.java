package com.oe.ultils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class cookieUltil {

    // 1. Tạo và gửi Cookie (Đã thêm mã hóa để xử lý dấu cách)
    public static Cookie add(String name, String value, int hours, HttpServletResponse resp) {
        // Mã hóa giá trị trước khi lưu để tránh lỗi ký tự đặc biệt
        String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
        
        Cookie cookie = new Cookie(name, encodedValue);
        cookie.setMaxAge(hours * 60 * 60);
        cookie.setPath("/");
        resp.addCookie(cookie);
        return cookie;
    }

    // 2. Đọc giá trị Cookie (Đã thêm giải mã để lấy lại dữ liệu gốc)
    public static String get(String name, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    // Giải mã giá trị sau khi đọc
                    return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                }
            }
        }
        return ""; 
    }
}