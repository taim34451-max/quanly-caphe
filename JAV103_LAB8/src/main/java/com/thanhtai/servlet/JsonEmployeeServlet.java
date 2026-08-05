package com.thanhtai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/json-employee")
public class JsonEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        // Trả về đúng định dạng đề bài yêu cầu
        String json = "{\n" +
                "  \"manv\": \"TeoNV\",\n" +
                "  \"hoTen\": \"Nguyễn Văn Tèo\",\n" +
                "  \"gioiTinh\": true,\n" +
                "  \"luong\": 950.5\n" +
                "}";
        
        resp.getWriter().print(json);
    }
}