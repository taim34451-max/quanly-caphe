<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-5 mb-5" style="min-height: 50vh;">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow border-0">
                <div class="card-header bg-warning text-dark text-center py-3">
                    <h5 class="mb-0 fw-bold"><i class="bi bi-key"></i> QUÊN MẬT KHẨU</h5>
                </div>
                <div class="card-body p-4">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">${message}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                        <div class="mb-4">
                            <label class="form-label fw-bold">Nhập địa chỉ Email đã đăng ký</label>
                            <input type="email" name="email" class="form-control" placeholder="vd: tai@gmail.com" required>
                            <div class="form-text text-muted">Hệ thống sẽ gửi mật khẩu mới vào email này.</div>
                        </div>
                        <button type="submit" class="btn btn-warning w-100 fw-bold">LẤY LẠI MẬT KHẨU</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />