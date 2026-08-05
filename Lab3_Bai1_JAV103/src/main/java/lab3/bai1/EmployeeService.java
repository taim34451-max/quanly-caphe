package lab3.bai1;
 
import java.sql.*;
 
/**
 * EmployeeService
 * Chức năng: Truy vấn và hiển thị toàn bộ nhân viên.
 * Columns: ID | Tên | Lương | Tên phòng ban
 */
public class EmployeeService {
 
    private static final String SQL =
        "SELECT e.id, e.full_name, e.salary, d.name AS dept_name "
      + "FROM employees e "
      + "JOIN departments d ON e.department_id = d.id "
      + "ORDER BY e.id";
 
    /**
     * In ra console toàn bộ danh sách nhân viên kèm phòng ban.
     */
    public void showAllEmployees() {
        System.out.println("\n========== DANH SÁCH NHÂN VIÊN ==========");
        System.out.printf("%-5s %-25s %-18s %-15s%n",
                          "ID", "Họ Tên", "Lương (VNĐ)", "Phòng Ban");
        System.out.println("-".repeat(65));
 
        try (Connection conn = DBConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(SQL)) {
 
            int count = 0;
            while (rs.next()) {
                System.out.printf("%-5d %-25s %,-18.0f %-15s%n",
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getDouble("salary"),
                    rs.getString("dept_name"));
                count++;
            }
 
            System.out.println("-".repeat(65));
            System.out.println("Tổng: " + count + " nhân viên");
 
        } catch (SQLException e) {
            System.err.println("[LỖI] EmployeeService.showAllEmployees: " + e.getMessage());
        }
    }
}
