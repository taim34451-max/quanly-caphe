package com.oe.servlet.user;

import java.io.IOException;

import com.oe.dao.userDAO;
import com.oe.entity.user;
import com.oe.ultils.emailUltil;
import com.oe.ultils.sessionUltil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Gom 3 đường dẫn vào chung 1 controller
@WebServlet({"/forgot-password", "/change-password", "/edit-profile"})
public class accountcontroller extends HttpServlet {
    
    private userDAO dao = new userDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.contains("forgot-password")) {
            req.setAttribute("view", "/views/user/forgot-password.jsp");
            
        } else if (uri.contains("change-password")) {
            req.setAttribute("view", "/views/user/change-password.jsp");
            
        } else if (uri.contains("edit-profile")) {
            req.setAttribute("view", "/views/user/edit-profile.jsp");
            
        }
        req.getRequestDispatcher("/views/user/layout.jsp")
           .forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        try {
        	// 1. XỬ LÝ QUÊN MẬT KHẨU
            if (uri.contains("forgot-password")) {
                String email = req.getParameter("email");
                user u = dao.findByEmail(email);
                
                if (u != null) {
                    // Tạo mật khẩu mới ngẫu nhiên (6 số)
                	String chars =
                			"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

                			StringBuilder sb = new StringBuilder();

                			for (int i = 0; i < 8; i++) {

                			    int index =
                			        (int)(Math.random() * chars.length());

                			    sb.append(chars.charAt(index));
                			}

                			String newPass = sb.toString();
                    u.setPassword(newPass);
                    dao.update(u); // Lưu mật khẩu mới vào CSDL ngay lập tức
                    
                    // Soạn email
                    String subject = "🔑 Mật khẩu mới của bạn";
                    String content = "<p>Chào " + u.getFullname() + ",</p>"
                                   + "<p>Hệ thống đã reset mật khẩu của bạn. Mật khẩu mới là: <b>" + newPass + "</b></p>"
                                   + "<p>Vui lòng đăng nhập và đổi lại mật khẩu ngay nhé!</p>";
                    
                    // [CẬP NHẬT TỐI ƯU HIỆU NĂNG] Ném việc gửi mail cho luồng phụ xử lý
                    new Thread(() -> {
                        try {
                            emailUltil.send(email, subject, content);
                        } catch (Exception e) {
                            System.out.println("Lỗi gửi mail reset pass: " + e.getMessage());
                        }
                    }).start();
                    
                    // Đẩy thông báo ra màn hình luôn để người dùng không phải chờ
                    req.setAttribute("message", "Đã gửi mật khẩu mới vào email của bạn! Vui lòng chờ trong giây lát");
                } else {
                    req.setAttribute("error", "Email không tồn tại trong hệ thống!");
                }
                
                // 1. Gói cái ruột form vào biến "view"
                req.setAttribute("view", "/views/user/forgot-password.jsp");

                // 2. Chuyển hướng về file Layout tổng để nó nhúng Bootstrap và Menu vào
                req.getRequestDispatcher("/views/user/layout.jsp").forward(req, resp);
            }
            
            // 2. XỬ LÝ ĐỔI MẬT KHẨU
            if (uri.contains("change-password")) {
                // 1. Kéo dữ liệu từ form lên
                String oldPass = req.getParameter("oldPass");
                String newPass = req.getParameter("newPass");
                String confirmPass = req.getParameter("confirmPass");
                
                // 2. Móc thông tin user đang đăng nhập từ Session
                user u = (user) sessionUltil.get(req, "user");
                
                // 3. Bắt đầu rào lỗi (Validation)
                if (!u.getPassword().equals(oldPass)) {
                    req.setAttribute("error", "Mật khẩu cũ không chính xác!");
                    } else if (newPass.trim().length() < 6) {
                    // [BỔ SUNG VALIDATION] Ép mật khẩu mới phải từ 6 ký tự trở lên
                    req.setAttribute("error", "Mật khẩu mới quá ngắn, phải có ít nhất 6 ký tự!");
                   } else if (!newPass.equals(confirmPass)) {
                    req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
                   } else {
                    // 4. Mọi thứ hợp lệ -> Cập nhật dữ liệu
                    try {
                        u.setPassword(newPass); // Đổi thành pass mới
                        dao.update(u);          // Lưu xuống Database
                        
                        // Rất quan trọng: Cập nhật lại thông tin user trong Session để hệ thống nhớ pass mới
                        sessionUltil.add(req, "user", u); 
                        
                        req.setAttribute("message", "Đổi mật khẩu thành công! Tài khoản của bạn đã an toàn.");
                    } catch (Exception e) {
                        req.setAttribute("error", "Lỗi hệ thống khi cập nhật mật khẩu!");
                        e.printStackTrace();
                    }
                }
                
                // Bắn về lại trang đổi mật khẩu kèm thông báo (Lỗi hoặc Thành công)
                req.setAttribute("view", "/views/user/change-password.jsp");
                req.getRequestDispatcher("/views/user/layout.jsp").forward(req, resp);
            }
            
            // 3. XỬ LÝ CẬP NHẬT HỒ SƠ
            else if (uri.contains("edit-profile")) {
                String fullname = req.getParameter("fullname");
                String email = req.getParameter("email");
                
                user u = (user) sessionUltil.get(req, "user");
                u.setFullname(fullname);
                u.setEmail(email);
                
                dao.update(u);
                sessionUltil.add(req, "user", u); // Cập nhật session để tên trên góc màn hình thay đổi theo
                
                req.setAttribute("message", "Cập nhật hồ sơ thành công!");
                req.getRequestDispatcher("/views/user/edit-profile.jsp").forward(req, resp);
            }
            
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi xử lý hệ thống: " + e.getMessage());
            doGet(req, resp); // Trả lại trang hiện tại nếu có lỗi cứng
        }
    }
}