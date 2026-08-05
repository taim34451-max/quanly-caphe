package com.oe.servlet.video;

import java.io.IOException;
import java.util.Date;

import com.oe.dao.favoriteDAO;
import com.oe.entity.favorite;
import com.oe.entity.user;
import com.oe.entity.video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/like")
public class likeController extends HttpServlet { 
    
    private favoriteDAO DAO = new favoriteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 1. Kiểm tra session người dùng
        user user = (user) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Kiểm tra tham số video ID
        String videoId = req.getParameter("id");
        if (videoId == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        try {
            // 3. KIỂM TRA LOGIC: Xem đã tồn tại lượt Like này trong DB chưa?
            favorite existFav = DAO.findByUserIdAndVideoId(user.getId(), videoId);
            
            if (existFav == null) {
                // Nếu chưa Like -> Tiến hành thêm mới
                favorite fav = new favorite();
                fav.setUser(user);
                
                video v = new video();
                v.setId(videoId); 
                fav.setVideo(v);
                
                fav.setLikeDate(new Date());
                DAO.create(fav); 
                
                req.getSession().setAttribute("message", "Đã thêm vào danh sách yêu thích!");
            } else {
                // Nếu đã Like rồi -> Báo nhẹ nhàng, không ném lỗi SQL
                req.getSession().setAttribute("message", "Bạn đã yêu thích video này rồi!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("error", "Hệ thống đang bận, vui lòng thử lại sau!");
        }

        // 4. Trở về trang chi tiết (hoặc trang chủ tùy logic luồng của bạn)
        resp.sendRedirect(req.getContextPath() + "/detail?id=" + videoId);
    }
}