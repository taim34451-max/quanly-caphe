package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import DAO.DrinkDAO;
import Entity.Product;


@WebServlet("/DrinkServlet")
public class DrinkServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public DrinkServlet() {
        super();
    }
    // =====================================================
    // DO GET
    // =====================================================
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        DrinkDAO dao = new DrinkDAO();
        // =====================================================
        // CREATE FORM
        // /DrinkServlet?action=add
        // =====================================================
        if ("add".equals(action)) {
            request.setAttribute("edit", false);
            request.getRequestDispatcher(
                    "/Admin/DrinkForm.jsp"
            ).forward(request, response);
            return;
        }
        // =====================================================
        // UPDATE FORM
        // /DrinkServlet?action=update&id=1
        // =====================================================
        if ("update".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                Product drink =
                        dao.FindByID(Integer.valueOf(id));
                request.setAttribute("drink", drink);
                request.setAttribute("edit", true);
            }
            request.getRequestDispatcher(
                    "/Admin/DrinkForm.jsp"
            ).forward(request, response);
            return;
        }
        // =====================================================
        // DELETE
        // /DrinkServlet?action=delete&id=1
        // =====================================================
        if ("delete".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                dao.DeleteProduct(
                        Integer.valueOf(id)
                );
            }
            response.sendRedirect(
                    request.getContextPath()
                    + "/DrinkServlet"
            );
            return;
        }
        // =====================================================
        // LIST
        // =====================================================

        List<Product> list = dao.findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher(
                "/Admin/DrinkList.jsp"
        ).forward(request, response);
    }
    // =====================================================
    // DO POST
    // =====================================================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        DrinkDAO dao = new DrinkDAO();
        // =====================================================
        // CREATE
        // =====================================================
        if ("create".equals(action)) {
            Product drink = new Product();
            // Tên món
            drink.setProductName(
                    request.getParameter("drinkName")
            );
            // Giá
            String price =
                    request.getParameter("drinkPrice");
            if (price != null && !price.isEmpty()) {

               
            }
            // Category
            drink.setCategory(
                    request.getParameter("category")
            );
            // Hình ảnh
            drink.setProductIMG(
                    request.getParameter("drinkIMG")
            );
            // Trạng thái
            drink.setIsAvailable(
                    request.getParameter("drinkActive")
                    != null
            );
            // Lưu
            dao.CreateProduct(drink);
            // Quay về danh sách
            response.sendRedirect(
                    request.getContextPath()
                    + "/DrinkServlet"
            );

            return;
        }
        // =====================================================
        // UPDATE
        // =====================================================

        if ("update".equals(action)) {
            String id =
                    request.getParameter("idDrink");
            if (id != null && !id.isEmpty()) {
                Product drink =
                        dao.FindByID(Integer.valueOf(id));
                if (drink != null) {
                    // Tên món
                    drink.setProductName( request.getParameter("drinkName"));
                    // Giá
                    String price =
                            request.getParameter("drinkPrice");

                    if (price != null
                            && !price.isEmpty()) {

                        drink.setPrice((BigDecimal.valueOf(Float.parseFloat(price))));
                    }
                    // Category
                    drink.setCategory(
                            request.getParameter("category")
                    );
                    // Hình ảnh
                    drink.setProductIMG(request.getParameter("drinkIMG"));
                    // Trạng thái
                    drink.setIsAvailable(
                            request.getParameter("drinkActive")
                            != null
                    );
                    // Update
                    dao.UpdateProduct(drink);
                }
            }
            // Quay về danh sách
            response.sendRedirect(
                    request.getContextPath()
                    + "/DrinkServlet"
            );
            return;
        }
    }
}