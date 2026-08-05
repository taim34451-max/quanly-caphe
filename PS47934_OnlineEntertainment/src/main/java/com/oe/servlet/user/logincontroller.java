package com.oe.servlet.user;

import java.io.IOException;

import com.oe.dao.userDAO;
import com.oe.entity.user;
import com.oe.ultils.cookieUltil;
import com.oe.ultils.sessionUltil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/login", "/logoff"})
public class logincontroller extends HttpServlet {
    
    private userDAO dao = new userDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        // Xử lý Đăng xuất
        if (uri.contains("logoff")) {
            sessionUltil.invalidate(req);
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // Hiển thị form login và lấy Cookie (nếu có)
        String username = cookieUltil.get("username", req);
        String password = cookieUltil.get("password", req);

        req.setAttribute("username", username);
        req.setAttribute("password", password);

        // [CHUẨN HÓA LAYOUT] Đưa trang login vào khung layout để không bị mất Menu/CSS
        req.setAttribute("view", "/views/user/login.jsp");
        req.getRequestDispatcher("/views/user/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu khách hàng gõ trên form
        String id = req.getParameter("username");
        String pass = req.getParameter("password");
        boolean remember = req.getParameter("remember") != null;

        // Gọi DAO kiểm tra thông tin
        user u = dao.checkLogin(id, pass);
        
        if (u != null) {
            // Đăng nhập thành công -> Cấp thẻ VIP vào Session
            sessionUltil.add(req, "user", u);

            // Xử lý Ghi nhớ Cookie
            if (remember) {
                cookieUltil.add("username", id, 24, resp);
                cookieUltil.add("password", pass, 24, resp);
            } else {
                cookieUltil.add("username", id, 0, resp);
                cookieUltil.add("password", pass, 0, resp);
            }

            // ==============================================================
            // 1. NHẮC NHỞ MẬT KHẨU TẠM (SOFT PROMPT)
            // ==============================================================
            String cleanPass = pass.trim();
            if (cleanPass.matches("\\d{6}")) {
                // Nhét lời nhắc nhở vào Session, KHÔNG bẻ lái sang trang đổi pass
                sessionUltil.add(req, "message", "Bạn đang sử dụng mật khẩu tạm. Vui lòng đổi mật khẩu mới để bảo mật tài khoản!");
            }

            // ==============================================================
            // 2. XỬ LÝ CHUYỂN HƯỚNG THEO BỘ LỌC BẢO MẬT (FILTER)
            // ==============================================================
            String securityUri = (String) sessionUltil.get(req, "security-uri");
            if (securityUri != null) {
                sessionUltil.remove(req, "security-uri");
                resp.sendRedirect(securityUri);
                return;
            }

            // ==============================================================
            // 3. PHÂN LUỒNG ADMIN & KHÁCH (CONTROL FLOW)
            // ==============================================================
            if (u.getAdmin() == true) { // Ép kiểu nếu hàm getter trả về Boolean, hoặc dùng u.isAdmin()
                // Nếu là Admin -> Đưa thẳng vào Dashboard khu Quản trị
                resp.sendRedirect(req.getContextPath() + "/admin/video");
            } else {
                // Nếu là User thường -> Đưa ra Trang chủ xem phim
                resp.sendRedirect(req.getContextPath() + "/home");
            }
            return;
        
        } else {
            // ==============================================================
            // 4. XỬ LÝ KHI SAI MẬT KHẨU (BÁO LỖI VÀ DÙNG LAYOUT)
            // ==============================================================
            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!"); 
            req.setAttribute("view", "/views/user/login.jsp");
            req.getRequestDispatcher("/views/user/layout.jsp").forward(req, resp);
        }
    }
}