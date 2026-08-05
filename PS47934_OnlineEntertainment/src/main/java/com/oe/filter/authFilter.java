package com.oe.filter;

import java.io.IOException;

import com.oe.entity.user;
import com.oe.ultils.sessionUltil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Gắn Filter vào các đường dẫn cần bảo mật
@WebFilter(filterName = "authFilter", urlPatterns = {
    "/admin/*",          // Chặn toàn bộ khu vực của Admin
    "/favorite",         // Trang danh sách yêu thích
    "/like", "/unlike",  // Nút Like / Unlike
    "/share",            // Nút Share
    "/change-password",  // Đổi mật khẩu
    "/edit-profile"      // Cập nhật tài khoản
})
public class authFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String uri = req.getRequestURI();
        
     // 1. KIỂM TRA ĐĂNG NHẬP
        if (!sessionUltil.isLogin(req)) {
            // Ghi nhớ lại URL hiện tại vào session trước khi đuổi đi
            sessionUltil.add(req, "security-uri", uri); 
            // Nếu chưa đăng nhập -> Đá thẳng về trang Login
            resp.sendRedirect(req.getContextPath() + "/login");
            return; // Lệnh return này cực quan trọng để cắt đứt mạch chạy
        }
        
        // 2. KIỂM TRA QUYỀN ADMIN (Nếu URL có chứa /admin/)
        if (uri.contains("/admin/")) {
            user u = (user) sessionUltil.get(req, "user");
            // Nếu tài khoản không phải là Admin (admin = false)
            if (u == null || !u.getAdmin()) {
                // Đá về trang chủ, không cho vào khu vực cấm
                resp.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }
        
        // 3. VƯỢT QUA CÁC BÀI TEST -> Cho phép đi tiếp vào Servlet
        chain.doFilter(request, response);
    }
}