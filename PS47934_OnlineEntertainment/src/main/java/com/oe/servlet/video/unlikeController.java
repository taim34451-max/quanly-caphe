package com.oe.servlet.video;

import java.io.IOException;

import com.oe.dao.favoriteDAO;
import com.oe.entity.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/unlike")
public class unlikeController extends HttpServlet { // Đã ép viết thường tên class
    private favoriteDAO dao = new favoriteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Kiểm tra đăng nhập
        user user = (user) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Lấy ID video cần unlike
        String videoId = req.getParameter("id");
        
        // 3. Thực hiện xóa trong database
        if (videoId != null) {
            try {
                dao.deleteByUserIdAndVideoId(user.getId(), videoId);
                // Đã đổi thành Session để thông báo không bị mất khi Redirect
                req.getSession().setAttribute("message", "Đã xóa khỏi danh sách yêu thích!");
            } catch (Exception e) {
                e.printStackTrace();
                req.getSession().setAttribute("error", "Lỗi khi bỏ yêu thích: " + e.getMessage());
            }
        }

        // 4. Tuyệt chiêu quay xe: Lấy lại link của trang cũ để trả người dùng về đúng chỗ
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer != null ? referer : req.getContextPath() + "/detail?id=" + videoId);
    }
}