package PS47934_MaiThanhTai;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({
    "/product/index",
    "/product/create",
    "/product/edit/*",
    "/product/delete/*",
    "/product/update" 
})
public class ProductServlet extends HttpServlet { 

    private static final long serialVersionUID = 1L;

    // Bộ nhớ tạm lưu trữ danh sách sản phẩm
    ArrayList<Product> list = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        // Khởi tạo dữ liệu mẫu khi Server vừa chạy lên
        list.add(new Product(1, "Laptop", 1000));
        list.add(new Product(2, "Phone", 500));
        list.add(new Product(3, "Mouse", 50));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Ép kiểu cho luồng xuất dữ liệu ra màn hình
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String uri = req.getRequestURI();

        // 1. Luồng hiển thị danh sách (INDEX)
        if (uri.contains("index")) {
            out.println("<h1>DANH SÁCH SẢN PHẨM</h1>");
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Price</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");

            for (Product p : list) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>" + p.getPrice() + "</td>");
                out.println("<td>");
                out.println("<a href='edit/" + p.getId() + "'>Edit</a> | ");
                out.println("<a href='delete/" + p.getId() + "'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br>");
            out.println("<a href='create'>Create Product</a>");
        }

        // 2. Luồng hiển thị Form thêm mới (CREATE)
        else if (uri.contains("create")) {
            out.println("<h1>CREATE PRODUCT</h1>");
            out.println("<form method='post'>");
            out.println("ID:<br>");
            out.println("<input name='id'><br><br>");
            out.println("Name:<br>");
            out.println("<input name='name'><br><br>");
            out.println("Price:<br>");
            out.println("<input name='price'><br><br>");
            out.println("<button>Create</button>");
            out.println("</form>");
        }

        // 3. Luồng hiển thị Form cập nhật (EDIT)
        else if (uri.contains("edit")) {
            String idStr = uri.substring(uri.lastIndexOf("/") + 1);
            int id = Integer.parseInt(idStr);
            Product found = null;

            for (Product p : list) {
                if (p.getId() == id) {
                    found = p;
                    break;
                }
            }

            if (found != null) {
                out.println("<h1>EDIT PRODUCT</h1>");
                
                // Trỏ form về luồng xử lý update. 
                out.println("<form action='" + req.getContextPath() + "/product/update' method='post'>");
                
                // Thuộc tính readonly giúp người dùng nhìn thấy ID nhưng không thể sửa nó
                out.println("ID:<br>");
                out.println("<input name='id' value='" + found.getId() + "' readonly><br><br>");
                
                out.println("Name:<br>");
                out.println("<input name='name' value='" + found.getName() + "'><br><br>");
                
                out.println("Price:<br>");
                out.println("<input name='price' value='" + found.getPrice() + "'><br><br>");
                
                out.println("<button type='submit'>Save Changes</button>");
                out.println("</form>");
            }
        }

        // 4. Luồng xử lý Xóa (DELETE)
        else if (uri.contains("delete")) {
            String idStr = uri.substring(uri.lastIndexOf("/") + 1);
            int id = Integer.parseInt(idStr);
            
            // Xóa sản phẩm khỏi ArrayList
            list.removeIf(p -> p.getId() == id);
            
            // Quay về trang danh sách
            resp.sendRedirect(req.getContextPath() + "/product/index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Ép kiểu UTF-8 (Bắt buộc nằm trên cùng để giữ trọn vẹn tiếng Việt)
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Nhận diện xem người dùng đang đứng ở form nào gửi lên
        String uri = req.getRequestURI(); 

        // Đọc dữ liệu từ Form
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        // Lựa chọn Logic xử lý
        if (uri.contains("update")) {
            // Logic CẬP NHẬT: Tìm trong danh sách và sửa đè dữ liệu mới
            for (Product p : list) {
                if (p.getId() == id) {
                    p.setName(name);
                    p.setPrice(price);
                    break;
                }
            }
        } else {
            // Logic THÊM MỚI (Từ trang Create)
            list.add(new Product(id, name, price));
        }

        // Xử lý xong thì điều hướng về trang chủ
        resp.sendRedirect(req.getContextPath() + "/product/index");
    }
}