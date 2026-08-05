package com.oe.servlet.admin;

import java.io.IOException;
import java.util.List;

import com.oe.dao.userDAO;
import com.oe.entity.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
    "/admin/user", 
    "/admin/user/create", 
    "/admin/user/edit/*", 
    "/admin/user/update", 
    "/admin/user/delete", 
    "/admin/user/reset",
    "/admin/user/role"
})
public class adminUserController extends HttpServlet {
    
    private userDAO dao = new userDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        // 1. XỬ LÝ NÚT EDIT (Chỉ nạp user khi có lệnh edit)
        if (uri.contains("edit")) {
            String id = uri.substring(uri.lastIndexOf("/") + 1);
            user u = dao.findById(id);
            req.setAttribute("user", u); 
        } else {
            // [QUAN TRỌNG] Nếu không phải edit, nạp một đối tượng rỗng
            // để form tự động xóa trắng các ô input
            req.setAttribute("user", new user());
        }
        
        // 2. PHÂN TRANG
        int pageNumber = 0; 
        int pageSize = 10;  
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            pageNumber = Integer.parseInt(pageParam);
        }
        
        List<user> users = dao.findAll(pageNumber, pageSize);
        long totalUsers = dao.countAll();
        int maxPage = (int) Math.ceil((double) totalUsers / pageSize) - 1; 
        
        req.setAttribute("users", users);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("maxPage", maxPage);
        
        req.setAttribute("view", "/views/admin/user.jsp"); // Đẩy view vào attribute view
        req.getRequestDispatcher("/views/admin/adminLayout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        // Lấy dữ liệu từ Form (Check null để tránh lỗi)
        String id = req.getParameter("id"); 
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String adminStr = req.getParameter("admin");
        boolean admin = (adminStr != null) ? Boolean.parseBoolean(adminStr) : false;
        
        try {
            // [BỔ SUNG] LOGIC TẠO MỚI
            if (uri.contains("create")) {
                user u = new user(id, password, fullname, email, admin);
                dao.create(u);
                req.setAttribute("message", "Tạo tài khoản thành công!");
                
            } else if (uri.contains("update")) {
                user u = new user(id, password, fullname, email, admin);
                dao.update(u);
                req.setAttribute("message", "Cập nhật thành công!");
                
            } else if (uri.contains("delete")) {
                dao.delete(id);
                req.setAttribute("message", "Đã xóa tài khoản!");
                
            } else if (uri.contains("reset")) {
                // Reset chỉ cần xóa attribute user để form trắng
                req.removeAttribute("user");
                
            } else if (uri.contains("role")) {
                String action = req.getParameter("action"); 
                user uRole = dao.findById(id);
                if (uRole != null) {
                    uRole.setAdmin("grant".equals(action));
                    dao.update(uRole);
                    req.setAttribute("message", "Đã cập nhật quyền thành công!");
                }
            }
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        
        // TỐI ƯU: Sau khi làm xong Post, redirect về trang list để tránh lỗi F5 bị hỏi lại form
        resp.sendRedirect(req.getContextPath() + "/admin/user");
    }
}