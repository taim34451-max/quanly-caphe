package lab3.bai1;
 
import java.sql.*;
 
/**
 * DepartmentStatsService
 * Chức năng: Thống kê theo phòng ban.
 * Kết hợp dữ liệu giữa bảng employees và departments.
 * Hiển thị: Tên phòng ban | Số lượng nhân viên
 */
public class DepartmentStatsService {
 
    private static final String SQL =
        "SELECT d.name AS dept_name, COUNT(e.id) AS emp_count "
      + "FROM departments d "
      + "LEFT JOIN employees e ON e.department_id = d.id "
      + "GROUP BY d.name "
      + "ORDER BY emp_count DESC";
 
    /**
     * In ra console bảng thống kê nhân viên theo từng phòng ban.
     * Dùng LEFT JOIN để hiển thị cả phòng ban chưa có nhân viên.
     */
    public void showDepartmentStats() {
        System.out.println("\n========== THỐNG KÊ THEO PHÒNG BAN ==========");
        System.out.printf("%-25s %-15s%n", "Tên Phòng Ban", "Số Nhân Viên");
        System.out.println("-".repeat(42));
 
        try (Connection conn = DBConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(SQL)) {
 
            int totalEmp = 0;
            while (rs.next()) {
                int count = rs.getInt("emp_count");
                totalEmp += count;
                System.out.printf("%-25s %-15d%n",
                    rs.getString("dept_name"), count);
            }
 
            System.out.println("-".repeat(42));
            System.out.printf("%-25s %-15d%n", "TỔNG CỘNG", totalEmp);
 
        } catch (SQLException e) {
            System.err.println("[LỖI] DepartmentStatsService: " + e.getMessage());
        }
    }
}