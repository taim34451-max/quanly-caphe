<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ - Demo 2</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 100px; }
        .btn { 
            text-decoration: none; 
            padding: 15px 30px; 
            background-color: #28a745; 
            color: white; 
            font-size: 20px;
            border-radius: 8px; 
            transition: 0.3s;
        }
        .btn:hover { background-color: #218838; }
    </style>
</head>
<body>
    <h1>Hệ Thống Quản Lý Bài Tập</h1>
    <p>Server đang hoạt động bình thường. Vui lòng chọn chức năng bên dưới:</p>
    <br><br>
    
    <a href="${pageContext.request.contextPath}/elservlet" class="btn">
        Xem Dữ Liệu Expression Language (EL)
    </a>
</body>
</html>