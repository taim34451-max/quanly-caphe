<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-4 mb-5" style="min-height: 60vh;">
    <h3 class="mb-4 fw-bold"><i class="bi bi-bar-chart-fill"></i> BÁO CÁO THỐNG KÊ</h3>

    <ul class="nav nav-tabs fw-bold mb-4" id="reportTabs" role="tablist">
        <li class="nav-item">
            <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#tab1">Lượt Yêu Thích</button>
        </li>
        <li class="nav-item">
            <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab2">Người Dùng Yêu Thích</button>
        </li>
        <li class="nav-item">
            <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab3">Lịch Sử Chia Sẻ</button>
        </li>
    </ul>

    <div class="tab-content border-start border-end border-bottom p-4 bg-white shadow-sm">
        
        <div class="tab-pane fade show active" id="tab1">
            <table class="table table-bordered table-striped text-center align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>Tên Video</th>
                        <th>Tổng Lượt Thích</th>
                        <th>Thích Mới Nhất</th>
                        <th>Thích Cũ Nhất</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${favReports}">
                        <tr>
                            <td class="text-start fw-bold">${item[0]}</td> <td class="text-danger fw-bold fs-5">${item[1]} <i class="bi bi-heart-fill"></i></td>
                            <td><fmt:formatDate value="${item[2]}" pattern="dd/MM/yyyy"/></td>
                            <td><fmt:formatDate value="${item[3]}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="tab-pane fade" id="tab2">
            <form action="${pageContext.request.contextPath}/admin/report" method="get" class="row g-3 align-items-center mb-4">
                <div class="col-auto"><label class="fw-bold">Chọn Video:</label></div>
                <div class="col-md-6">
                    <select name="videoUserId" class="form-select">
                        <c:forEach var="v" items="${videos}">
                            <option value="${v.id}" ${v.id == videoUserId ? 'selected' : ''}>${v.title}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary">Lọc Dữ Liệu</button>
                </div>
            </form>
            <table class="table table-bordered text-center align-middle">
                <thead class="table-primary">
                    <tr>
                        <th>Username</th>
                        <th>Họ và Tên</th>
                        <th>Email</th>
                        <th>Ngày Thích</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="f" items="${favUsers}">
                        <tr>
                            <td>${f.user.id}</td>
                            <td>${f.user.fullname}</td>
                            <td>${f.user.email}</td>
                            <td><fmt:formatDate value="${f.likeDate}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="tab-pane fade" id="tab3">
            <form action="${pageContext.request.contextPath}/admin/report" method="get" class="row g-3 align-items-center mb-4">
                <div class="col-auto"><label class="fw-bold">Chọn Video:</label></div>
                <div class="col-md-6">
                    <select name="videoShareId" class="form-select">
                        <c:forEach var="v" items="${videos}">
                            <option value="${v.id}" ${v.id == videoShareId ? 'selected' : ''}>${v.title}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary">Lọc Dữ Liệu</button>
                </div>
            </form>
            <table class="table table-bordered text-center align-middle">
                <thead class="table-warning text-dark">
                    <tr>
                        <th>Người Gửi (Tên)</th>
                        <th>Người Gửi (Email)</th>
                        <th>Danh sách Email Nhận</th>
                        <th>Ngày Gửi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${sharedFriends}">
                        <tr>
                            <td class="fw-bold">${s[0]}</td>
                            <td>${s[1]}</td>
                            <td class="text-start">${s[2]}</td>
                            <td><fmt:formatDate value="${s[3]}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />