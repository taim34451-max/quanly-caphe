package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Order;
import model.OrderItem;
import utils.DBConnection;

public class OrderDAO {

    // 1. Lấy thống kê số lượng đơn theo từng trạng thái từ Database
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

    // 2. Lấy danh sách đơn hàng theo trạng thái trực tiếp từ Database SQL Server
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
            System.err.println("❌ LỖI LẤY DANH SÁCH ĐƠN HÀNG TỪ DATABASE: " + e.getMessage());
            e.printStackTrace();
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

    // 4. Cập nhật Trạng thái & Ghi chú Đơn hàng trực tiếp vào Database SQL Server
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
            System.err.println("❌ CẬP NHẬT TRẠNG THÁI ĐƠN HÀNG TRÊN DATABASE THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }

        return updatedInDB;
    }

    public boolean UpdateOrderStatus(int id, String status, String note) {
        return updateOrderStatus(id, status, note);
    }

    // Helper: Lấy danh sách các món trong đơn hàng trực tiếp từ CSDL
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
            System.err.println("❌ LỖI LẤY DỮ LIỆU BILL DETAIL TỪ DATABASE: " + e.getMessage());
            e.printStackTrace();
        }
        return items;
    }
}