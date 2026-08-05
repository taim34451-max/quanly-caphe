package com.oe.servlet.admin;

import java.io.IOException;
import java.util.List;

import com.oe.dao.favoriteDAO;
import com.oe.dao.shareDAO;
import com.oe.dao.videoDAO;
import com.oe.entity.favorite;
import com.oe.entity.video;
import com.oe.ultils.jpaUltil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/report")
public class adminReportController extends HttpServlet {
    
    // Gọi cả 3 DAO lên để phục vụ cho 3 Tab thống kê
    private favoriteDAO fDao = new favoriteDAO();
    private shareDAO sDao = new shareDAO();
    private videoDAO vDao = new videoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<video> videos = vDao.findAll();
        req.setAttribute("videos", videos);

        // Mặc định tab hiển thị là tab 1
        String activeTab = "tab1";

        // ================= TAB 1: FAVORITES =================
        List<Object[]> favReports = fDao.reportFavoriteByVideo();
        req.setAttribute("favReports", favReports);

        // ================= TAB 2: FAVORITE USERS =================
        String videoUserId = req.getParameter("videoUserId");
        if (videoUserId == null && !videos.isEmpty()) {
            videoUserId = videos.get(0).getId();
        }
        
        if (videoUserId != null) {
            EntityManager em = jpaUltil.getEntityManager();
            try {
                String jpql = "SELECT f FROM favorite f WHERE f.video.id = :vid";
                TypedQuery<favorite> query = em.createQuery(jpql, favorite.class);
                query.setParameter("vid", videoUserId);
                req.setAttribute("favUsers", query.getResultList());
                req.setAttribute("videoUserId", videoUserId);
                
                // Nếu có tham số videoUserId, tự động kích hoạt tab 2
                activeTab = "tab2"; 
            } finally {
                em.close();
            }
        }

        // ================= TAB 3: SHARED FRIENDS =================
        String videoShareId = req.getParameter("videoShareId");
        if (videoShareId != null) {
            req.setAttribute("sharedFriends", sDao.reportShareByVideo(videoShareId));
            req.setAttribute("videoShareId", videoShareId);
            
            // Nếu có tham số videoShareId, tự động kích hoạt tab 3
            activeTab = "tab3";
        }

        // Đẩy tab cần mở sang view
        req.setAttribute("activeTab", activeTab);

        // Đẩy sang layout
        req.setAttribute("view", "/views/admin/report.jsp");
        req.getRequestDispatcher("/views/admin/adminLayout.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Khi người dùng bấm nút "Lọc" (Submit Form) trên giao diện, đẩy luồng xử lý về lại hàm doGet cho gọn
        doGet(req, resp);
    }
}