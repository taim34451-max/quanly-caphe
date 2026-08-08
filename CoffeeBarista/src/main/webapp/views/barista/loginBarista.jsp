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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/style.css">
    
    <!-- CSS Dự phòng bảo đảm 100% hiện đẹp mượt mà (Đã đổi sang Blue Luxury) -->
    <style>
        body.login-body { background: radial-gradient(circle at center, #1E293B 0%, #0B1120 100%); min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px; font-family: 'Plus Jakarta Sans', sans-serif; }
        .login-card { background: rgba(30, 41, 59, 0.85); backdrop-filter: blur(16px); width: 100%; max-width: 420px; padding: 40px 35px; border-radius: 24px; border: 1px solid rgba(56, 189, 248, 0.2); box-shadow: 0 20px 50px rgba(0, 0, 0, 0.6); text-align: center; color: #F8FAFC; }
        .login-logo { font-size: 48px; color: #38BDF8; margin-bottom: 12px; filter: drop-shadow(0 0 10px rgba(56, 189, 248, 0.4)); }
        .login-title { color: #F8FAFC; font-size: 26px; font-weight: 800; margin-bottom: 6px; }
        .login-subtitle { color: #94A3B8; font-size: 14px; margin-bottom: 25px; }
        .form-group { text-align: left; margin-bottom: 20px; }
        .form-label { display: block; font-size: 13px; font-weight: 600; color: #F8FAFC; margin-bottom: 8px; }
        .form-input { width: 100%; padding: 12px 16px; background-color: #0F172A; border: 1px solid rgba(56, 189, 248, 0.2); border-radius: 12px; font-size: 14px; color: #F8FAFC; outline: none; box-sizing: border-box; }
        .form-input:focus { border-color: #38BDF8; box-shadow: 0 0 10px rgba(56, 189, 248, 0.2); }
        .btn { width: 100%; padding: 13px; background: linear-gradient(135deg, #38BDF8 0%, #0284C7 100%); color: #ffffff; border: none; border-radius: 12px; font-size: 15px; font-weight: 700; cursor: pointer; display: flex; align-items: center; justify-content: center; gap: 8px; }
        .btn:hover { filter: brightness(1.1); transform: translateY(-2px); }
        .login-error { background: rgba(239, 68, 68, 0.15); color: #fca5a5; padding: 10px 14px; border-radius: 12px; font-size: 13px; margin-bottom: 20px; border: 1px solid rgba(239, 68, 68, 0.3); text-align: left; }
        .login-tip { margin-top: 25px; padding-top: 18px; border-top: 1px solid rgba(56, 189, 248, 0.12); color: #94A3B8; font-size: 13px; }
        .login-tip code { background: #0F172A; color: #38BDF8; padding: 2px 6px; border-radius: 4px; font-weight: 700; }
    </style>
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
                <input class="form-input" type="text" id="username" name="username" placeholder="Nhập tên đăng nhập (vd: barista)..." required autocomplete="off" value="barista">
            </div>
            
            <div class="form-group">
                <label class="form-label" for="password">Mật khẩu</label>
                <input class="form-input" type="password" id="password" name="password" placeholder="Nhập mật khẩu (vd: 123456)..." required value="123456">
            </div>
            
            <button type="submit" class="btn">
                <i class="fas fa-sign-in-alt"></i> Đăng nhập làm Barista
            </button>
        </form>
        
        <p class="login-tip">
            💡 Gợi ý thử nghiệm: Nhập tài khoản <code>barista</code> (mật khẩu <code>123456</code>) để đăng nhập ngay.
        </p>
    </div>
</body>
</html>