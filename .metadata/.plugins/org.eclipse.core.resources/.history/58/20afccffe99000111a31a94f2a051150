<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập | Barista Hub</title>
    <!-- FontAwesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body class="login-body">
    <div class="login-card">
        <div class="login-logo">
            <i class="fas fa-coffee"></i>
        </div>
        <h1 class="login-title">Barista Hub</h1>
        <p class="login-subtitle">Hệ thống quản lý pha chế & đơn hàng CoffeeShop</p>
        
        <c:if test="${not empty error}">
            <div class="login-error">
                <i class="fas fa-exclamation-circle"></i> ${error}
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/login" method="POST">
            <div class="form-group">
                <label class="form-label" for="username">Tên đăng nhập</label>
                <input class="form-input" type="text" id="username" name="username" placeholder="Nhập tên đăng nhập..." required autocomplete="off">
            </div>
            
            <div class="form-group">
                <label class="form-label" for="password">Mật khẩu</label>
                <input class="form-input" type="password" id="password" name="password" placeholder="Nhập mật khẩu..." required>
            </div>
            
            <button type="submit" class="btn">
                <i class="fas fa-sign-in-alt"></i> Đăng nhập làm Barista
            </button>
        </form>
        
        <p class="login-tip">
            💡 Gợi ý thử nghiệm: Nhập tài khoản <code>barista</code> (mật khẩu bất kỳ) để đăng nhập ngay.
        </p>
    </div>
</body>
</html>