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
            			<a href="quan-ly-thuc-don.jsp">QL Thực đơn</a>
	                    <a href="quan-ly-kho.jsp">QL Kho hàng</a>
	                    <a href="quan-ly-nhan-vien.jsp">QL Nhân viên</a>
	                    <a href="bao-cao-doanh-thu.jsp">Báo cáo</a>
            		</c:when>
            		<c:when test="${sessionScope.user.role == 'BARISTA' }">
            			<a href="nhan-don-hang.jsp">Nhận Đơn Hàng</a>
                    	<a href="cap-nhat-trang-thai.jsp">Cập Nhật Trạng Thái</a>
            		</c:when>
            		<c:when test="${sessionScope.user.role == 'USER' }">
            			<a href="${ctx}/Menu">Thực đơn</a>
                    	<a href="gio-hang.jsp">Giỏ hàng</a>
            		</c:when>
            		<c:otherwise>
			            <a href="${ctx}/Menu">Thực đơn</a>
			            <a href="gio-hang.jsp">Giỏ hàng</a>
			            <span style="color: #fff; margin: 0 15px;">|</span>
			            <a href="dang-ky.jsp" style="color: var(--primary-color); font-weight: bold;">Đăng ký</a>
			            <a href="${ctx }/User/dang-nhap.jsp">Đăng nhập</a>
            		</c:otherwise>
            	</c:choose>
            </nav>
        </div>
    </header>

</body>
</html>