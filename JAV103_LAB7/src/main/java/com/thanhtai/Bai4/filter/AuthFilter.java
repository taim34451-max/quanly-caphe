package com.thanhtai.Bai4.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.thanhtai.Bai2.model.User;

@WebFilter("/admin/*")

public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Object user = req.getSession().getAttribute("user");

        if (user == null) {
        	resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        if (!((User) user).isAdmin()) {
        	resp.sendRedirect(req.getContextPath() + "/views/error.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}