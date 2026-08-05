package com.fpoly.servlet;

import java.io.IOException;

import com.poly.dao.usermanager;
import com.poly.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/account/sign-in")
public class loginservlet extends HttpServlet {
    
    // Khởi tạo công cụ giao tiếp với Database
    usermanager manager = new usermanager();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("username");
        String pw = req.getParameter("password");
        
        try {
            // Xuống Database tìm user theo ID
            User u = manager.findById(id);
            
            // Kiểm tra user có tồn tại và mật khẩu có khớp không
            if (u != null && u.getPassword().equals(pw)) {
                
                // Thành công: Đẩy nguyên object user từ CSDL vào Session
                req.getSession().setAttribute("user", u);
                req.setAttribute("message", "Đăng nhập thành công!");
                
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                
            } else {
                req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi kết nối cơ sở dữ liệu!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}