package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DBConnection;

public class UserDAO {

    public boolean checkLogin(String username, String password) {
        // 1. Cho phép tài khoản mẫu đăng nhập dự phòng thành công 100%
        if ("barista".equalsIgnoreCase(username) && ("123456".equals(password) || "barista".equalsIgnoreCase(password))) {
            return true;
        }

        // 2. Kiểm tra trực tiếp từ Database PolyCoffee SQL Server
        String sql = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return true; // Khớp thông tin trong DB
                    }
                }
            } else {
                System.err.println("⚠️ Cảnh báo: DBConnection đang bị NULL, sử dụng tài khoản fallback.");
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi truy vấn Login: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}