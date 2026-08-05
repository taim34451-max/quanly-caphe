package com.thanhtai.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.thanhtai.model.item;

@WebServlet("/list.php")
public class listservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // Sử dụng cấu trúc dữ liệu List để chứa danh sách sản phẩm
        List<item> productList = new ArrayList<>();
        
        // Mock data: Thêm 6 sản phẩm vào list (Giá 100, giảm giá 0.01 tức 1% -> Giá mới là 99)
        String defaultImg = "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=150";
        for (int i = 1; i <= 6; i++) {
            productList.add(new item("Tên sản phẩm " + i, defaultImg, 100.0, 0.01));
        }

        // Đẩy List này lên Request Scope với tên biến là "items"
        req.setAttribute("items", productList);

        // Điều hướng sang trang JSP
        req.getRequestDispatcher("/views/item/list.jsp").forward(req, resp);
    }
}