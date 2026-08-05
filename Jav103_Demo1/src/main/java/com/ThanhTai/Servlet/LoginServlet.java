package com.ThanhTai.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    // Xử lý khi người dùng mới gõ URL hoặc bị điều hướng về
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/login.jsp").forward(request, response);
    }

    // Xử lý khi người dùng bấm nút Submit Form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Cấu hình UTF-8 để không lỗi font tiếng Việt
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 2. Lấy dữ liệu từ JSP
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String gender = request.getParameter("gender");
        String city = request.getParameter("city");

        // (Tùy chọn) In ra Console để theo dõi
        System.out.println("Tài khoản: " + user + " | Mật khẩu: " + pass);
        System.out.println("Giới tính: " + gender + " | Thành phố: " + city);

        // 3. Logic kiểm tra đăng nhập
        if ("admin".equals(user) && "123".equals(pass)) {
            // Đăng nhập thành công -> đi tới trang chủ
            request.getRequestDispatcher("views/index.jsp").forward(request, response);
        } else {
            // Đăng nhập thất bại -> gắn lỗi và trả về lại trang login
            request.setAttribute("message", "Đăng nhập thất bại. Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
}