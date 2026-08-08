package Servlet;

import dao.CategoryDAO;
import entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/category/index", "/category/create", "/category/edit", "/category/delete", "/category/update"})
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("index")) {
            List<Category> list = categoryDAO.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/views/category/index.jsp").forward(req, resp);

        } else if (uri.contains("create")) {
            try {
                Category category = new Category();
                category.setIdCate(req.getParameter("idCate"));
                category.setCatename(req.getParameter("catename"));
                category.setCatetype(req.getParameter("catetype"));

                categoryDAO.create(category);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/category/index");

        } else if (uri.contains("edit")) {
            String id = req.getParameter("id");
            Category category = categoryDAO.findById(id);
            req.setAttribute("category", category);

            List<Category> list = categoryDAO.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/views/category/index.jsp").forward(req, resp);

        } else if (uri.contains("update")) {
            try {
                Category category = new Category();
                category.setIdCate(req.getParameter("idCate"));
                category.setCatename(req.getParameter("catename"));
                category.setCatetype(req.getParameter("catetype"));

                categoryDAO.update(category);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/category/index");

        } else if (uri.contains("delete")) {
            String id = req.getParameter("id");
            try {
                categoryDAO.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/category/index");
        }
    }
}
