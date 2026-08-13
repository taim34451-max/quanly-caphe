package Ulti;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String FROM_EMAIL = "duyanhmai23800@gmail.com"; // Thay bằng Email của bạn
    private static final String FROM_PASSWORD = "xdpe fvxr hceu azml"; // Thay bằng Mật khẩu ứng dụng Gmail (16 ký tự)

    public static boolean sendEmail(String toEmail, String subject, String bodyContent) {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL, "PolyCoffee"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
            message.setContent(bodyContent, "text/html; charset=UTF-8");

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Email Chào Mừng Khi Đăng Ký
    public static boolean sendWelcomeEmail(String toEmail, String username) {
        String subject = "Chào mừng bạn đến với PolyCoffee!";
        String content = "<h2 style='color: #b31217;'>Xin chào " + username + "!</h2>"
                + "<p>Cảm ơn bạn đã đăng ký tài khoản tại <b>PolyCoffee</b>.</p>"
                + "<p>Tài khoản của bạn đã được khởi tạo thành công. Chúc bạn có những trải nghiệm tuyệt vời!</p>"
                + "<br><p>Trân trọng,<br>Đội ngũ PolyCoffee</p>";
        return sendEmail(toEmail, subject, content);
    }

    // Email Gửi Mật Khẩu Cũ Khi Quên Mật Khẩu
    public static boolean sendForgotPasswordEmail(String toEmail, String password) {
        String subject = "Khôi phục mật khẩu tài khoản PolyCoffee";
        String content = "<h2 style='color: #b31217;'>Khôi phục mật khẩu</h2>"
                + "<p>Bạn đã yêu cầu lấy lại mật khẩu tài khoản PolyCoffee.</p>"
                + "<p>Mật khẩu hiện tại của bạn là: <strong style='font-size: 18px; color: #b31217;'>" + password + "</strong></p>"
                + "<p>Vui lòng đăng nhập và đổi lại mật khẩu nếu cần thiết.</p>"
                + "<br><p>Trân trọng,<br>Đội ngũ PolyCoffee</p>";
        return sendEmail(toEmail, subject, content);
    }
    
    public static void main(String[] args) {

        boolean result = EmailUtil.sendWelcomeEmail(
            "EMAIL_NHAN_CUA_BAN@gmail.com",
            "Duy Anh"
        );

        System.out.println("Kết quả gửi mail: " + result);
    }
}