package lab3.bai1;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * DBConnection
 * Quản lý kết nối tới SQL Server.
 * Mọi class khác gọi DBConnection.getConnection() để lấy kết nối.
 */
public class DBConnection {
 
    private static final String JDBC_URL =
    		 "jdbc:sqlserver://TaiSmile\\SQLEXPRESS01;"
    				    + "databaseName=Lab3DB;"
    				    + "encrypt=false;"
    				    + "trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "123456";
 
    // Ngăn khởi tạo từ bên ngoài
    private DBConnection() {}
 
    /**
     * Trả về một Connection mới tới SQL Server.
     * Người gọi chịu trách nhiệm đóng Connection sau khi dùng.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }
 
    /**
     * Kiểm tra kết nối, in kết quả ra console.
     * @return true nếu kết nối thành công
     */
    public static boolean testConnection() {
        System.out.print("Đang kết nối SQL Server... ");
        try (Connection conn = getConnection()) {
            System.out.println("✔ Thành công! ("
                + conn.getMetaData().getDatabaseProductVersion() + ")");
            return true;
        } catch (SQLException e) {
            System.err.println("✘ Thất bại!\n" + e.getMessage());
            return false;
        }
    }
}