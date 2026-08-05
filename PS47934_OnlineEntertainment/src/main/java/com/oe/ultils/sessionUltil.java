package com.oe.ultils;

import com.oe.entity.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class sessionUltil {

    // 1. Lưu dữ liệu vào Session
    public static void add(HttpServletRequest req, String name, Object value) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
    }

    // 2. Lấy dữ liệu từ Session
    public static Object get(HttpServletRequest req, String name) {
        HttpSession session = req.getSession();
        return session.getAttribute(name);
    }

    // 3. Xóa 1 thuộc tính khỏi Session
    public static void remove(HttpServletRequest req, String name) {
        HttpSession session = req.getSession();
        session.removeAttribute(name);
    }

    // 4. Hủy toàn bộ Session (Logout)
    public static void invalidate(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.invalidate();
    }

    // 5. Kiểm tra đã đăng nhập chưa
    public static boolean isLogin(HttpServletRequest req) {
        return get(req, "user") != null;
    }

    // 6. Lấy nhanh Username đang đăng nhập
    public static String getLoginedUsername(HttpServletRequest req) {
        Object obj = get(req, "user");
        return obj == null ? null : ((user) obj).getId();
    }
}