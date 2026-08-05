<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Sửa nhân viên</title></head>
<body style="font-family: Arial; padding: 20px;">
    <h2>Sửa nhân viên</h2>
    
    <form action="${pageContext.request.contextPath}/employees/update" method="POST">
        <p>
            Mã nhân viên (Không cho sửa): <br>
            <input type="text" name="emp_code" value="${emp.empCode}" readonly style="background:#eee;">
        </p>
        <p>
            Họ và tên: <br>
            <input type="text" name="full_name" value="${emp.fullName}" required>
            <span style="color:red;">${errName}</span>
        </p>
        <p>
            Email: <br>
            <input type="email" name="email" value="${emp.email}" required>
            <span style="color:red;">${errEmail}</span>
        </p>
        <button type="submit">Lưu</button>
        <a href="${pageContext.request.contextPath}/employees">Quay lại</a>
    </form>
</body>
</html>