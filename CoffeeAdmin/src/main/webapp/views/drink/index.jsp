<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Đồ uống</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h2 class="mb-4 text-center">QUẢN LÝ ĐỒ UỐNG</h2>

    <!-- Form Thêm / Sửa -->
    <form action="${pageContext.request.contextPath}/${empty drink ? 'drink/create' : 'drink/update'}" method="post" class="card p-4 mb-4 shadow-sm">
        <div class="row g-3">
            <div class="col-md-3">
                <label class="form-label">ID Đồ uống</label>
                <input type="text" name="idDrink" value="${drink.idDrink}" class="form-control" ${not empty drink ? 'readonly' : ''} required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Tên đồ uống</label>
                <input type="text" name="drinkName" value="${drink.drinkName}" class="form-control" required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Giá tiền</label>
                <input type="number" step="0.01" name="drinkPrice" value="${drink.drinkPrice}" class="form-control" required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Danh mục</label>
                <select name="idCate" class="form-select" required>
                    <option value="">-- Chọn danh mục --</option>
                    <c:forEach var="cate" items="${categories}">
                        <option value="${cate.idCate}" ${drink.category.idCate == cate.idCate ? 'selected' : ''}>
                            ${cate.catename}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-4">
                <label class="form-label">Ảnh (Tên file)</label>
                <input type="text" name="drinkIMG" value="${drink.drinkIMG}" class="form-control">
            </div>
            <div class="col-md-5">
                <label class="form-label">Mô tả</label>
                <input type="text" name="drinkDescription" value="${drink.drinkDescription}" class="form-control">
            </div>
            <c:if test="${not empty drink}">
                <div class="col-md-3">
                    <label class="form-label">Trạng thái</label>
                    <select name="drinkActive" class="form-select">
                        <option value="true" ${drink.drinkActive == true ? 'selected' : ''}>Hoạt động</option>
                        <option value="false" ${drink.drinkActive == false ? 'selected' : ''}>Ngừng bán</option>
                    </select>
                </div>
            </c:if>
        </div>
        <div class="mt-3">
            <button type="submit" class="btn btn-success">${empty drink ? 'Thêm mới' : 'Cập nhật'}</button>
            <a href="${pageContext.request.contextPath}/drink/index" class="btn btn-secondary">Làm mới</a>
        </div>
    </form>

    <!-- Bảng Hiển Thị Danh Sách -->
    <table class="table table-bordered table-striped text-center align-middle">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên món</th>
                <th>Giá</th>
                <th>Danh mục</th>
                <th>Ảnh</th>
                <th>Trạng thái</th>
                <th>Mô tả</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="d" items="${list}">
                <tr>
                    <td>${d.idDrink}</td>
                    <td>${d.drinkName}</td>
                    <td>${d.drinkPrice}</td>
                    <td>${d.category.catename}</td>
                    <td>${d.drinkIMG}</td>
                    <td>${d.drinkActive ? 'Hoạt động' : 'Ngừng bán'}</td>
                    <td>${d.drinkDescription}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/drink/edit?id=${d.idDrink}" class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/drink/delete?id=${d.idDrink}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa không?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>