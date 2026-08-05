package lab3.bai1;
 
/**
 * Main – Lab3_Bai1_JAV103
 *
 * Điều phối gọi từng Service theo từng chức năng:
 *   DBConnection          → quản lý kết nối SQL Server
 *   EmployeeService       → hiển thị toàn bộ nhân viên
 *   DepartmentFilterService → lọc phòng ban có NV >= ngưỡng
 *   DepartmentStatsService  → thống kê tên PB | số NV
 */
public class Main {
 
    public static void main(String[] args) {
 
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   Lab3_Bai1_JAV103 – JDBC + Statement        ║");
        System.out.println("║   SQL Server: .\\SQLEXPRESS01 | DB: Lab3DB    ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
 
        // 0. Kiểm tra kết nối trước khi chạy
        if (!DBConnection.testConnection()) {
            System.err.println("Không thể kết nối CSDL. Dừng chương trình.");
            return;
        }
 
        // 1. Hiển thị toàn bộ nhân viên (ID | Tên | Lương | Phòng ban)
        EmployeeService employeeService = new EmployeeService();
        employeeService.showAllEmployees();
 
        // 2. Lọc phòng ban có số nhân viên >= 2
        DepartmentFilterService filterService = new DepartmentFilterService();
        filterService.showDepartmentsWithMinEmployees(2);
 
        // 3. Thống kê: Tên phòng ban | Số lượng nhân viên
        DepartmentStatsService statsService = new DepartmentStatsService();
        statsService.showDepartmentStats();
 
        System.out.println("\n[DONE] Hoàn thành Lab3 Bài 1.");
    }
}