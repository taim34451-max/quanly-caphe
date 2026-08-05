package com.thanhtai.Bai2.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

import com.thanhtai.Bai2.model.User;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // demo hard-code
        if ("admin".equals(username) && "123".equals(password)) {
            User user = new User("admin", "Admin User", true);
            req.getSession().setAttribute("user", user);

            
            resp.sendRedirect(req.getContextPath() + "/views/index.jsp");

        } else {
            
            req.setAttribute("message", "Login failed");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    } 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
}