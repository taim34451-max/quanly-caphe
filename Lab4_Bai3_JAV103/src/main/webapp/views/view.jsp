<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Chi tiết nhân viên</title></head>
<body style="font-family: Arial; padding: 20px;">
    <h2>Chi tiết nhân viên</h2>
    <p><strong>Mã NV:</strong> ${emp.empCode}</p>
    <p><strong>Họ tên:</strong> ${emp.fullName}</p>
    <p><strong>Email:</strong> ${emp.email}</p>
    <br>
    <a href="${pageContext.request.contextPath}/employees">Quay lại</a>
</body>
</html>