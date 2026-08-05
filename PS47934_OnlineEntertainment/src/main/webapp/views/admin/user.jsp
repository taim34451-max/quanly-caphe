<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
    .btn-red { background-color: #e50914; color: white; border: none; font-weight: 600; }
    .btn-red:hover { background-color: #b20710; color: white; }
    .admin-card { background-color: #181818; border: 1px solid #333; color: white; }
    .admin-input { background-color: #000 !important; border: 1px solid #333 !important; color: #fff !important; }
</style>

<div class="container-fluid py-4">
    <h3 class="mb-4 fw-bold text-white"><i class="bi bi-people-fill"></i> QUẢN LÝ NGƯỜI DÙNG</h3>
    
    <%-- Thông báo --%>
    <c:if test="${not empty message}"><div class="alert alert-success">${message}</div></c:if>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

    <div class="card admin-card mb-5">
        <div class="card-body">
            <form method="post">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Tên đăng nhập (ID)</label>
                        <input type="text" name="id" value="${user.id}" class="form-control admin-input" required ${not empty user.id ? 'readonly' : ''}>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Mật khẩu</label>
                        <input type="password" name="password" value="${user.password}" class="form-control admin-input" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" name="fullname" value="${user.fullname}" class="form-control admin-input" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" value="${user.email}" class="form-control admin-input" required>
                    </div>
                    <div class="col-md-12 mb-4">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="admin" value="false" ${!user.admin ? 'checked' : ''}>
                            <label class="form-check-label">Khách hàng</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="admin" value="true" ${user.admin ? 'checked' : ''}>
                            <label class="form-check-label fw-bold text-danger">Quản trị viên (Admin)</label>
                        </div>
                    </div>
                </div>
                
                <div class="d-flex gap-2">
                    <button type="submit" formaction="${pageContext.request.contextPath}/admin/user/create" class="btn btn-red px-4">Tạo</button>
                    <button type="submit" formaction="${pageContext.request.contextPath}/admin/user/update" class="btn btn-red px-4">Cập Nhật</button>
                    <button type="submit" formaction="${pageContext.request.contextPath}/admin/user/delete" class="btn btn-secondary px-4" onclick="return confirm('Xác nhận xóa?')">Xóa</button>
                   <button type="button" class="btn btn-secondary px-4" onclick="window.location.href='${pageContext.request.contextPath}/admin/user'">Làm Mới</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card admin-card">
        <div class="card-header border-bottom border-secondary bg-black text-white"><h5><i class="bi bi-list-ul"></i> Danh sách Tài khoản</h5></div>
        <table class="table table-dark table-hover mb-0 text-center">
            <thead>
                <tr><th>Username</th><th>Họ và Tên</th><th>Email</th><th>Vai trò</th><th>Thao tác</th></tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.fullname}</td>
                        <td>${u.email}</td>
                        <td>${u.admin ? '<span class="badge bg-danger">Admin</span>' : '<span class="badge bg-secondary">Khách</span>'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/user/edit/${u.id}" class="btn btn-sm btn-outline-light">Sửa</a>
                            <form action="${pageContext.request.contextPath}/admin/user/role" method="post" class="d-inline">
                                <input type="hidden" name="id" value="${u.id}">
                                <input type="hidden" name="action" value="${u.admin ? 'revoke' : 'grant'}">
                                <button type="submit" class="btn btn-sm btn-outline-light ms-1">${u.admin ? 'Hạ cấp' : 'Thăng cấp'}</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="card-footer border-secondary d-flex justify-content-center p-3">
            <nav>
                <ul class="pagination pagination-sm mb-0">
                    <li class="page-item ${pageNumber == 0 ? 'disabled' : ''}"><a class="page-link bg-black border-secondary text-white" href="${pageContext.request.contextPath}/admin/user?page=0">Đầu</a></li>
                    <li class="page-item ${pageNumber == 0 ? 'disabled' : ''}"><a class="page-link bg-black border-secondary text-white" href="${pageContext.request.contextPath}/admin/user?page=${pageNumber - 1}">Trước</a></li>
                    <li class="page-item"><span class="page-link bg-black border-secondary text-white">${pageNumber + 1} / ${maxPage + 1}</span></li>
                    <li class="page-item ${pageNumber >= maxPage ? 'disabled' : ''}"><a class="page-link bg-black border-secondary text-white" href="${pageContext.request.contextPath}/admin/user?page=${pageNumber + 1}">Sau</a></li>
                    <li class="page-item ${pageNumber >= maxPage ? 'disabled' : ''}"><a class="page-link bg-black border-secondary text-white" href="${pageContext.request.contextPath}/admin/user?page=${maxPage}">Cuối</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>