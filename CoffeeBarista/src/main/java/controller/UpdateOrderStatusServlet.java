package controller;

import java.io.IOException;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/barista/update-status")
public class UpdateOrderStatusServlet extends HttpServlet {
    private final OrderDAO orderDAO = new OrderDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        String status = request.getParameter("status");
        String note = request.getParameter("note");
        if (idStr == null || status == null) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard");
            return;
        }
        try {
            int id = Integer.parseInt(idStr.trim());
            
            if (note == null) {
                note = "";
            }
                                                                                       
            boolean success = orderDAO.UpdateOrderStatus(id, status, note);
            
            if (success) {
                
                if ("MAKING".equalsIgnoreCase(status)) {
                	
                    response.sendRedirect(request.getContextPath() + "/barista/order-detail?id=" + id);
                } else if ("COMPLETED".equalsIgnoreCase(status) || "CANCELLED".equalsIgnoreCase(status)) {
                    
                    response.sendRedirect(request.getContextPath() + "/barista/dashboard");
                } else {
                    
                    String referer = request.getHeader("referer");
                    if (referer != null && referer.contains("order-detail")) {
                        response.sendRedirect(request.getContextPath() + "/barista/order-detail?id=" + id);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/barista/dashboard");
                    }
                }
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể cập nhật trạng thái đơn hàng.");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard");
        }
    }
}