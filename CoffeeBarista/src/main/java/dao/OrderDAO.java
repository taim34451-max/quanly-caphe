package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Order;
import model.OrderItem;
import utils.DBConnection;

public class OrderDAO {

    // Danh sách lưu tạm bộ nhớ nếu chưa nối được Database SQL Server
    private static final List<Order> mockOrders = new ArrayList<>();

    static {
        // Khởi tạo đơn mẫu thử nghiệm nếu chưa kết nối được DB
        Order sampleOrder = new Order(3, "Khách Bàn 06", "Bàn 06", "PENDING", 85000.0, new Date());
        sampleOrder.setNote("Giao gấp cho bànVIP");
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(1, 3, "Cà Phê Sữa Đá - Ít đường", 2, 35000.0, "Ít đá"));
        items.add(new OrderItem(2, 3, "Bánh Croissant", 1, 15000.0, "Nóng"));
        sampleOrder.setItems(items);
        mockOrders.add(sampleOrder);
    }

    // 1. Lấy thống kê số lượng đơn theo từng trạng thái
    public Map<String, Integer> getStatusCounts() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("PENDING", 0);
        counts.put("MAKING", 0);
        counts.put("COMPLETED", 0);
        counts.put("CANCELLED", 0);

        List<Order> list = getOrdersByStatus("ALL");
        for (Order o : list) {
            if (o.getStatus() != null) {
                String st = o.getStatus().toUpperCase();
                counts.put(st, counts.getOrDefault(st, 0) + 1);
            }
        }
        return counts;
    }

    // 2. Lấy danh sách đơn hàng theo trạng thái (Hỗ trợ đọc DB thật & Fallback dữ liệu mẫu)
    public List<Order> getOrdersByStatus(String statusFilter) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Bill ";
        if (statusFilter != null && !statusFilter.trim().isEmpty() && !"ALL".equalsIgnoreCase(statusFilter)) {
            sql += "WHERE status = ? ";
        }
        sql += "ORDER BY CreatedDate DESC";

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    if (statusFilter != null && !statusFilter.trim().isEmpty() && !"ALL".equalsIgnoreCase(statusFilter)) {
                        stmt.setString(1, statusFilter.toUpperCase());
                    }

                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        Order order = new Order(
                            rs.getInt("BillId"),
                            "Khách " + rs.getString("TableNumber"),
                            rs.getString("TableNumber"),
                            rs.getString("Status"),
                            rs.getDouble("Total"),
                            rs.getTimestamp("CreatedDate")
                        );
                        order.setNote(rs.getString("Note"));
                        order.setItems(getOrderItems(conn, order.getId()));
                        orders.add(order);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("⚠️ Chưa đọc được DB thật, dùng dữ liệu mẫu: " + e.getMessage());
        }

        // Nếu DB rỗng hoặc chưa nối được, trả về danh sách mẫu để hiển thị ngay
        if (orders.isEmpty()) {
            for (Order mo : mockOrders) {
                if (statusFilter == null || "ALL".equalsIgnoreCase(statusFilter) || statusFilter.equalsIgnoreCase(mo.getStatus())) {
                    orders.add(mo);
                }
            }
        }
        return orders;
    }

    // 3. Lấy chi tiết đơn hàng theo ID
    public Order getOrderById(int orderId) {
        List<Order> all = getOrdersByStatus("ALL");
        for (Order o : all) {
            if (o.getId() == orderId) {
                return o;
            }
        }
        return null;
    }

    // 4. Cập nhật Trạng thái & Ghi chú Đơn hàng
    public boolean updateOrderStatus(int billId, String status, String note) {
        String sql = "UPDATE Bill SET Status = ?, Note = ? WHERE BillId = ?";
        boolean updatedInDB = false;
        
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, status.toUpperCase());
                    stmt.setString(2, note != null ? note : "");
                    stmt.setInt(3, billId);
                    updatedInDB = stmt.executeUpdate() > 0;
                }
            }
        } catch (Exception e) {
            System.err.println("⚠️ Cập nhật DB thất bại, cập nhật vào dữ liệu mẫu: " + e.getMessage());
        }

        // Cập nhật cả trong danh sách tạm thời
        for (Order mo : mockOrders) {
            if (mo.getId() == billId) {
                mo.setStatus(status.toUpperCase());
                mo.setNote(note);
                updatedInDB = true;
            }
        }
        return updatedInDB;
    }

    public boolean UpdateOrderStatus(int id, String status, String note) {
        return updateOrderStatus(id, status, note);
    }

    // Helper: Lấy danh sách các món trong đơn hàng từ DB
    private List<OrderItem> getOrderItems(Connection conn, int billId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT bd.*, p.ProductName FROM BillDetail bd " +
                     "LEFT JOIN Product p ON bd.ProductId = p.ProductId WHERE bd.BillId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("ProductName");
                if (name == null) name = "Món ăn/uống";
                items.add(new OrderItem(
                    rs.getInt("DetailId"),
                    rs.getInt("BillId"),
                    name,
                    rs.getInt("Quantity"),
                    rs.getDouble("Price"),
                    rs.getString("Note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}