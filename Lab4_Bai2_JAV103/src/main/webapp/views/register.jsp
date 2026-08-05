<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form Đăng Ký</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-container { width: 400px; border: 1px solid #ccc; padding: 20px; border-radius: 5px; }
        .error { color: red; font-size: 0.9em; margin-bottom: 10px; display: block; }
        input[type="text"], select { width: 100%; padding: 8px; margin-top: 5px; margin-bottom: 15px; box-sizing: border-box; }
        .btn-submit { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Form Đăng Ký (Bài 2)</h2>
    
    <c:if test="${not empty errors.db}">
        <div style="background-color: #ffdddd; padding: 10px; margin-bottom: 15px; border-left: 5px solid red;">
            <span class="error" style="margin: 0;">${errors.db}</span>
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/registrationservlet" method="POST">
        <label>Họ tên:</label>
        <input type="text" name="name" value="${name}">
        <c:if test="${not empty errors.name}"><span class="error">${errors.name}</span></c:if>

        <label>Email:</label>
        <input type="text" name="email" value="${email}">
        <c:if test="${not empty errors.email}"><span class="error">${errors.email}</span></c:if>

        <label>Giới tính:</label><br>
        <input type="radio" name="gender" value="Nam" ${gender == 'Nam' ? 'checked' : ''} checked> Nam
        <input type="radio" name="gender" value="Nữ" ${gender == 'Nữ' ? 'checked' : ''}> Nữ<br><br>

        <label>Chuyên ngành:</label>
        <select name="major">
            <option value="Công nghệ thông tin" ${major == 'Công nghệ thông tin' ? 'selected' : ''}>Công nghệ thông tin</option>
            <option value="Kinh doanh quốc tế" ${major == 'Kinh doanh quốc tế' ? 'selected' : ''}>Kinh doanh quốc tế</option>
            <option value="Ngôn ngữ Anh" ${major == 'Ngôn ngữ Anh' ? 'selected' : ''}>Ngôn ngữ Anh</option>
        </select>

        <button type="submit" class="btn-submit">Đăng ký vào CSDL</button>
    </form>
</div>
</body>
</html>