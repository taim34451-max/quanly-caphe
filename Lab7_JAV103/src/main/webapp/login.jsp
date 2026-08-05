<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập hệ thống</title>
</head>
<body>
    <h2>Đăng nhập</h2>
    
    <h3 style="color:red;">${message}</h3>
    
    <form action="${pageContext.request.contextPath}/account/sign-in" method="post">
        <div>
            <label>Tên đăng nhập (username):</label>
            <input type="text" name="username" required>
            <i>(Gợi ý: admin hoặc user)</i>
        </div>
        <br>
        <div>
            <label>Mật khẩu (password):</label>
            <input type="password" name="password" required>
            <i>(Gợi ý: 123)</i>
        </div>
        <br>
        <button type="submit">Đăng nhập</button>
    </form>
</body>
</html>