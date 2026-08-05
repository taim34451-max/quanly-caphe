package controller;

import dao.customerdao;
import entity.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/customer/index", "/customer/create", "/customer/update", "/customer/delete", "/customer/edit/*"})
public class customerservlet extends HttpServlet {

    private customerdao dao = new customerdao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        customer cus = new customer();

        if (uri.contains("/edit")) {
            String username = uri.substring(uri.lastIndexOf("/") + 1);
            cus = dao.findById(username);
        } 
        else if (uri.contains("/delete")) {
            String username = req.getParameter("username");
            dao.delete(username);
        }

        req.setAttribute("form", cus);
        loadTable(req);
        req.getRequestDispatcher("/customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();

        customer cus = new customer();
        cus.setUsername(req.getParameter("username"));
        cus.setPassword(req.getParameter("password"));
        cus.setFullname(req.getParameter("fullname"));
        cus.setGender(Boolean.parseBoolean(req.getParameter("gender")));
        cus.setEmail(req.getParameter("email"));

        // Khu vực xử lý các hành động từ Form gửi lên
        if (uri.contains("/create")) {
            // 1. Kiểm tra xem username đã tồn tại dưới Database chưa
            customer existCustomer = dao.findById(cus.getUsername());
            
            if (existCustomer != null) {
                // Nếu đã tồn tại -> Trả về thông báo lỗi, giữ lại dữ liệu vừa gõ trên Form để sửa
                req.setAttribute("errorMessage", "Thất bại: Username '" + cus.getUsername() + "' đã được sử dụng!");
            } else {
                // Nếu chưa tồn tại -> Thực hiện thêm mới
                dao.create(cus);
                req.setAttribute("successMessage", "Thêm mới thành công!");
                cus = new customer(); // Reset đối tượng để xóa trắng các ô nhập liệu trên Form
            }
        } 
        else if (uri.contains("/update")) {
            // Thực hiện cập nhật dữ liệu ghi đè lên tài khoản cũ
            dao.update(cus);
            req.setAttribute("successMessage", "Cập nhật thông tin thành công!");
        }

        req.setAttribute("form", cus);
        loadTable(req);
        req.getRequestDispatcher("/customer.jsp").forward(req, resp);
    }

    private void loadTable(HttpServletRequest req) {
        List<customer> list = dao.findAll();
        req.setAttribute("list", list);
    }
}