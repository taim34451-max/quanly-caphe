package poly.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig 
@WebServlet("/bai2")
public class Bai2Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Cấu hình UTF-8 và kiểu trả về là JSON
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Lấy đối tượng Part chứa file upload từ client (trùng tên 'uploadFile' ở JS)
        Part filePart = request.getPart("uploadFile");

        if (filePart != null && filePart.getSize() > 0) {
            // Lấy thông tin file
            String fileName = filePart.getSubmittedFileName();
            String fileType = filePart.getContentType();
            long fileSize = filePart.getSize();

            // Lấy đường dẫn thực tế của thư mục gốc ứng dụng để lưu file
            String applicationPath = request.getServletContext().getRealPath("");
            // Tạo thư mục "uploads" nếu chưa có
            String uploadFilePath = applicationPath + File.separator + "uploads";
            File uploadFolder = new File(uploadFilePath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Lưu file vào thư mục
            filePart.write(uploadFilePath + File.separator + fileName);

            // Tạo chuỗi JSON trả về
            String jsonResponse = String.format(
                    "{\"name\": \"%s\", \"type\": \"%s\", \"size\": %d}",
                    fileName, fileType, fileSize
            );

            // Gửi JSON về client
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\": \"Không có file được tải lên!\"}");
        }
    }
}