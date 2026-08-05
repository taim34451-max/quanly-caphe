package lab3.bai2;

import java.sql.*;
import java.time.LocalDate;

public class EmployeeDAO {
    private final String jdbcUrl;
    private final String dbUser;
    private final String dbPass;

    public EmployeeDAO(String jdbcUrl, String dbUser, String dbPass) {
        this.jdbcUrl = jdbcUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        try {
            // Đã đổi thành Driver SQL Server theo đúng pom.xml của bạn
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy SQL Server JDBC Driver!", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
    }

    // CREATE - Thêm mới nhân viên
    public boolean insert(Employee e) throws SQLException {
        String sql = "INSERT INTO employees(emp_code, full_name, email, phone, gender, birth_date, department, position, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, e.getEmpCode());
            ps.setString(2, e.getFullName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhone());
            ps.setString(5, e.getGender());
            ps.setDate(6, Date.valueOf(e.getBirthDate()));
            ps.setString(7, e.getDepartment());
            ps.setString(8, e.getPosition());
            ps.setBigDecimal(9, e.getSalary());
            
            return ps.executeUpdate() > 0;
        }
    }

    // READ - Danh sách & Tìm kiếm theo mã hoặc tên (LIKE)
    public void list(String search) throws SQLException {
        String sql = "SELECT emp_code, full_name, email, department, position, salary FROM employees";
        boolean useSearch = search != null && !search.trim().isEmpty();
        
        if (useSearch) {
            sql += " WHERE emp_code LIKE ? OR full_name LIKE ?";
        }

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (useSearch) {
                String q = "%" + search.trim() + "%";
                ps.setString(1, q);
                ps.setString(2, q);
            }

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("-".repeat(115));
                System.out.printf("%-10s | %-25s | %-25s | %-15s | %-15s | %-10s%n", "Code", "Full Name", "Email", "Department", "Position", "Salary");
                System.out.println("-".repeat(115));
                
                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    System.out.printf("%-10s | %-25s | %-25s | %-15s | %-15s | %-10.2f%n",
                            rs.getString("emp_code"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("department"),
                            rs.getString("position"),
                            rs.getBigDecimal("salary"));
                }
                if (!hasData) System.out.println("Không có dữ liệu nhân viên hiển thị.");
                System.out.println("-".repeat(115));
            }
        }
    }

    // READ - Xem chi tiết nhân viên theo mã
    public void detail(String code) throws SQLException {
        String sql = "SELECT * FROM employees WHERE emp_code = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n--- Chi Tiết Nhân Viên ---");
                    System.out.println("Mã NV: " + rs.getString("emp_code"));
                    System.out.println("Họ tên: " + rs.getString("full_name"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("SĐT: " + rs.getString("phone"));
                    System.out.println("Giới tính: " + rs.getString("gender"));
                    System.out.println("Ngày sinh: " + rs.getDate("birth_date"));
                    System.out.println("Phòng ban: " + rs.getString("department"));
                    System.out.println("Chức vụ: " + rs.getString("position"));
                    System.out.println("Lương: " + rs.getBigDecimal("salary"));
                } else {
                    System.out.println("Không tìm thấy mã nhân viên: " + code);
                }
            }
        }
    }

    // UPDATE - Cập nhật thông tin theo mã
    public boolean update(Employee e) throws SQLException {
        String sql = "UPDATE employees SET full_name=?, email=?, phone=?, gender=?, birth_date=?, department=?, position=?, salary=?, updated_at=GETDATE() WHERE emp_code=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getPhone());
            ps.setString(4, e.getGender());
            ps.setDate(5, Date.valueOf(e.getBirthDate()));
            ps.setString(6, e.getDepartment());
            ps.setString(7, e.getPosition());
            ps.setBigDecimal(8, e.getSalary());
            ps.setString(9, e.getEmpCode());
            
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE - Xóa nhân viên theo mã
    public boolean delete(String code) throws SQLException {
        String sql = "DELETE FROM employees WHERE emp_code = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, code);
            return ps.executeUpdate() > 0;
        }
    }
}