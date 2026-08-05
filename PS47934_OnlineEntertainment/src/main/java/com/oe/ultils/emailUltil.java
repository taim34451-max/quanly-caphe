package com.oe.ultils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class emailUltil {
    
    // Khai báo tài khoản Gmail dùng để gửi đi (Bạn cần thay bằng email của bạn)
    private static final String MY_EMAIL = "maithanhtai12092021@gmail.com";
    
    // TUYỆT ĐỐI KHÔNG DÙNG MẬT KHẨU ĐĂNG NHẬP GMAIL. Phải dùng "Mật khẩu ứng dụng" (App Password) 16 ký tự.
    private static final String MY_PASSWORD = "uuwx zguo ptgh xsgm"; 

    public static void send(String to, String subject, String content) throws Exception {
        
        // 1. Cài đặt cấu hình kết nối đến máy chủ SMTP của Google
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // 2. Mở phiên giao dịch và xác thực tài khoản
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("maithanhtai12092021@gmail.com", "uuwx zguo ptgh xsgm");
            }
        });

        // 3. Soạn thảo bức thư
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("maithanhtai12092021@gmail.com"));
        
        // Hàm parse() giúp hỗ trợ gửi cho nhiều email cùng lúc (cách nhau bởi dấu phẩy) cho chức năng Share
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); 
        
        // Cài đặt UTF-8 để gửi tiếng Việt không bị lỗi font
        message.setSubject(subject, "UTF-8");
        
        // Gửi nội dung dưới dạng HTML để có thể chèn link, in đậm, đổi màu cho đẹp
        message.setContent(content, "text/html; charset=utf-8"); 

        // 4. Bấm nút Gửi
        Transport.send(message);
    }
}