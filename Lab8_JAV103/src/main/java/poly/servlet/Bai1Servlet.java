package poly.servlet;

import java.io.IOException;
import java.io.PrintWriter;

// Thay đổi bắt buộc cho Tomcat 10: Sử dụng jakarta thay vì javax
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bai1")
public class Bai1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Thiết lập header để trình duyệt hiểu đây là dữ liệu JSON và hỗ trợ tiếng Việt
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Chuỗi JSON dữ liệu nhân viên
        String jsonString = "{\n" +
                "  \"manv\": \"TeoNV\",\n" +
                "  \"hoTen\": \"Nguyễn Văn Tèo\",\n" +
                "  \"gioiTinh\": true,\n" +
                "  \"luong\": 950.5\n" +
                "}";

        // Gửi dữ liệu JSON về client
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }
}