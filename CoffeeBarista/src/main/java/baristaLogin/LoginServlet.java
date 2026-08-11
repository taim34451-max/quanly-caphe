package baristaLogin;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 🔑 Đã thêm "" và "/" để tự động nhận diện trang chủ
@WebServlet(urlPatterns = {"", "/", "/login", "/logout"})
public class LoginServlet extends HttpServlet {
    
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getServletPath();
        
        if ("/logout".equals(path)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        // Chuyển thẳng tới giao diện loginBarista.jsp
        request.getRequestDispatcher("/views/barista/loginBarista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       
        // Kiểm tra Tài khoản & Mật khẩu từ Database
        if (username != null && password != null && userDAO.checkLogin(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", username);
            response.sendRedirect(request.getContextPath() + "/barista/dashboard");
        } else {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/views/barista/loginBarista.jsp").forward(request, response);
        }
    }
}