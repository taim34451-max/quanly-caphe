<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Thêm nhân viên</title></head>
<body style="font-family: Arial; padding: 20px;">
    <h2>Thêm nhân viên</h2>
    <span style="color:red;">${error}</span>
    
    <form action="${pageContext.request.contextPath}/employees/create" method="POST">
        <p>
            Mã nhân viên: <br>
            <input type="text" name="emp_code" value="${param.emp_code}" required>
            <span style="color:red;">${errCode}</span>
        </p>
        <p>
            Họ và tên: <br>
            <input type="text" name="full_name" value="${param.full_name}" required>
            <span style="color:red;">${errName}</span>
        </p>
        <p>
            Email: <br>
            <input type="email" name="email" value="${param.email}" required>
            <span style="color:red;">${errEmail}</span>
        </p>
        <button type="submit">Thêm</button>
        <a href="${pageContext.request.contextPath}/employees">Quay lại</a>
    </form>
</body>
</html>