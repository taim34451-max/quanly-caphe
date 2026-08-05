<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-5 mb-5" style="min-height: 50vh;">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow border-0">
                <div class="card-header bg-info text-white text-center py-3">
                    <h5 class="mb-0 fw-bold"><i class="bi bi-person-lines-fill"></i> HỒ SƠ CÁ NHÂN</h5>
                </div>
                <div class="card-body p-4">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">${message}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/edit-profile" method="post">
                        <div class="mb-3">
                            <label class="form-label fw-bold">Tên đăng nhập (Không thể đổi)</label>
                            <input type="text" name="id" value="${sessionScope.user.id}" class="form-control bg-light" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label fw-bold">Họ và tên</label>
                            <input type="text" name="fullname" value="${sessionScope.user.fullname}" class="form-control" required>
                        </div>
                        <div class="mb-4">
                            <label class="form-label fw-bold">Địa chỉ Email</label>
                            <input type="email" name="email" value="${sessionScope.user.email}" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-info text-white w-100 fw-bold">LƯU THAY ĐỔI</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />