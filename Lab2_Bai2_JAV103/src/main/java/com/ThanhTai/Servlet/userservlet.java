package com.ThanhTai.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.ThanhTai.Model.user;

@WebServlet("/user.php")
public class userservlet extends HttpServlet {

    // HÀM 1: CHUYÊN XỬ LÝ LỆNH GET (Hiển thị giao diện khi vừa vào trang)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // Tạo dữ liệu giả lập đổ lên bảng
        List<user> users = Arrays.asList(
            new user("Username 1", "Password 1", true),
            new user("Username 2", "Password 2", false),
            new user("Username 3", "Password 3", true)
        );

        req.setAttribute("message", "Quản lý người sử dụng!");
        req.setAttribute("form", users.get(0)); 
        req.setAttribute("items", users);      

        req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
    }

    // HÀM 2: CHUYÊN XỬ LÝ LỆNH POST (Chạy khi người dùng bấm nút Create)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // Đọc dữ liệu người dùng gõ từ Form gửi lên
        String userForm = req.getParameter("username");
        String passForm = req.getParameter("password");
        String rememberForm = req.getParameter("remember"); 

        boolean isRemember = (rememberForm != null);
        user newUser = new user(userForm, passForm, isRemember);

        // Đổi thông báo để xác nhận đã bắt được dữ liệu
        req.setAttribute("message", "Bạn vừa bấm Create! Đã nhận tài khoản thành công.");
        req.setAttribute("form", newUser);

        // Giữ lại danh sách bảng để hiển thị kèm theo
        List<user> users = Arrays.asList(
            new user("Username 1", "Password 1", true),
            new user("Username 2", "Password 2", false),
            new user("Username 3", "Password 3", true)
        );
        req.setAttribute("items", users);

        req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
    }
}