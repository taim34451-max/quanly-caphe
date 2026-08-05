package com.thanhtai.Bai2.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/") // 👈 QUAN TRỌNG
public class ServletHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // chuyển tới trang chủ JSP
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}