package com.ThanhTai.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/elservlet")
public class elservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Phân bổ dữ liệu vào các Scope
        request.setAttribute("x", 1000); // Request Scope
        request.getSession().setAttribute("y", 2000); // Session Scope
        request.getServletContext().setAttribute("z", 3000); // Application Scope
        request.getServletContext().setAttribute("x", 5000); // Trùng tên "x" ở Application Scope
        
        request.setAttribute("now", new Date());

        // 2. Truyền Cấu trúc dữ liệu (Map)
        Map<String, Double> diems = new HashMap<>();
        diems.put("toan", 5.0);
        diems.put("ly", 7.0);
        request.setAttribute("map", diems);

        // 3. Truyền Cấu trúc dữ liệu (List)
        List<String> tens = new ArrayList<>();
        tens.add("Hồng");
        tens.add("Phượng");
        request.setAttribute("list", tens);

        // 4. Điều hướng sang View
        request.getRequestDispatcher("/views/el.jsp").forward(request, response);
    }
}