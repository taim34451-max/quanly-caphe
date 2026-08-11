package Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import DAO.BillDAO;
import Entity.Bill;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/barista/orders/*")
public class OrderListServlet extends HttpServlet {
    private final BillDAO orderDAO = new BillDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String status = request.getParameter("status");
        if (status == null || status.trim().isEmpty()) {
            status = "ALL";
        }
        status = status.toUpperCase().trim();
       
        List<Bill> orders = orderDAO.getOrdersByStatus(status);
        request.setAttribute("orders", orders);
        request.setAttribute("currentStatus", status);
       
        Map<String, Integer> counts = orderDAO.getStatusCounts();
        request.setAttribute("counts", counts);
       
        request.getRequestDispatcher("/barista/order-list.jsp").forward(request, response);
    }
}