<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kết Quả Đăng Ký</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .result-container { width: 400px; border: 1px solid #4CAF50; padding: 20px; border-radius: 5px; background-color: #f9fff9; }
        h2 { color: #4CAF50; text-align: center; margin-top: 0;}
    </style>
</head>
<body>

<div class="result-container">
    <h2>Đăng Ký Thành Công!</h2>
    <p><strong>Họ tên:</strong> ${name}</p>
    <p><strong>Email:</strong> ${email}</p>
    <p><strong>Giới tính:</strong> ${gender}</p>
    <p><strong>Chuyên ngành:</strong> ${major}</p>
    
    <div style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/views/register.jsp" style="text-decoration: none; color: #007BFF; font-weight: bold;">Quay lại form</a>
    </div>
</div>

</body>
</html>