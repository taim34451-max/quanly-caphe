<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-black shadow-sm mb-4" style="border-bottom: 3px solid #e50914;">
  <div class="container">
    <a class="navbar-brand text-white fw-bold fs-4" href="${pageContext.request.contextPath}/home">
        ONLINE ENTERTAINMENT
    </a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto"></ul>
      
      <ul class="navbar-nav align-items-center">
        <li class="nav-item me-3">
          <a class="nav-link text-white fw-bold text-uppercase" href="${pageContext.request.contextPath}/favorite">MY FAVORITES</a>
        </li>
      
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-white fw-bold text-uppercase" href="#" role="button" data-bs-toggle="dropdown">
            My Account
          </a>
          
          <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Login</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/forgot-password">Forgot Password</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/register">Registration</a></li>
                </c:when>
                <c:otherwise>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logoff">Logoff</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/change-password">Change Password</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/edit-profile">Edit Profile</a></li>
                    
                    <c:if test="${sessionScope.user.admin}">
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item text-danger fw-bold" href="${pageContext.request.contextPath}/admin/video">Admin Management</a></li>
                    </c:if>
                </c:otherwise>
            </c:choose>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>