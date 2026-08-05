<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-4 mb-5">
    <h3 class="mb-4 fw-bold"><i class="bi bi-people"></i> QUẢN LÝ NGƯỜI DÙNG</h3>
    
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <div class="card shadow-sm mb-5">
        <div class="card-body">
            <form method="post">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label fw-bold">Tên đăng nhập (ID)</label>
                        <input type="text" name="id" value="${user.id}" class="form-control" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label fw-bold">Mật khẩu</label>
                        <input type="password" name="password" value="${user.password}" class="form-control" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label fw-bold">Họ và tên</label>
                        <input type="text" name="fullname" value="${user.fullname}" class="form-control" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label fw-bold">Email</label>
                        <input type="email" name="email" value="${user.email}" class="form-control" required>
                    </div>
                    <div class="col-md-12 mb-4">
                        <div class="form-check form-check-inline fs-5">
                            <input class="form-check-input" type="radio" name="admin" value="false" ${!user.admin ? 'checked' : ''}>
                            <label class="form-check-label text-secondary">Khách hàng</label>
                        </div>
                        <div class="form-check form-check-inline fs-5">
                            <input class="form-check-input" type="radio" name="admin" value="true" ${user.admin ? 'checked' : ''}>
                            <label class="form-check-label fw-bold text-danger">Quản trị viên (Admin)</label>
                        </div>
                    </div>
                </div>
                
                <div class="d-flex gap-2">
                    <button class="btn btn-success fw-bold" formaction="${pageContext.request.contextPath}/admin/user/update">Cập Nhật</button>
                    <button class="btn btn-danger fw-bold" formaction="${pageContext.request.contextPath}/admin/user/delete" onclick="return confirm('Xác nhận xóa tài khoản?')">Xóa</button>
                    <button class="btn btn-secondary fw-bold" formaction="${pageContext.request.contextPath}/admin/user/reset">Mới</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-header bg-dark text-white"><h5 class="mb-0">Danh sách Tài khoản</h5></div>
        <div class="card-body p-0">
            <table class="table table-hover mb-0 align-middle text-center">
                <thead class="table-light">
                    <tr>
                        <th>Username</th>
                        <th>Họ và Tên</th>
                        <th>Email</th>
                        <th>Vai trò</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.fullname}</td>
                            <td>${u.email}</td>
                            <td>${u.admin ? '<span class="badge bg-danger">Admin</span>' : '<span class="badge bg-secondary">Khách</span>'}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/user/edit/${u.id}" class="btn btn-sm btn-info text-white">Sửa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
        <div class="card-footer bg-white d-flex justify-content-center pt-3">
            <ul class="pagination">
                <li class="page-item ${pageNumber == 0 ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/user?page=0">Đầu</a>
                </li>
                <li class="page-item ${pageNumber == 0 ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/user?page=${pageNumber - 1}">Trước</a>
                </li>
                <li class="page-item disabled"><span class="page-link text-dark fw-bold">Trang ${pageNumber + 1} / ${maxPage + 1}</span></li>
                <li class="page-item ${pageNumber >= maxPage ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/user?page=${pageNumber + 1}">Sau</a>
                </li>
                <li class="page-item ${pageNumber >= maxPage ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/user?page=${maxPage}">Cuối</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />