package Servlet;

import dao.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/user/index", "/user/create", "/user/edit", "/user/delete", "/user/update"})
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("index")) {
            List<User> list = userDAO.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);

        } else if (uri.contains("create")) {
            try {
                User user = new User();
                user.setIdUser(req.getParameter("idUser"));
                user.setUserName(req.getParameter("userName"));
                user.setUserPass(req.getParameter("userPass"));
                user.setUserPhone(req.getParameter("userPhone"));
                user.setUserEmail(req.getParameter("userEmail"));
                user.setRole(Integer.parseInt(req.getParameter("role")));
                user.setUserImg(req.getParameter("userImg"));
                user.setUserActive(true);

                userDAO.create(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/user/index");

        } else if (uri.contains("edit")) {
            String id = req.getParameter("id");
            User user = userDAO.findById(id);
            req.setAttribute("user", user);

            List<User> list = userDAO.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);

        } else if (uri.contains("update")) {
            try {
                User user = new User();
                user.setIdUser(req.getParameter("idUser"));
                user.setUserName(req.getParameter("userName"));
                user.setUserPass(req.getParameter("userPass"));
                user.setUserPhone(req.getParameter("userPhone"));
                user.setUserEmail(req.getParameter("userEmail"));
                user.setRole(Integer.parseInt(req.getParameter("role")));
                user.setUserImg(req.getParameter("userImg"));
                user.setUserActive(Boolean.parseBoolean(req.getParameter("userActive")));

                userDAO.update(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/user/index");

        } else if (uri.contains("delete")) {
            String id = req.getParameter("id");
            try {
                userDAO.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/user/index");
        }
    }
}
