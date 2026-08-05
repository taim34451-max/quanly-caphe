<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-5 mb-5 text-center" style="min-height: 50vh;">
    <h1 class="display-4 fw-bold text-primary">Khu Vực Quản Trị Hệ Thống</h1>
    <p class="lead mt-3">Chào mừng sếp <b>${sessionScope.user.fullname}</b> đã quay trở lại.</p>
    <hr class="my-4">
    <div class="d-flex justify-content-center gap-3 mt-4">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/admin/video"><i class="bi bi-film"></i> Quản lý Video</a>
        <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/admin/user"><i class="bi bi-people"></i> Quản lý Người dùng</a>
        <a class="btn btn-warning btn-lg text-dark" href="${pageContext.request.contextPath}/admin/report"><i class="bi bi-bar-chart"></i> Báo cáo Thống kê</a>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />