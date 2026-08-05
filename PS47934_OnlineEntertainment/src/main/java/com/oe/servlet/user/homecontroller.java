package com.oe.servlet.user;

import java.io.IOException;
import java.util.List;

import com.oe.dao.videoDAO;
import com.oe.entity.video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Bắt cả 2 đường dẫn /home và /index
@WebServlet({"/home", "/index"})
public class homecontroller extends HttpServlet {
    
    // Gọi DAO 1 lần duy nhất trên này để dùng chung
    private videoDAO dao = new videoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        int page = 1; // Mặc định là trang 1
        int pageSize = 6; // Yêu cầu 6 video / trang
        
        // Lấy số trang từ URL (nếu người dùng có bấm nút chuyển trang)
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }
        
        // Gọi hàm phân trang
        List<video> list = dao.findAll(page, pageSize);
        
        // Tính toán tổng số trang
        long totalVideos = dao.countActiveVideos();
        int totalPages = (int) Math.ceil((double) totalVideos / pageSize);
        
        // Gửi dữ liệu Video và Phân trang xuống JSP
        req.setAttribute("videos", list);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        
        // =========================================================
        // [QUAN TRỌNG NHẤT] CHUẨN HÓA LUỒNG ĐẨY RA LAYOUT
        // =========================================================
        
        // 1. Chỉ định "cái ruột" là trang home.jsp
        req.setAttribute("view", "/views/user/home.jsp");
        
        // 2. Đẩy "cái ruột" đó vào "bộ khung" layout.jsp để xuất hiện giao diện Luxury
        req.getRequestDispatcher("/views/user/layout.jsp").forward(req, resp);
    }
}