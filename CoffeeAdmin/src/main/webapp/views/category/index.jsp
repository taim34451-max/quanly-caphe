<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2 class="mb-4 text-center">QUẢN LÝ DANH MỤC</h2>

    <form action="${pageContext.request.contextPath}/${empty category ? 'category/create' : 'category/update'}" method="post" class="card p-4 mb-4 shadow-sm">
        <div class="row g-3">
            <div class="col-md-4">
                <label class="form-label">ID Danh mục</label>
                <input type="text" name="idCate" value="${category.idCate}" class="form-control" ${not empty category ? 'readonly' : ''} required>
            </div>
            <div class="col-md-4">
                <label class="form-label">Tên danh mục</label>
                <input type="text" name="catename" value="${category.catename}" class="form-control" required>
            </div>
            <div class="col-md-4">
                <label class="form-label">Loại danh mục</label>
                <input type="text" name="catetype" value="${category.catetype}" class="form-control">
            </div>
        </div>
        <div class="mt-3">
            <button type="submit" class="btn btn-success">${empty category ? 'Thêm mới' : 'Cập nhật'}</button>
            <a href="${pageContext.request.contextPath}/category/index" class="btn btn-secondary">Làm mới</a>
        </div>
    </form>

    <table class="table table-bordered table-striped text-center align-middle">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên danh mục</th>
                <th>Loại</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${list}">
                <tr>
                    <td>${c.idCate}</td>
                    <td>${c.catename}</td>
                    <td>${c.catetype}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/category/edit?id=${c.idCate}" class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/category/delete?id=${c.idCate}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa không?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>