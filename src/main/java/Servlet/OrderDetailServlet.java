package Servlet;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import DAO.BillDAO;
import Entity.Bill;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/barista/order-detail")
public class OrderDetailServlet extends HttpServlet {
    
    private final BillDAO orderDAO = new BillDAO();
    
    // Bộ nhớ RAM lưu mốc thời gian bắt đầu khi bấm "Đang Pha"
    private static final Map<Integer, Long> orderStartTimes = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard.jsp");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr.trim());
            Bill order = orderDAO.getOrderById(id);
            
            if (order == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng #" + id);
                return;
            }
            
            // --- LOGIC TÍNH THỜI GIAN THEO TRẠNG THÁI ---
            long remainingSeconds = 600; // Mặc định 10 phút
            String currentStatus = order.getStatus();

            if (currentStatus != null && ("COMPLETED".equalsIgnoreCase(currentStatus) || "CANCELLED".equalsIgnoreCase(currentStatus)
                    || "Hoàn Thành".equalsIgnoreCase(currentStatus) || "Hủy Đơn".equalsIgnoreCase(currentStatus))) {
                
                remainingSeconds = 0;
                orderStartTimes.remove(id);
                
            } else if (currentStatus != null && ("MAKING".equalsIgnoreCase(currentStatus) || "Đang Pha".equalsIgnoreCase(currentStatus))) {
                
                // CHỈ KÍCH HOẠT BẤM GIỜ KHI LÀ "ĐANG PHA"
                orderStartTimes.putIfAbsent(id, System.currentTimeMillis());
                long startTime = orderStartTimes.get(id);

                long elapsedTime = System.currentTimeMillis() - startTime;
                long remainingTimeMs = (10 * 60 * 1000) - elapsedTime; // 10 phút
                
                if (remainingTimeMs > 0) {
                    remainingSeconds = remainingTimeMs / 1000;
                } else {
                    remainingSeconds = 0; // Hết giờ
                }
            } else {
                // Trạng thái PENDING (Đơn Mới) -> Giữ nguyên 10:00 (600 giây), chưa đếm ngược
                remainingSeconds = 600;
            }

            request.setAttribute("remainingSeconds", remainingSeconds);
            request.setAttribute("order", order);
            
            Map<String, Integer> counts = orderDAO.getStatusCounts();
            request.setAttribute("counts", counts);
            
            request.getRequestDispatcher("/barista/order-detail.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        String newStatus = request.getParameter("status");
        String note = request.getParameter("note");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard.jsp");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr.trim());
            
            // Cập nhật trạng thái vào CSDL
            boolean isUpdated = orderDAO.updateOrderStatus(id, newStatus, note); 

            if (isUpdated) {
                // Nếu bấm Hoàn Thành hoặc Hủy Đơn -> Quay về ngay Dashboard
                if ("COMPLETED".equalsIgnoreCase(newStatus) || "CANCELLED".equalsIgnoreCase(newStatus) ||
                    "Hoàn Thành".equalsIgnoreCase(newStatus) || "Hủy Đơn".equalsIgnoreCase(newStatus)) {
                    
                    orderStartTimes.remove(id); 
                    response.sendRedirect(request.getContextPath() + "/barista/dashboard.jsp");
                    return;
                }
            }
            
            // Nếu bấm Đang Pha hoặc Đổi Ghi Chú -> Ở lại trang chi tiết để xem đếm ngược
            response.sendRedirect(request.getContextPath() + "/barista/order-detail?id=" + id);
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard.jsp");
        }
    }
}