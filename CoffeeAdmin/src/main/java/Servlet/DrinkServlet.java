package Servlet;

import dao.CategoryDAO;
import dao.DrinkDAO;
import entity.Category;
import entity.Drink;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet({"/drink/index", "/drink/create", "/drink/edit", "/drink/delete", "/drink/update"})
public class DrinkServlet extends HttpServlet {
    private DrinkDAO drinkDAO = new DrinkDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        if (uri.contains("index")) {
            List<Drink> list = drinkDAO.findAll();
            List<Category> categories = categoryDAO.findAll();
            req.setAttribute("list", list);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/drink/index.jsp").forward(req, resp);
            
        } else if (uri.contains("create")) {
            try {
                Drink drink = new Drink();
                drink.setIdDrink(req.getParameter("idDrink"));
                drink.setDrinkName(req.getParameter("drinkName"));
                drink.setDrinkPrice(new BigDecimal(req.getParameter("drinkPrice")));
                drink.setDrinkIMG(req.getParameter("drinkIMG"));
                drink.setDrinkActive(true);
                drink.setDrinkDescription(req.getParameter("drinkDescription"));
                
                Category category = categoryDAO.findById(req.getParameter("idCate"));
                drink.setCategory(category);
                
                drinkDAO.create(drink);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/drink/index");
            
        } else if (uri.contains("edit")) {
            String id = req.getParameter("id");
            Drink drink = drinkDAO.findById(id);
            req.setAttribute("drink", drink);
            
            List<Drink> list = drinkDAO.findAll();
            List<Category> categories = categoryDAO.findAll();
            req.setAttribute("list", list);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/drink/index.jsp").forward(req, resp);
            
        } else if (uri.contains("update")) {
            try {
                Drink drink = new Drink();
                drink.setIdDrink(req.getParameter("idDrink"));
                drink.setDrinkName(req.getParameter("drinkName"));
                drink.setDrinkPrice(new BigDecimal(req.getParameter("drinkPrice")));
                drink.setDrinkIMG(req.getParameter("drinkIMG"));
                drink.setDrinkActive(Boolean.parseBoolean(req.getParameter("drinkActive")));
                drink.setDrinkDescription(req.getParameter("drinkDescription"));
                
                Category category = categoryDAO.findById(req.getParameter("idCate"));
                drink.setCategory(category);
                
                drinkDAO.update(drink);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/drink/index");
            
        } else if (uri.contains("delete")) {
            String id = req.getParameter("id");
            try {
                drinkDAO.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/drink/index");
        }
    }
}
