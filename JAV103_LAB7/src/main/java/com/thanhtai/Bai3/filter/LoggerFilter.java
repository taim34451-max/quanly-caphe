package com.thanhtai.Bai3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/admin/*")
public class LoggerFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String uri = req.getRequestURI();
        Object user = req.getSession().getAttribute("user");

        System.out.println("User: " + user);
        System.out.println("Access URI: " + uri);

        chain.doFilter(request, response);
    }
}