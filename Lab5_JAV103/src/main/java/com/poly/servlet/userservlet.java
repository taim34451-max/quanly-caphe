package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.poly.dao.usermanager;
import com.poly.entity.user;

// Ánh xạ các đường dẫn URL tương ứng với các nút bấm trên giao diện JSP
@WebServlet({"/user/index", "/user/create", "/user/update", "/user/delete", "/user/edit"})
public class userservlet extends HttpServlet {
    
    // Gọi lớp xử lý CSDL
    usermanager manager = new usermanager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Cấu hình UTF-8 để nhận và gửi tiếng Việt không bị lỗi font
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Bắt lấy đường dẫn người dùng đang tương tác
        String uri = req.getRequestURI();

        try {
            if (uri.contains("create")) {
                user u = new user();
                u.setId(req.getParameter("id"));
                u.setPassword(req.getParameter("password"));
                u.setFullname(req.getParameter("fullname"));
                u.setEmail(req.getParameter("email"));
                u.setAdmin(Boolean.parseBoolean(req.getParameter("admin")));
                manager.create(u);
                
            } else if (uri.contains("update")) {
                user u = new user();
                u.setId(req.getParameter("id"));
                u.setPassword(req.getParameter("password"));
                u.setFullname(req.getParameter("fullname"));
                u.setEmail(req.getParameter("email"));
                u.setAdmin(Boolean.parseBoolean(req.getParameter("admin")));
                manager.update(u);
                
            } else if (uri.contains("delete")) {
                String id = req.getParameter("id");
                manager.deleteById(id);
                
            } else if (uri.contains("edit")) {
                // Hứng ID từ link Edit trên bảng và tìm trong CSDL đẩy lên form
                String id = req.getParameter("id");
                user u = manager.findById(id);
                req.setAttribute("form", u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Bất kể hành động nào diễn ra, luôn tải lại danh sách mới nhất để đổ ra bảng
        List<user> list = manager.findAll();
        req.setAttribute("items", list);

        // Chuyển tiếp luồng dữ liệu sang trang user.jsp để hiển thị
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}