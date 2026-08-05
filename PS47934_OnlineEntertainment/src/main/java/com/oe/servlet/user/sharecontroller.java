package com.oe.servlet.user;

import java.io.IOException;
import java.util.Date;

import com.oe.dao.shareDAO;
import com.oe.dao.videoDAO;
import com.oe.entity.share;
import com.oe.entity.user;
import com.oe.entity.video;
import com.oe.ultils.emailUltil;
import com.oe.ultils.sessionUltil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/share")
public class sharecontroller extends HttpServlet {
    
    private shareDAO sDao = new shareDAO();
    private videoDAO vDao = new videoDAO();

    // HÀM MỚI THÊM: Hứng luồng GET để hiển thị giao diện trang share.jsp
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chặn cửa: Chưa đăng nhập không cho vào trang Share
        if (!sessionUltil.isLogin(req)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // Điều hướng ra giao diện
        req.getRequestDispatcher("/views/user/share.jsp").forward(req, resp);
    }

    // HÀM CŨ: Xử lý lưu Database và gửi Email khi form Submit
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Bảo mật: Chưa đăng nhập mà dám dùng tool post thì đá về trang login
        if (!sessionUltil.isLogin(req)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Lấy thông tin từ form trong trang share.jsp
        String videoId = req.getParameter("videoId");
        String emails = req.getParameter("emails");
        
        // Kéo thông tin người dùng đang đăng nhập từ Session
        user u = (user) sessionUltil.get(req, "user");
        video v = vDao.findById(videoId);

        try {
            // [FIX 1]: Tiền xử lý chuỗi email. Biến tất cả dấu chấm phẩy thành dấu phẩy 
            // để hàm InternetAddress.parse() trong emailUltil không bị crash.
            String safeEmails = emails.replace(";", ",");

            // 1. Lưu vết lịch sử Share vào Database
            share s = new share(u, v, safeEmails, new Date());
            sDao.create(s);

            // 2. Tạo link động trỏ về trang web của bạn
            String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() 
                        + req.getContextPath() + "/detail?id=" + videoId;
            
         // 3. Soạn nội dung email
            String subject = u.getFullname() + " muốn chia sẻ một video siêu hay với bạn!";
            String content = "<p>Chào bạn,</p>"
                           + "<p>Người dùng <b>" + u.getFullname() + "</b> vừa gửi cho bạn tiểu phẩm: <b>" + v.getTitle() + "</b>.</p>"
                           + "<p>Hãy click vào link sau để xem ngay: <a href='" + link + "'>Xem Video Tại Đây</a></p>";
            
            // [CẬP NHẬT TỐI ƯU HIỆU NĂNG] 
            // 4. Bấm nút gửi NHƯNG ném vào một luồng chạy ngầm (Thread) để không bắt người dùng phải chờ
            new Thread(() -> {
                try {
                    emailUltil.send(safeEmails, subject, content);
                } catch (Exception e) {
                    System.out.println("Lỗi gửi mail ngầm: " + e.getMessage());
                }
            }).start();
            
            // Đẩy thông báo thành công vào Session để trang detail đọc được ngay lập tức
            sessionUltil.add(req, "message", "Đã gửi email chia sẻ thành công! Vui lòng đợi trong giây lát");
            
        } catch (Exception e) {
            e.printStackTrace(); // In toàn bộ log đỏ ra console của Tomcat để dễ trace lỗi
            
            // [FIX 3]: Báo lỗi thẳng ra màn hình cho người dùng biết thay vì im lặng
            sessionUltil.add(req, "error", "Gửi email thất bại. Vui lòng kiểm tra lại log hệ thống!");
        }
        
        // Gửi xong thì load lại trang chi tiết video đó
        resp.sendRedirect(req.getContextPath() + "/detail?id=" + videoId);
    }
}