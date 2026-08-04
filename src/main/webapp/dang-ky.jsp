<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký - Highlands Coffee</title>
    <link rel="stylesheet" href="dinh-dang.css">
</head>
<body class="auth-body">
    <a href="trang-chu.jsp" class="back-home">← Quay lại Trang chủ</a>

    <div class="auth-container">
        <div class="form-box">
            <!-- Đổi màu tiêu đề sang đỏ Highlands (--dark-bg) -->
            <h2 style="color: var(--dark-bg); margin-bottom: 10px;">Đăng ký tài khoản</h2>
            <p style="color: #666; margin-bottom: 20px; font-size: 14px;">Trở thành khách hàng thân thiết của Highlands Coffee</p>

            <!-- Cấp ID cho form để JS tiếp quản -->
            <form id="register-form">
                <input type="text" id="reg-name" placeholder="Họ và tên" autocomplete="name" required>
                <input type="tel" id="reg-phone" placeholder="Số điện thoại" autocomplete="tel" required>
                <input type="email" id="reg-email" placeholder="Email" autocomplete="email" required>
                
                <div class="gender">
                    <label><input type="radio" name="gender" value="nam" checked> Nam</label>
                    <label><input type="radio" name="gender" value="nu"> Nữ</label>
                </div>
                
                <input type="password" id="reg-pass" placeholder="Mật khẩu" autocomplete="new-password" required>
                
                <!-- Ép màu nút bấm sang đỏ Highlands -->
                <button type="submit" style="background-color: var(--dark-bg);">Đăng ký</button>
            </form>
            
            <p style="margin-top: 15px; font-size: 14px;">
                Đã có tài khoản? <a href="dang-nhap.jsp" style="color: var(--dark-bg); text-decoration: none; font-weight: bold;">Đăng nhập</a>
            </p>
        </div>
    </div>

    <!-- Nhúng file main.js để gọi Module xử lý đăng ký -->
    <script src="main.js"></script>
</body>
</html>