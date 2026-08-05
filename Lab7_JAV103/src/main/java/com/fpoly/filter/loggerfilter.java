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

@WebFilter("/admin/*")
public class loggerfilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        Object userObj = req.getSession().getAttribute("user");
        
        // Rút ID của user từ database để in log
        String username = (userObj != null) ? ((User) userObj).getId() : "Guest";
        
        System.out.println("LOG SYSTEM - Người dùng: " + username + " | Truy cập: " + uri);
        
        chain.doFilter(request, response);
    }
}