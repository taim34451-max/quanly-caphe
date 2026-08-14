<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu - PolyCoffee</title>
    <link rel="stylesheet" href="dinh-dang.css">
</head>
<body class="auth-body">
    <a href="${pageContext.request.contextPath}/Index" class="back-home">← Quay lại Trang chủ</a>

    <div class="auth-container">
        <div class="form-box">
            <h2 style="color: var(--dark-bg); margin-bottom: 10px;">Quên mật khẩu</h2>
            <p style="color: #666; margin-bottom: 20px; font-size: 14px;">Nhập Email hoặc Tên đăng nhập để nhận lại mật khẩu qua Email</p>

            <% if (request.getAttribute("error") != null) { %>
                <div style="color: red; margin-bottom: 15px; font-size: 14px;">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <% if (request.getAttribute("message") != null) { %>
                <div style="color: green; margin-bottom: 15px; font-size: 14px;">
                    <%= request.getAttribute("message") %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/quen_mat_khau" method="post">
                <input type="text" name="account" placeholder="Nhập Email hoặc Tên đăng nhập..." required>
                <button type="submit" style="background-color: var(--dark-bg);">Gửi lại mật khẩu</button>
            </form>
            
            <p style="margin-top: 15px; font-size: 14px;">
                Quay lại <a href="${pageContext.request.contextPath}/User/dang-nhap.jsp" style="color: var(--dark-bg); text-decoration: none; font-weight: bold;">Đăng nhập</a>
            </p>
        </div>
    </div>
</body>
</html>