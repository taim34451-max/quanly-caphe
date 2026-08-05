<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<style>
    /* Dark Theme Styles */
    .admin-input { background-color: #181818 !important; border: 1px solid #333 !important; color: #fff !important; }
    .nav-tabs .nav-link { color: #fff; background-color: #000; border: 1px solid #333; }
    .nav-tabs .nav-link.active { background-color: #e50914 !important; border: none !important; color: white !important; }
</style>

<h4 class="fw-bold mb-3 text-white"><i class="bi bi-bar-chart-fill me-2"></i> BÁO CÁO THỐNG KÊ</h4>

<ul class="nav nav-tabs border-0 mb-4" id="reportTabs">
    <li class="nav-item"><button class="nav-link ${activeTab == 'tab1' ? 'active' : ''}" data-bs-toggle="tab" data-bs-target="#tab1">Lượt Yêu Thích</button></li>
    <li class="nav-item"><button class="nav-link ${activeTab == 'tab2' ? 'active' : ''}" data-bs-toggle="tab" data-bs-target="#tab2">Người Dùng Yêu Thích</button></li>
    <li class="nav-item"><button class="nav-link ${activeTab == 'tab3' ? 'active' : ''}" data-bs-toggle="tab" data-bs-target="#tab3">Lịch Sử Chia Sẻ</button></li>
</ul>

<div class="tab-content p-4 border border-dark bg-dark text-white">
    
    <div class="tab-pane fade ${activeTab == 'tab1' ? 'show active' : ''}" id="tab1">
        <table class="table table-dark table-hover border-secondary text-center">
            <thead class="table-secondary">
                <tr><th>Tên Video</th><th>Tổng Lượt Thích</th><th>Mới Nhất</th><th>Cũ Nhất</th></tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${favReports}">
                    <tr>
                        <td class="text-start fw-bold">${item[0]}</td>
                        <td class="text-danger fw-bold fs-5">${item[1]} <i class="bi bi-heart-fill"></i></td>
                        <td><fmt:formatDate value="${item[2]}" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatDate value="${item[3]}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="tab-pane fade ${activeTab == 'tab2' ? 'show active' : ''}" id="tab2">
        <form action="${pageContext.request.contextPath}/admin/report" method="get" class="mb-4">
            <input type="hidden" name="activeTab" value="tab2"> <label class="fw-bold mb-2">Chọn Video:</label>
            <select name="videoUserId" class="form-select admin-input" onchange="this.form.submit()">
                <c:forEach var="v" items="${videos}">
                    <option value="${v.id}" ${v.id == videoUserId ? 'selected' : ''}>${v.title}</option>
                </c:forEach>
            </select>
        </form>
        <table class="table table-dark table-hover border-secondary text-center">
            <thead class="table-secondary">
                <tr><th>Username</th><th>Họ và Tên</th><th>Email</th><th>Ngày Thích</th></tr>
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
                <c:if test="${empty favUsers}">
                    <tr><td colspan="4" class="text-secondary">Chưa có người dùng nào thích video này.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <div class="tab-pane fade ${activeTab == 'tab3' ? 'show active' : ''}" id="tab3">
        <form action="${pageContext.request.contextPath}/admin/report" method="get" class="mb-4">
            <input type="hidden" name="activeTab" value="tab3"> <label class="fw-bold mb-2">Chọn Video:</label>
            <select name="videoShareId" class="form-select admin-input" onchange="this.form.submit()">
                <c:forEach var="v" items="${videos}">
                    <option value="${v.id}" ${v.id == videoShareId ? 'selected' : ''}>${v.title}</option>
                </c:forEach>
            </select>
        </form>
        <table class="table table-dark table-hover border-secondary text-center">
            <thead class="table-secondary">
                <tr><th>Người Gửi (Tên)</th><th>Email Gửi</th><th>Email Nhận</th><th>Ngày Gửi</th></tr>
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
                <c:if test="${empty sharedFriends}">
                    <tr><td colspan="4" class="text-secondary">Chưa có lượt chia sẻ nào cho video này.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>