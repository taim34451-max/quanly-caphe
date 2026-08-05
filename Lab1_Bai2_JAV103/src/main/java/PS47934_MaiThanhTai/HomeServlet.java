package PS47934_MaiThanhTai;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({
        "/home/index",
        "/home/about",
        "/home/contact"
})
public class HomeServlet extends HttpServlet { // Đã ép viết thường theo form chuẩn

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Cài đặt kiểu trả về và bộ ghi (Không cần khai báo lại nữa)
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 2. Lấy đường dẫn hiện tại và Tên Project động (Context Path)
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath(); // Tránh lỗi 404 do sai tên Project

        // 3. Bắt đầu vẽ giao diện HTML
        out.println("<html>");
        out.println("<body>");

        // === THANH ĐIỀU HƯỚNG (NAVIGATION BAR) ===
        // Sử dụng contextPath để link luôn chạy đúng dù Project tên gì
        out.println("<nav>");
        out.println("  <a href='" + contextPath + "/home/index'>Home</a> | ");
        out.println("  <a href='" + contextPath + "/home/about'>About</a> | ");
        out.println("  <a href='" + contextPath + "/home/contact'>Contact</a>");
        out.println("</nav>");
        out.println("<hr>");
        // ===========================================

        // 4. In nội dung động tùy theo đường link
        if (uri.contains("index")) {
            out.println("<h1>Welcome to Home Page</h1>");

        } else if (uri.contains("about")) {
            out.println("<h1>About Us Page</h1>");

        } else if (uri.contains("contact")) {
            out.println("<h1>Contact Page</h1>");
        }

        // 5. Đóng thẻ HTML
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>POST method is called</h1>");
    }
}