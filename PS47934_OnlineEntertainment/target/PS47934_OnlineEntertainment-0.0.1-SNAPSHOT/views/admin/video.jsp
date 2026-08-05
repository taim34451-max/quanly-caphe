<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-4 mb-5">
    <h3 class="mb-4 fw-bold"><i class="bi bi-film"></i> QUẢN LÝ TIỂU PHẨM (VIDEO)</h3>
    
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="card shadow-sm mb-5">
        <div class="card-body">
            <form method="post">
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label class="form-label fw-bold">Youtube ID (Mã Video)</label>
                        <input type="text" name="id" value="${video.id}" class="form-control" placeholder="vd: Ytet_bPiRCU" required>
                    </div>
                    <div class="col-md-8 mb-3">
                        <label class="form-label fw-bold">Tên tiểu phẩm</label>
                        <input type="text" name="title" value="${video.title}" class="form-control" required>
                    </div>
                    <div class="col-md-12 mb-3">
                        <label class="form-label fw-bold">Link Ảnh Bìa (Poster)</label>
                        <input type="text" name="poster" value="${video.poster}" class="form-control" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label fw-bold">Lượt xem</label>
                        <input type="number" name="views" value="${video.views}" class="form-control" min="0">
                    </div>
                    <div class="col-md-6 mb-3 d-flex align-items-end">
                        <div class="form-check fs-5 mb-2">
                            <input class="form-check-input" type="checkbox" name="active" value="true" ${video.active ? 'checked' : ''} id="activeCheck">
                            <label class="form-check-label fw-bold" for="activeCheck">Đang hoạt động (Active)</label>
                        </div>
                    </div>
                    <div class="col-md-12 mb-4">
                        <label class="form-label fw-bold">Mô tả chi tiết</label>
                        <textarea name="description" class="form-control" rows="4">${video.description}</textarea>
                    </div>
                </div>
                
                <div class="d-flex gap-2">
                    <button class="btn btn-primary fw-bold" formaction="${pageContext.request.contextPath}/admin/video/create">Thêm Mới</button>
                    <button class="btn btn-success fw-bold" formaction="${pageContext.request.contextPath}/admin/video/update">Cập Nhật</button>
                    <button class="btn btn-danger fw-bold" formaction="${pageContext.request.contextPath}/admin/video/delete" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</button>
                    <button class="btn btn-secondary fw-bold" formaction="${pageContext.request.contextPath}/admin/video/reset">Làm Mới Form</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-header bg-dark text-white"><h5 class="mb-0">Danh sách Video</h5></div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-bordered mb-0 align-middle text-center">
                    <thead class="table-light">
                        <tr>
                            <th>Youtube ID</th>
                            <th>Tên Tiểu Phẩm</th>
                            <th>Lượt Xem</th>
                            <th>Trạng Thái</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="v" items="${videos}">
                            <tr>
                                <td>${v.id}</td>
                                <td class="text-start">${v.title}</td>
                                <td>${v.views}</td>
                                <td>${v.active ? '<span class="badge bg-success">Active</span>' : '<span class="badge bg-secondary">Inactive</span>'}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/video/edit/${v.id}" class="btn btn-sm btn-info text-white">Chỉnh sửa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />