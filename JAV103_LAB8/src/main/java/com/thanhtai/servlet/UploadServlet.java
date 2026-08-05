package com.thanhtai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/upload-ajax")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
    maxFileSize = 1024 * 1024 * 10,       // 10 MB
    maxRequestSize = 1024 * 1024 * 50     // 50 MB
)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Xác định đường dẫn thư mục lưu trữ thực tế trên máy chủ
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + "uploads";
        
        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            Part part = req.getPart("file"); // Lấy part từ key dữ liệu gửi từ Client là 'file'
            String fileName = part.getSubmittedFileName();
            String fileType = part.getContentType();
            long fileSize = part.getSize();

            // Ghi file vào thư mục đích
            part.write(uploadFilePath + File.separator + fileName);

            // Phản hồi thông tin tệp dạng JSON chuẩn
            String jsonResponse = String.format(
                "{\"name\": \"%s\", \"type\": \"%s\", \"size\": %d}",
                fileName, fileType, fileSize
            );

            resp.getWriter().print(jsonResponse);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"error\": \"Lỗi tải file: " + e.getMessage() + "\"}");
        }
    }
}