package Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DAO.BillDAO;
import Entity.Bill;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/barista/dashboard")
public class BaristaDashboardServlet extends HttpServlet {
    private final BillDAO orderDAO = new BillDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Map<String, Integer> counts = orderDAO.getStatusCounts();
        request.setAttribute("counts", counts);
        
        List<Bill> allOrders = orderDAO.getOrdersByStatus("ALL");
        
        List<Bill> pendingOrders = allOrders.stream()
                .filter(o -> "PENDING".equalsIgnoreCase(o.getStatus()))
                .collect(Collectors.toList());
                
        List<Bill> makingOrders = allOrders.stream()
                .filter(o -> "MAKING".equalsIgnoreCase(o.getStatus()))
                .collect(Collectors.toList());
        request.setAttribute("pendingOrders", pendingOrders);
        request.setAttribute("makingOrders", makingOrders);
       
        request.getRequestDispatcher("/barista/dashboard.jsp").forward(request, response);
    }
}