<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Báo cáo doanh thu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2 class="text-center mb-4">BÁO CÁO DOANH THU THEO MÓN</h2>
    
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Tên đồ uống</th>
                <th>Tổng số lượng bán</th>
                <th>Tổng doanh thu</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${reportList}">
                <tr>
                    <td>${row[0]}</td>
                    <td>${row[1]}</td>
                    <td>${row[2]} VNĐ</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <a href="${pageContext.request.contextPath}/drink/index" class="btn btn-secondary">Quay lại quản lý</a>
</body>
</html>