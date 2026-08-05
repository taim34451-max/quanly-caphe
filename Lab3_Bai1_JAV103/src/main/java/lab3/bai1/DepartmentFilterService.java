package lab3.bai1;
 
import java.sql.*;
 
/**
 * DepartmentFilterService
 * Chức năng: Lọc và hiển thị các phòng ban có số nhân viên >= minCount.
 * Ví dụ theo đề bài: hiển thị các phòng ban có số nhân viên >= 2.
 */
public class DepartmentFilterService {
 
    private static final String SQL =
        "SELECT d.id, d.name, COUNT(e.id) AS emp_count "
      + "FROM departments d "
      + "JOIN employees e ON e.department_id = d.id "
      + "GROUP BY d.id, d.name "
      + "HAVING COUNT(e.id) >= ? "
      + "ORDER BY emp_count DESC";
 
    /**
     * In ra console các phòng ban có số nhân viên >= minCount.
     *
     * @param minCount ngưỡng tối thiểu số nhân viên (>= 1)
     */
    public void showDepartmentsWithMinEmployees(int minCount) {
        // Validation: ngưỡng phải >= 1
        if (minCount < 1) {
            System.err.println("[Validation] minCount phải >= 1, nhận: " + minCount);
            return;
        }
 
        System.out.println("\n===== PHÒNG BAN CÓ TỪ " + minCount + " NHÂN VIÊN TRỞ LÊN =====");
        System.out.printf("%-5s %-25s %-10s%n", "ID", "Tên Phòng Ban", "Số NV");
        System.out.println("-".repeat(42));
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
 
            ps.setInt(1, minCount);
 
            try (ResultSet rs = ps.executeQuery()) {
                int rowCount = 0;
                while (rs.next()) {
                    System.out.printf("%-5d %-25s %-10d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("emp_count"));
                    rowCount++;
                }
 
                if (rowCount == 0) {
                    System.out.println("(Không có phòng ban nào thỏa điều kiện)");
                }
            }
 
        } catch (SQLException e) {
            System.err.println("[LỖI] DepartmentFilterService: " + e.getMessage());
        }
    }
}
 