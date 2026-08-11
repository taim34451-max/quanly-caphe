<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="sidebar">
    <div>
        <div class="brand">
            <div class="brand-icon"><i class="fas fa-coffee"></i></div>
            <div class="brand-name">Barista Hub</div>
        </div>
        
	<ul class="menu-list">
    
    <c:set var="uri" value="${requestScope['jakarta.servlet.forward.servlet_path']}" />
    <c:set var="activeStatus" value="${not empty currentStatus ? currentStatus : param.status}" />

    <li class="menu-item ${uri == '/barista/dashboard' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/barista/dashboard.jsp">
            <i class="fas fa-chart-line"></i>
            <span>Dashboard</span>
        </a>
    </li>
    
    <li class="menu-item ${(uri == '/barista/orders' && (activeStatus == null || activeStatus == 'ALL')) ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/barista/orders?status=ALL">
            <i class="fas fa-list"></i>
            <span>Tất cả đơn hàng</span>
        </a>
    </li>
    
    <li class="menu-item ${(uri == '/barista/orders' && activeStatus == 'PENDING') ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/barista/orders?status=PENDING">
            <i class="fas fa-clock"></i>
            <span>Đơn Mới</span>
            <span class="menu-badge" id="sidebar-pending-badge" style="${counts['PENDING'] > 0 ? '' : 'display:none;'}">
                ${counts['PENDING']}
            </span>
        </a>
    </li>
    
    <li class="menu-item ${(uri == '/barista/orders' && activeStatus == 'MAKING') ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/barista/orders?status=MAKING">
            <i class="fas fa-mug-hot"></i>
            <span>Đang Pha</span>
            <span class="menu-badge" id="sidebar-making-badge" style="${counts['MAKING'] > 0 ? '' : 'display:none;'}">
                ${counts['MAKING']}
            </span>
        </a>
    </li>
    
    <li class="menu-item ${(uri == '/barista/orders' && activeStatus == 'COMPLETED') ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/barista/orders?status=COMPLETED">
            <i class="fas fa-check-circle"></i>
            <span>Hoàn Thành</span>
        </a>
    </li>
</ul>
    </div>
    
    <div>
        <a href="${pageContext.request.contextPath}/logout" class="logout-btn">
            <i class="fas fa-sign-out-alt"></i>
            <span>Đăng xuất</span>
        </a>
    </div>
</div>