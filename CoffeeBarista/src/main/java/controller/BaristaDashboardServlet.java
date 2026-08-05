package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;

@WebServlet(urlPatterns = "/barista/dashboard")
public class BaristaDashboardServlet extends HttpServlet {
    private final OrderDAO orderDAO = new OrderDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Map<String, Integer> counts = orderDAO.getStatusCounts();
        request.setAttribute("counts", counts);
        
        List<Order> allOrders = orderDAO.getOrdersByStatus("ALL");
        
        List<Order> pendingOrders = allOrders.stream()
                .filter(o -> "PENDING".equalsIgnoreCase(o.getStatus()))
                .collect(Collectors.toList());
                
        List<Order> makingOrders = allOrders.stream()
                .filter(o -> "MAKING".equalsIgnoreCase(o.getStatus()))
                .collect(Collectors.toList());
        request.setAttribute("pendingOrders", pendingOrders);
        request.setAttribute("makingOrders", makingOrders);
       
        request.getRequestDispatcher("/views/barista/dashboard.jsp").forward(request, response);
    }
}