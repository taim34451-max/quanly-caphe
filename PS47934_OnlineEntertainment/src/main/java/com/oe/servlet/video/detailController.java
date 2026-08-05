package com.oe.servlet.video;

import java.io.IOException;

import com.oe.dao.videoDAO; // Đảm bảo bạn import đúng DAO của mình
import com.oe.entity.video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail") // Đây là đường dẫn mà trình duyệt sẽ gọi tới
public class detailController extends HttpServlet {
    
    private videoDAO dao = new videoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lấy ID video từ URL (Ví dụ: /detail?id=abc)
        String videoId = req.getParameter("id");
        
        if (videoId != null) {
            // 2. Gọi DAO lấy thông tin video từ SQL Server
            video video = dao.findById(videoId);
            
            // 3. Truyền dữ liệu sang trang JSP
            req.setAttribute("video", video);
            
            // 4. Forward tới giao diện chi tiết
            req.getRequestDispatcher("/views/video/detail.jsp").forward(req, resp);
        } else {
            // Nếu không có ID thì đẩy về trang chủ
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}