<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thành Công</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 20px;">
    <h2 style="color: #4CAF50;">Đã lưu dữ liệu vào SQL Server!</h2>
    <p><strong>Họ tên:</strong> ${name}</p>
    <p><strong>Email:</strong> ${email}</p>
    <p><strong>Giới tính:</strong> ${gender}</p>
    <p><strong>Chuyên ngành:</strong> ${major}</p>
    
    <a href="${pageContext.request.contextPath}/views/register.jsp">Quay lại form</a>
</body>
</html>