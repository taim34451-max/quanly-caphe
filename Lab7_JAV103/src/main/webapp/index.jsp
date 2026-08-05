<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h2>Visitors: ${applicationScope.visitors}</h2>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        Welcome you
        <a href="${pageContext.request.contextPath}/login.jsp">Đăng nhập</a>
    </c:when>
    <c:otherwise>
        Welcome ${sessionScope.user.fullname}
        <a href="${pageContext.request.contextPath}/account/sign-out">Đăng xuất</a>
        <c:if test="${sessionScope.user.admin}">
            <a href="${pageContext.request.contextPath}/admin/home/index">Quản trị</a>
        </c:if>
    </c:otherwise>
</c:choose>