package com.ThanhTai.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.ThanhTai.Model.user; // Import class user

@WebServlet("/user.php")
public class userservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Khởi tạo danh sách dữ liệu giả lập
        List<user> users = Arrays.asList(
            new user("Username 1", "Password 1", true),
            new user("Username 2", "Password 2", false),
            new user("Username 3", "Password 3", true)
        );

        // Đẩy dữ liệu lên Request để chia sẻ sang JSP
        req.setAttribute("message", "Quản lý người sử dụng!");
        req.setAttribute("form", users.get(0));
        req.setAttribute("items", users);

        // Điều hướng sang file index.jsp (nằm trong thư mục views/user/)
        req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
    }
}