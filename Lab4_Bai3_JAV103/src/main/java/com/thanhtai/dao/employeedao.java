package com.thanhtai.dao;

import com.thanhtai.model.employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class employeedao {
	 private static final String JDBC_URL =
    		 "jdbc:sqlserver://TaiSmile\\SQLEXPRESS01;"
    				    + "databaseName=Lab4DB;"
    				    + "encrypt=false;"
    				    + "trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "123456";
 

    public employeedao() {
        try {
            // Đổi sang nạp Driver của SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    // Lấy toàn bộ danh sách
    public List<employee> getAll() {
        List<employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new employee(rs.getString("emp_code"), rs.getString("full_name"), rs.getString("email")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Lấy 1 nhân viên theo mã
    public employee getByCode(String code) {
        String sql = "SELECT * FROM employees WHERE emp_code = ?";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new employee(rs.getString("emp_code"), rs.getString("full_name"), rs.getString("email"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // Thêm nhân viên
    public boolean insert(employee emp) {
        String sql = "INSERT INTO employees (emp_code, full_name, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getEmpCode());
            ps.setString(2, emp.getFullName());
            ps.setString(3, emp.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }

    // Cập nhật (Không sửa mã)
    public boolean update(employee emp) {
        String sql = "UPDATE employees SET full_name = ?, email = ? WHERE emp_code = ?";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getFullName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getEmpCode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    // Xóa nhân viên
    public boolean delete(String code) {
        String sql = "DELETE FROM employees WHERE emp_code = ?";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}