package lab3.bai2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    // Chuỗi kết nối chuẩn cho SQL Server. Bạn nhớ thay đổi "YourDatabaseName", "sa", "your_password" thành thông tin máy bạn nhé!
	 private static final String JDBC_URL =
    		 "jdbc:sqlserver://TaiSmile\\SQLEXPRESS01;"
    				    + "databaseName=Lab3DB;"
    				    + "encrypt=false;"
    				    + "trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "123456";
    private static final Scanner sc = new Scanner(System.in);
    private static final EmployeeDAO dao = new EmployeeDAO(JDBC_URL, DB_USER, DB_PASS);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Employee Menu (SQL Server Mode) ===");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Danh sách nhân viên (Tìm kiếm)");
            System.out.println("3. Xem chi tiết nhân viên");
            System.out.println("4. Cập nhật nhân viên");
            System.out.println("5. Xóa nhân viên");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": createEmployee(); break;
                    case "2": listEmployees(); break;
                    case "3": showDetail(); break;
                    case "4": updateEmployee(); break;
                    case "5": deleteEmployee(); break;
                    case "6": 
                        System.out.println("Đang thoát chương trình..."); 
                        sc.close();
                        return;
                    default: 
                        System.out.println("Lựa chọn sai, vui lòng chọn lại từ 1-6.");
                }
            } catch (Exception ex) {
                System.err.println("Đã xảy ra lỗi hệ thống: " + ex.getMessage());
            }
        }
    }

    private static void createEmployee() throws Exception {
        System.out.println("\n--- Nhập Thông Tin Thêm Mới ---");
        Employee e = new Employee();
        
        e.setEmpCode(inputRequired("Mã NV (Bắt buộc): "));
        e.setFullName(inputRequired("Họ tên (Bắt buộc): "));
        e.setEmail(inputEmail("Email (Bắt buộc, đúng định dạng): "));
        System.out.print("Số điện thoại: "); e.setPhone(sc.nextLine().trim());
        
        System.out.print("Giới tính (Male/Female/Other): ");
        String gender = sc.nextLine().trim();
        e.setGender(gender.isEmpty() ? "Other" : gender);
        
        e.setBirthDate(inputDate("Ngày sinh (YYYY-MM-DD): "));
        System.out.print("Phòng ban: "); e.setDepartment(sc.nextLine().trim());
        System.out.print("Chức vụ: "); e.setPosition(sc.nextLine().trim());
        e.setSalary(inputSalary("Mức lương (>= 0): "));

        boolean isSuccess = dao.insert(e);
        System.out.println(isSuccess ? "=> Thêm mới nhân viên thành công!" : "=> Thêm mới thất bại.");
    }

    private static void listEmployees() throws Exception {
        System.out.print("Nhập từ khóa tìm kiếm (Mã hoặc Tên), hoặc nhấn Enter để xem hết: ");
        String search = sc.nextLine().trim();
        dao.list(search);
    }

    private static void showDetail() throws Exception {
        String code = inputRequired("Nhập mã NV cần xem chi tiết: ");
        dao.detail(code);
    }

    private static void updateEmployee() throws Exception {
        System.out.println("\n--- Cập Nhật Thông Tin ---");
        String code = inputRequired("Nhập mã NV cần sửa thông tin: ");
        
        Employee e = new Employee();
        e.setEmpCode(code);
        e.setFullName(inputRequired("Họ tên mới: "));
        e.setEmail(inputEmail("Email mới: "));
        System.out.print("Số điện thoại mới: "); e.setPhone(sc.nextLine().trim());
        System.out.print("Giới tính mới (Male/Female/Other): ");
        String gender = sc.nextLine().trim();
        e.setGender(gender.isEmpty() ? "Other" : gender);
        e.setBirthDate(inputDate("Ngày sinh mới (YYYY-MM-DD): "));
        System.out.print("Phòng ban mới: "); e.setDepartment(sc.nextLine().trim());
        System.out.print("Chức vụ mới: "); e.setPosition(sc.nextLine().trim());
        e.setSalary(inputSalary("Mức lương mới: "));

        boolean isSuccess = dao.update(e);
        System.out.println(isSuccess ? "=> Cập nhật dữ liệu thành công!" : "=> Thao tác thất bại (Không tìm thấy mã nhân viên)!");
    }

    private static void deleteEmployee() throws Exception {
        String code = inputRequired("Nhập mã NV muốn xóa: ");
        System.out.print("Bạn có chắc chắn muốn xóa nhân viên này không? (Y/N): ");
        if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
            boolean isSuccess = dao.delete(code);
            System.out.println(isSuccess ? "=> Đã xóa nhân viên thành công!" : "=> Thất bại, mã nhân viên không tồn tại.");
        } else {
            System.out.println("Đã hủy lệnh xóa.");
        }
    }

    // --- BỘ CÔNG CỤ KIỂM TRA DỮ LIỆU ĐẦU VÀO (VALIDATION) ---

    private static String inputRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (!text.isEmpty()) return text;
            System.out.println("Thông báo lỗi: Trường này bắt buộc nhập, không được bỏ trống!");
        }
    }

    private static String inputEmail(String prompt) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        while (true) {
            String email = inputRequired(prompt);
            if (email.matches(emailPattern)) return email;
            System.out.println("Thông báo lỗi: Email không hợp lệ! Vui lòng nhập đúng cấu trúc (VD: name@company.com).");
        }
    }

    private static LocalDate inputDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (text.isEmpty()) return LocalDate.now(); // Trả về ngày hiện tại nếu không nhập
            try {
                return LocalDate.parse(text);
            } catch (DateTimeParseException e) {
                System.out.println("Thông báo lỗi: Định dạng ngày sai! Hãy nhập theo chuẩn YYYY-MM-DD (VD: 1999-05-18).");
            }
        }
    }

    private static BigDecimal inputSalary(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (text.isEmpty()) return BigDecimal.ZERO;
            try {
                BigDecimal sal = new BigDecimal(text);
                if (sal.compareTo(BigDecimal.ZERO) >= 0) return sal;
                System.out.println("Thông báo lỗi: Mức lương phải là số lớn hơn hoặc bằng 0!");
            } catch (NumberFormatException e) {
                System.out.println("Thông báo lỗi: Giá trị nhập vào phải là một số thực hợp lệ!");
            }
        }
    }
}