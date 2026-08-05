package com.thanhtai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index") 
public class MainControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        
        // Cập nhật href trỏ thẳng vào thư mục views/ để tránh lỗi 404
        String html = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <title>Menu Tổng Hợp Lab 8</title>"
                + "    <style>"
                + "        body { font-family: Arial, sans-serif; margin: 50px; background-color: #f4f4f9; }"
                + "        h1 { color: #333; }"
                + "        ul { list-style-type: none; padding: 0; }"
                + "        li { margin: 15px 0; }"
                + "        a { display: inline-block; width: 350px; padding: 12px 20px; "
                + "            background-color: #007bff; color: white; text-decoration: none; "
                + "            border-radius: 5px; font-weight: bold; box-shadow: 0 2px 5px rgba(0,0,0,0.2); "
                + "            transition: background 0.3s; }"
                + "        a:hover { background-color: #0056b3; }"
                + "    </style>"
                + "</head>"
                + "<body>"
                + "    <h1>FPT POLYTECHNIC - QUẢN LÝ BÀI TẬP LAB 8</h1>"
                + "    <p>Nhập vào các liên kết dưới đây để chạy và kiểm tra từng bài:</p>"
                + "    <ul>"
                + "        <li><a href='views/bai1.html' target='_blank'>Bài 1: Fetch API JSON từ Servlet</a></li>"
                + "        <li><a href='views/bai2.html' target='_blank'>Bài 2: Upload File qua Ajax</a></li>"
                + "        <li><a href='views/employee-rest-client.html' target='_blank'>Bài 4: Giao diện Quản lý nhân viên (REST CLIENT)</a></li>"
                + "    </ul>"
                + "</body>"
                + "</html>";
                
        resp.getWriter().print(html);
    }
}