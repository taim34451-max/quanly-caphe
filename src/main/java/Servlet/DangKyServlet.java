package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.UserDAO;
import Entity.Users;
import Ulti.EmailUtil;

@WebServlet("/dang_ky")
public class DangKyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/dang-ky.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();

        // Kiểm tra username đã tồn tại chưa
        if (dao.FindByID(username) != null) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("/dang-ky.jsp").forward(request, response);
            return;
        }

        Users user = new Users();
        user.setUserName(username);
        user.setUserPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER");

        try {
            dao.CreateUser(user);

            // Gửi mail chào mừng trong Thread riêng để giao diện không bị giật/chờ
            new Thread(() -> {
                EmailUtil.sendWelcomeEmail(email, username);
            }).start();

            request.setAttribute("message", "Đăng ký thành công! Email chào mừng đã được gửi.");
            request.getRequestDispatcher("/User/dang-nhap.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đăng ký thất bại! Vui lòng thử lại.");
            request.getRequestDispatcher("/dang-ky.jsp").forward(request, response);
        }
    }
}