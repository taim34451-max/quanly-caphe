package lab4.bai1.servlet;

import lab4.bai1.utils.ValidationUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // [ĐÃ SỬA]: Chỉ rõ đường dẫn vào thư mục views
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String major = request.getParameter("major");

        Map<String, String> errors = new HashMap<>();

        if (ValidationUtil.isBlank(name)) {
            errors.put("name", "Họ tên không được để trống hoặc chỉ chứa khoảng trắng.");
        }
        if (!ValidationUtil.isValidEmail(email)) {
            errors.put("email", "Email không hợp lệ (VD: abc@gmail.com).");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("major", major);
            
            // [ĐÃ SỬA]: Trả về lại form trong thư mục views kèm lỗi
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("major", major);
            
            // [ĐÃ SỬA]: Chuyển tiếp sang trang kết quả trong thư mục views
            request.getRequestDispatcher("/views/result.jsp").forward(request, response);
        }
    }
}