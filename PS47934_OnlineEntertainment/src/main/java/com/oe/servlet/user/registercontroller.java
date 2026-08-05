package com.oe.servlet.user;

import java.io.IOException;

import com.oe.dao.userDAO;
import com.oe.entity.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class registercontroller extends HttpServlet {
    
    private userDAO dao = new userDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đã sửa thêm /user/ vào đường dẫn
        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");

        try {
            user existingUser = dao.findById(id); 
            if (existingUser != null) {
                req.setAttribute("error", "Tên đăng nhập '" + id + "' đã có người sử dụng!");
                // Đã sửa thêm /user/
                req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
                return;
            }

            if (dao.isEmailExists(email)) {
                req.setAttribute("error", "Email này đã được đăng ký cho một tài khoản khác!");
                // Đã sửa thêm /user/
                req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
                return;
            }

            user newUser = new user(id, password, fullname, email);
            dao.create(newUser);
            
            req.setAttribute("message", "Đăng ký thành công! Hãy đăng nhập nhé.");
            // Chuyển hướng sang trang login (cũng phải có /user/)
            req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Hệ thống đang bảo trì, vui lòng thử lại sau!");
            req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
        }
    }
}