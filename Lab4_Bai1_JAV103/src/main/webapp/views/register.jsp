<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Form Đăng Ký Người Dùng</title>
    <style>
        /* Gom nhóm và định dạng CSS sạch sẽ, dễ bảo trì */
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px; 
            background-color: #f9f9f9;
        }
        .form-container { 
            width: 400px; 
            margin: 0 auto; /* Căn giữa form ra giữa màn hình */
            border: 1px solid #ccc; 
            padding: 20px 30px; 
            border-radius: 8px; 
            background-color: white;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center; 
            margin-top: 0;
            color: #333;
        }
        .form-group { 
            margin-bottom: 15px; 
        }
        label {
            font-weight: bold;
            color: #555;
        }
        input[type="text"], select { 
            width: 100%; 
            padding: 10px; 
            margin-top: 8px; 
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; 
        }
        .error { 
            color: #e74c3c; 
            font-size: 0.85em; 
            margin-top: 5px; 
            display: block; 
            font-style: italic;
        }
        .radio-group {
            margin-top: 8px;
        }
        .btn-submit { 
            width: 100%; 
            padding: 12px; 
            margin-top: 10px;
            background-color: #4CAF50; 
            color: white; 
            border: none; 
            border-radius: 4px;
            cursor: pointer; 
            font-weight: bold;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .btn-submit:hover { 
            background-color: #45a049; 
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Form Đăng Ký</h2>
    
    <form action="${pageContext.request.contextPath}/RegistrationServlet" method="POST">
        
        <div class="form-group">
            <label for="name">Họ tên:</label>
            <input type="text" id="name" name="name" value="${name}" placeholder="Nhập họ và tên...">
            <c:if test="${not empty errors.name}">
                <span class="error">${errors.name}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="${email}" placeholder="Nhập địa chỉ email...">
            <c:if test="${not empty errors.email}">
                <span class="error">${errors.email}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label>Giới tính:</label>
            <div class="radio-group">
                <input type="radio" id="male" name="gender" value="Nam" ${gender == 'Nam' ? 'checked' : ''} checked> 
                <label for="male" style="font-weight: normal;">Nam</label>
                
                <input type="radio" id="female" name="gender" value="Nữ" ${gender == 'Nữ' ? 'checked' : ''}> 
                <label for="female" style="font-weight: normal;">Nữ</label>
            </div>
        </div>

        <div class="form-group">
            <label for="major">Chuyên ngành:</label>
            <select id="major" name="major">
                <option value="Công nghệ thông tin" ${major == 'Công nghệ thông tin' ? 'selected' : ''}>Công nghệ thông tin</option>
                <option value="Kinh doanh quốc tế" ${major == 'Kinh doanh quốc tế' ? 'selected' : ''}>Kinh doanh quốc tế</option>
                <option value="Ngôn ngữ Anh" ${major == 'Ngôn ngữ Anh' ? 'selected' : ''}>Ngôn ngữ Anh</option>
            </select>
        </div>

        <button type="submit" class="btn-submit">Đăng ký</button>
    </form>
</div>

</body>
</html>