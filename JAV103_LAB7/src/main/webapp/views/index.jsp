<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Trang chủ</title>
</head>
<body>

<h1>Trang chủ</h1>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <p>Welcome you</p>
        <a href="${pageContext.request.contextPath}/views/login.jsp">Đăng nhập</a>
    </c:when>
    <c:otherwise>
        <p>Welcome ${sessionScope.user.fullname}</p>
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>

        <c:if test="${sessionScope.user.admin}">
            | <a href="${pageContext.request.contextPath}/views/admin/index.jsp">Quản trị</a>
        </c:if>
    </c:otherwise>
</c:choose>

<hr>

<h2>Visitors: ${applicationScope.visitors}</h2>

</body>
</html>