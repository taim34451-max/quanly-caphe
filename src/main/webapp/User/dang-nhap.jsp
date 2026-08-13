<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - Highlands Coffee</title>
    <link rel="stylesheet" href="dinh-dang.css">
</head>
<body class="auth-body">
    
    <!-- POPUP THÔNG BÁO TỪ TRANG ĐĂNG KÝ CHUYỂN SANG -->
    <div id="register-success" class="success-popup">
        <div class="popup-content">
            <a href="#" class="close-btn">×</a> 
            <h2 style="color: var(--dark-bg); margin-bottom: 10px;">Đăng ký thành công!</h2>
            <p style="color: #666;">Cảm ơn bạn đã trở thành khách hàng thân thiết. Vui lòng đăng nhập để tiếp tục đặt hàng.</p>
            <a href="#" class="popup-btn">Đóng thông báo</a>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/trang-chu.jsp" class="back-home">← Quay lại Trang chủ</a>

    <div class="auth-container">
        <div class="form-box">
            <h2 style="color: var(--dark-bg); margin-bottom: 10px;">Đăng nhập</h2>
            <p style="color: #666; margin-bottom: 20px; font-size: 14px;">Vui lòng đăng nhập để kiểm tra phân quyền</p>
            
            <!-- Đã đồng bộ ID với main.js -->
            <form id="login-form" action="${pageContext.request.contextPath}/dang_nhap" method="post">
           		${error}
               <input type="text" id="username" name="username" placeholder="Nhập tên đăng nhập...">
               <input type="password" id="password" name="password" placeholder="Nhập mật khẩu...">	
                <button type="submit" style="background-color: var(--dark-bg);">Đăng nhập</button>
            <p style="margin-top: 10px; margin-bottom: 15px; font-size: 14px; text-align: right;">
                    <a href="${pageContext.request.contextPath}/quen_mat_khau" style="color: #666; text-decoration: none;">Quên mật khẩu?</a>
                </p>
            
            
            </form>
            
            <p style="margin-top: 15px; font-size: 14px;">
                Chưa có tài khoản? <a href="${pageContext.request.contextPath}/dang-ky.jsp" style="color: var(--dark-bg); text-decoration: none; font-weight: bold;">Đăng ký ngay</a>
            </p>
        </div>
    </div>

    <!-- Nhúng file main.js để gọi Module 4 xử lý đăng nhập -->
    <!-- <script src="main.js"></script> -->
</body>
</html>