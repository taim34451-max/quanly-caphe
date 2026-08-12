<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="NewFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header class="main-header">
	
		

        <div class="header-container">
            <div class="logo">Highlands Coffee</div>
            <!-- Dọn trống Nav, nhường quyền kiểm soát cho JavaScript -->
            <nav id="main-nav">
            <a href="trang-chu.jsp" class="active">Trang chủ</a>
            	<c:choose>
            		<c:when test="${sessionScope.user.role == 'ADMIN' }">
            
            			<a href="${ctx }/DrinkServlet">QL Thực đơn</a>
	                    
	                    <a href="${ctx }/UserList">QL Nhân viên</a>
	                    <a href="bao-cao-doanh-thu.jsp">Báo cáo</a>
	                    <a>Xin chào, ${sessionScope.user.userName}</a>
	                    <a href="${ctx}/Logout">Logout</a>
            		</c:when>
            		<c:when test="${sessionScope.user.role == 'BARISTA' }">
            			<a href="barista/dashboard.jsp">Barista</a>
            			<a>Xin chào, ${sessionScope.user.userName}</a>
                    	<a href="${ctx}/Logout">Logout</a>
            		</c:when>
            		<c:when test="${sessionScope.user.role == 'USER' }">
            			<a href="${ctx}/Menu">Thực đơn</a>
                    	<a href="${ctx}/cart">Giỏ hàng</a>
                    	<a>Xin chào, ${sessionScope.user.userName}</a>
                    	<a href="${ctx}/Logout">Logout</a>
            		</c:when>
            		<c:otherwise>
			            <a href="${ctx}/Menu">Thực đơn</a>
			            <a href="${ctx}/cart">Giỏ hàng</a>
			            <span style="color: #fff; margin: 0 15px;">|</span>
			            <a href="dang-ky.jsp" style="color: var(--primary-color); font-weight: bold;">Đăng ký</a>
			            <a href="${ctx }/User/dang-nhap.jsp">Đăng nhập</a>
			            <a href="">Quên mật khẩu</a>
            		</c:otherwise>
            	</c:choose>
            	
            </nav>
        </div>
    </header>

</body>
</html>