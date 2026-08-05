package com.ThanhTai.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.ThanhTai.Model.item;

public class detailservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thiết lập bộ mã hóa xử lý tiếng Việt có dấu
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // Khởi tạo đối tượng sản phẩm theo mẫu đề bài
        item prod = new item("Nokia 2020", "nokia.png", 500.0, 0.1);
        
        // Đẩy đối tượng lên request scope để chuyển tiếp
        req.setAttribute("item", prod);
        
        // Điều hướng sang trang hiển thị chi tiết
        req.getRequestDispatcher("/views/item/detail.jsp").forward(req, resp);
    }
}