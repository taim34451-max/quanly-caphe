package com.oe.servlet.user;

import java.io.IOException;
import java.util.List;

import com.oe.dao.favoriteDAO;
import com.oe.entity.favorite;
import com.oe.entity.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/favorite")
public class favoritecontroller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. Lấy thông tin tài khoản đang đăng nhập từ Session
        user currentUser = (user) request.getSession().getAttribute("user");
        
        // 2. Nếu đã đăng nhập thì lấy danh sách favorite
        if (currentUser != null) {
            favoriteDAO dao = new favoriteDAO();
            List<favorite> list = dao.findByUserId(currentUser.getId());
            request.setAttribute("favorites", list);
        }
        
        // =========================================================
        // SỬA Ở ĐÂY: ĐẨY VÀO LAYOUT THAY VÌ ĐẨY VÀO TRANG FAV TRỰC TIẾP
        // =========================================================
        
        // 1. Chỉ định "cái ruột" là trang favorite.jsp
        request.setAttribute("view", "/views/user/favorite.jsp");
        
        // 2. Forward vào layout chung
        request.getRequestDispatcher("/views/user/layout.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}