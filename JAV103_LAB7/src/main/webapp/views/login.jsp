<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>

<h1>Đăng nhập</h1>

<form method="post" action="${pageContext.request.contextPath}/login">
    Username: <input name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <button type="submit">Login</button>
</form>

<p style="color:red;">
    ${message}
</p>

<a href="${pageContext.request.contextPath}/">Về trang chủ</a>

</body>
</html>