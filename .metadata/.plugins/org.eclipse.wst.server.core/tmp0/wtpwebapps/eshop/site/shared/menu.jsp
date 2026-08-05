<%@ include file="page.jsp" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${ctx}/home/index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${ctx}/home/about">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${ctx}/home/contact">Contact</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${ctx}/product/index">Products</a>
            </li>
            <c:if test="${!empty sessionScope.user}">
            	<c:if test="${sessionScope.user.role == 1}">
		            <li class="nav-item">
		                <a class="nav-link" href="${ctx}/admin/user/index">Administration</a>
		            </li>
	            </c:if>
	            <li class="nav-item">
	                <a class="nav-link" href="${ctx}/auth/logoff">Sign Out</a>
	            </li>
            </c:if>
        </ul>
    </div>
</nav>