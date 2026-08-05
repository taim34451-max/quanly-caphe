package com.fpoly.filter;

import java.io.IOException;

import com.poly.entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/admin/*", "/secured/*"})
public class authfilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        Object userObj = session.getAttribute("user");
        
        if (userObj == null) {
            session.setAttribute("securi", req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        
        String uri = req.getRequestURI();
        if (uri.contains("/admin/")) {
            // Ép kiểu về class user của DB
            User currentUser = (User) userObj;
            
            // Tùy theo entity em viết getAdmin() hay isAdmin() thì chỉnh lại cho khớp nhé
            // Thông thường với kiểu boolean, Java sẽ sinh ra hàm isAdmin() hoặc getAdmin()
            if (!currentUser.getAdmin()) { 
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập khu vực quản trị.");
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
}