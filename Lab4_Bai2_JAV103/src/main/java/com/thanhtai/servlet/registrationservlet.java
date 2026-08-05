package com.thanhtai.servlet;

import com.thanhtai.model.user;
import com.thanhtai.dao.userdao;
import com.thanhtai.utils.validationutil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrationservlet")
public class registrationservlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. Thu thập dữ liệu
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String major = request.getParameter("major");

        // 2. Validate
        Map<String, String> errors = new HashMap<>();
        if (validationutil.isBlank(name)) {
            errors.put("name", "Họ tên không được để trống.");
        }
        if (!validationutil.isValidEmail(email)) {
            errors.put("email", "Email sai định dạng.");
        }

        // Đổ dữ liệu lại form để hiển thị
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("gender", gender);
        request.setAttribute("major", major);

        // 3. Xử lý logic
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else {
            // Đổ vào Model
            user newUser = new user(name, email, gender, major);
            userdao dao = new userdao();
            
            // Nhúng Database
            if (dao.insert(newUser)) {
                request.getRequestDispatcher("/views/result.jsp").forward(request, response);
            } else {
                errors.put("db", "Email này đã tồn tại trong hệ thống!");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            }
        }
    }
}