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

@WebServlet("/quen_mat_khau")
public class QuenMatKhauServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/User/quen-mat-khau.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accountInfo = request.getParameter("account");
        UserDAO dao = new UserDAO();

        // Tìm theo Email trước, nếu không thấy thì tìm theo Username
        Users user = dao.findByEmail(accountInfo);
        if (user == null) {
            user = dao.FindByID(accountInfo);
        }

        if (user == null) {
            request.setAttribute("error", "Không tìm thấy tài khoản hoặc Email trong hệ thống!");
            request.getRequestDispatcher("/User/quen-mat-khau.jsp").forward(request, response);
            return;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            request.setAttribute("error", "Tài khoản này chưa cài đặt Email!");
            request.getRequestDispatcher("/User/quen-mat-khau.jsp").forward(request, response);
            return;
        }

        final Users targetUser = user;
        new Thread(() -> {
            EmailUtil.sendForgotPasswordEmail(targetUser.getEmail(), targetUser.getPassword());
        }).start();

        request.setAttribute("message", "Mật khẩu đã được gửi về email: " + user.getEmail());
        request.getRequestDispatcher("/User/quen-mat-khau.jsp").forward(request, response);
    }
}