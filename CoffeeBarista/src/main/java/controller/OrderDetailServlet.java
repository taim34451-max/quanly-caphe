package controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;

@WebServlet(urlPatterns = "/barista/order-detail")
public class OrderDetailServlet extends HttpServlet {
    
    private final OrderDAO orderDAO = new OrderDAO();
    
    // Bộ nhớ tạm trên RAM để quản lý thời gian bắt đầu của từng đơn hàng độc lập
    private static final Map<Integer, Long> orderStartTimes = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr.trim());
            Order order = orderDAO.getOrderById(id);
            
            if (order == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng #" + id);
                return;
            }
            
         // --- LOGIC TÍNH TOÁN THỜI GIAN (ĐÃ REFACTOR) ---
            long remainingSeconds = 600; // Mặc định 10 phút
            String currentStatus = order.getStatus();

            // Chỉ bắt đầu bấm giờ khi đơn hàng chuyển sang trạng thái "Đang Pha" (MAKING)
            if (currentStatus != null && currentStatus.equalsIgnoreCase("MAKING")) {
                
                if (!orderStartTimes.containsKey(id)) {
                    orderStartTimes.put(id, System.currentTimeMillis());
                }

                long startTime = orderStartTimes.get(id);
                long elapsedTime = System.currentTimeMillis() - startTime;
                long remainingTimeMs = (10 * 60 * 1000) - elapsedTime;
                
                if (remainingTimeMs < 0) {
                    remainingTimeMs = 0;
                }
                remainingSeconds = remainingTimeMs / 1000;
                
            } else if (currentStatus != null && (currentStatus.equalsIgnoreCase("COMPLETED") || currentStatus.equalsIgnoreCase("CANCELLED"))) {
                // Nếu đã hoàn thành hoặc hủy đơn thì thời gian = 0, đồng thời dọn dẹp RAM
                remainingSeconds = 0;
                orderStartTimes.remove(id);
            }

            request.setAttribute("remainingSeconds", remainingSeconds);
            // --- KẾT THÚC LOGIC THỜI GIAN ---

            // Đẩy các dữ liệu gốc của bạn xuống JSP
            request.setAttribute("order", order);
            
            Map<String, Integer> counts = orderDAO.getStatusCounts();
            request.setAttribute("counts", counts);
            
            request.getRequestDispatcher("/views/barista/order-detail.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/barista/dashboard");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Lấy dữ liệu từ Form (JSP) gửi lên
        String idStr = request.getParameter("id");
        String newStatus = request.getParameter("status");
        String note = request.getParameter("note"); // Lấy thêm ghi chú (nếu Form của bạn có ô nhập ghi chú)
        
        // Kiểm tra an toàn: Nếu Form gửi thiếu ID thì chặn lại ngay
        if (idStr == null || idStr.trim().isEmpty()) {
            System.out.println("LỖI: Form JSP chưa gửi thuộc tính name='id' về Server!");
            response.sendRedirect(request.getContextPath() + "/barista/dashboard");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr.trim());
            
            // --- LOGIC CẬP NHẬT DATABASE ---
            // Gọi đúng tên hàm và truyền đủ 3 tham số theo cấu trúc của orderdao.java
            boolean isUpdated = orderDAO.updateOrderStatus(id, newStatus, note); 
            // ---------------------------------------

            if (isUpdated) {
                // 2. Kiểm tra: Nếu trạng thái là "Hoàn Thành", "COMPLETED", "Hủy Đơn" hoặc "CANCELLED" thì dọn dẹp RAM
                // (Mình thêm các mã tiếng Anh vì thấy trong DAO bạn dùng PENDING, MAKING, COMPLETED, CANCELLED)
                if ("Hoàn Thành".equalsIgnoreCase(newStatus) || "COMPLETED".equalsIgnoreCase(newStatus) || 
                    "Hủy Đơn".equalsIgnoreCase(newStatus) || "CANCELLED".equalsIgnoreCase(newStatus)) {
                    
                    // XÓA ID KHỎI BỘ NHỚ TẠM
                    orderStartTimes.remove(id); 
                }
            }
            
            // 3. Xử lý xong thì điều hướng (redirect) về lại trang chi tiết để xem kết quả
            response.sendRedirect(request.getContextPath() + "/barista/order-detail?id=" + id);
            
        } catch (NumberFormatException e) {
            System.out.println("LỖI ÉP KIỂU ID TRONG DOPOST: " + idStr);
            response.sendRedirect(request.getContextPath() + "/barista/dashboard");
        }
    
    }
}