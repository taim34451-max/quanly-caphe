package com.ThanhTai.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ThanhTai.Model.item; // Import class item

@WebServlet("/detail.php")
public class detailservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        item sp = new item("Nokia 2020", "nokia.png", 500, 0.1);
        
        req.setAttribute("item", sp);
        req.getRequestDispatcher("/views/item/detail.jsp").forward(req, resp);
    }
}