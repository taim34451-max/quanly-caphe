<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">🎬 TÀI PHƯỚC ENTERTAINMENT</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-toggle="target" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
        </li>
        <c:if test="${not empty sessionScope.user}">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/favorite">Yêu thích của tôi</a>
            </li>
            <c:if test="${sessionScope.user.admin}">
                <li class="nav-item">
                  <a class="nav-link text-warning" href="${pageContext.request.contextPath}/admin/video">Quản trị</a>
                </li>
            </c:if>
        </c:if>
      </ul>
      
      <ul class="navbar-nav">
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
            </c:when>
            <c:otherwise>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                    Xin chào, <b>${sessionScope.user.fullname}</b>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/edit-profile">Cập nhật hồ sơ</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/change-password">Đổi mật khẩu</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logoff">Đăng xuất</a></li>
                  </ul>
                </li>
            </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>