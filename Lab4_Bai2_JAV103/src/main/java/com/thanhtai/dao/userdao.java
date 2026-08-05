package com.thanhtai.dao;

import com.thanhtai.model.user;
import java.sql.*;

public class userdao {
	 private static final String JDBC_URL =
    		 "jdbc:sqlserver://TaiSmile\\SQLEXPRESS01;"
    				    + "databaseName=Lab3DB;"
    				    + "encrypt=false;"
    				    + "trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "123456";
 
    public userdao() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Lỗi nạp Driver SQL Server!", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    public boolean insert(user u) {
        String sql = "INSERT INTO users (full_name, email, gender, major) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getGender());
            ps.setString(4, u.getMajor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}