//package Ulti;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class DBConnection {
//
//    
//    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS01;databaseName=PolyCoffee;encrypt=false;trustServerCertificate=true;";
//
//    private static final String USER = "sa";
//    private static final String PASSWORD = "123456"; 
//
//    public static Connection getConnection() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            return DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (Exception e) {
//            System.err.println("❌ LỖI KẾT NỐI DATABASE POLYCOFFEE: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // HÀM MAIN TEST TRỰC TIẾP TRONG ECLIPSE
//    public static void main(String[] args) {
//        System.out.println("🔄 Đang kiểm tra kết nối tới SQL Server...");
//        try (Connection conn = getConnection()) {
//            if (conn != null) {
//                System.out.println("✅ KẾT NỐI CSDL THÀNH CÔNG!");
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery("SELECT count(*) FROM Bill WHERE status = 'PENDING'");
//                if (rs.next()) {
//                    System.out.println("📊 Số lượng đơn PENDING trong SQL Server hiện tại = " + rs.getInt(1));
//                }
//            } else {
//                System.out.println("❌ KHÔNG KẾT NỐI ĐƯỢC CSDL!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}