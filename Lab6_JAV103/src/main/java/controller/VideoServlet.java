package controller;

import dao.bai2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.favorite;
import model.report;

// 1. Map nhiều đường dẫn vào chung 1 Servlet để quản lý tập trung
@WebServlet({"/lab6/bai2", "/lab6/bai3", "/lab6/bai4"})
public class VideoServlet extends HttpServlet {
    
    private EntityManagerFactory emf;

    // 2. Khởi tạo kết nối CSDL 1 lần duy nhất khi Server khởi động để tối ưu hiệu suất
    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("PolyOE");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Cấu hình mã hóa UTF-8 để hiển thị tiếng Việt chuẩn
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String path = req.getServletPath();
        EntityManager em = emf.createEntityManager();

        try {
            // --- XỬ LÝ BÀI 2 ---
        	if (path.equals("/lab6/bai2")) {
                String username = req.getParameter("username"); 
                if (username != null && !username.isEmpty()) {
                    bai2 dao2 = new bai2(em);
                    List<favorite> listFav = dao2.getFavoritesByUser(username);
                    req.setAttribute("favorites", listFav);
                    req.setAttribute("username", username); 
                }
            }
            // --- BÀI 3 ---
           else if (path.equals("/lab6/bai3")) {
                String keyword = req.getParameter("keyword");
                if (keyword != null && !keyword.isEmpty()) {
                    dao.bai3 dao3 = new dao.bai3(em);
                    List<model.video> listVideo = dao3.findVideosByKeyword(keyword);
                    req.setAttribute("videos", listVideo);
                    req.setAttribute("keyword", keyword);
                }
            }
            // --- BÀI 4 ---
          else if (path.equals("/lab6/bai4")) {
            String yearStr = req.getParameter("year");
            if (yearStr != null && !yearStr.isEmpty()) {
                int year = Integer.parseInt(yearStr);
                dao.bai4 dao4 = new dao.bai4(em);
                List<Object[]> stats = dao4.getFavoriteByYear(year); // Gọi đúng hàm đã sửa
                req.setAttribute("stats", stats);
                req.setAttribute("year", year);
            }
          }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Giải phóng tài nguyên sau khi xử lý xong
        }

        // 3. Đẩy toàn bộ dữ liệu sang trang index.jsp để hiển thị
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    // 4. Đóng kết nối an toàn khi tắt Server
    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}