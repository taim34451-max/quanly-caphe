<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2 class="mb-4 text-center">QUẢN LÝ NGƯỜI DÙNG</h2>

    <form action="${pageContext.request.contextPath}/${empty user ? 'user/create' : 'user/update'}" method="post" class="card p-4 mb-4 shadow-sm">
        <div class="row g-3">
            <div class="col-md-3">
                <label class="form-label">ID Người dùng</label>
                <input type="text" name="idUser" value="${user.idUser}" class="form-control" ${not empty user ? 'readonly' : ''} required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" name="password" value="${user.password}" class="form-control" required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Họ tên</label>
                <input type="text" name="fullName" value="${user.fullName}" class="form-control" required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Vai trò</label>
                <select name="role" class="form-select">
                    <option value="true" ${user.role == true ? 'selected' : ''}>Quản trị viên</option>
                    <option value="false" ${user.role == false ? 'selected' : ''}>Nhân viên</option>
                </select>
            </div>
        </div>
        <div class="mt-3">
            <button type="submit" class="btn btn-success">${empty user ? 'Thêm mới' : 'Cập nhật'}</button>
            <a href="${pageContext.request.contextPath}/user/index" class="btn btn-secondary">Làm mới</a>
        </div>
    </form>

    <table class="table table-bordered table-striped text-center align-middle">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Họ tên</th>
                <th>Vai trò</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${list}">
                <tr>
                    <td>${u.idUser}</td>
                    <td>${u.userName}</td>
                    <td>${u.role == 1 ? 'Quản trị viên' : 'Nhân viên'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/user/edit?id=${u.idUser}" class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/user/delete?id=${u.idUser}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa không?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>