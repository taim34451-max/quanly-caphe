<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Online Entertainment - Cinematic</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    
   <style>
    @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');
    
    body { 
        font-family: 'Inter', sans-serif !important;
        background-color: #000000 !important; /* Đen tuyền 100% */
        color: #ffffff !important; /* Trắng tinh khiết */
    }

    .navbar-cinematic {
        background-color: #000000 !important;
        border-bottom: 1px solid #222;
    }
    
    .brand-text { color: #ffffff !important; }
    .nav-link { color: #aaaaaa !important; }
    .nav-link:hover { color: #ffffff !important; }

    /* Dropdown Đen tuyền */
    .dropdown-menu { background-color: #000000 !important; border: 1px solid #333; }
    .dropdown-item { color: #ffffff !important; }
    .dropdown-item:hover { background-color: #1a1a1a !important; color: #e50914 !important; }
</style>
</head>
<body class="d-flex flex-column min-vh-100">

    <nav class="navbar navbar-expand-lg navbar-dark navbar-cinematic py-3 sticky-top">
        <div class="container">
            <a class="navbar-brand brand-text" href="${pageContext.request.contextPath}/home">
                <i class="bi bi-display-fill text-danger me-1"></i> ONLINE ENTERTAINMENT
            </a>
            
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-center">
                    
                    <c:if test="${not empty sessionScope.user}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/favorite">
                                <i class="bi bi-bookmark-check-fill me-1"></i> My Favorites
                            </a>
                        </li>
                    </c:if>

                    <li class="nav-item dropdown ms-3">
                        <a class="nav-link dropdown-toggle" href="#" id="accountMenu" role="button" data-bs-toggle="dropdown">
                            <i class="bi bi-person-bounding-box me-1"></i> 
                            ${not empty sessionScope.user ? sessionScope.user.fullname : 'My Account'}
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end shadow-lg mt-2">
                            <c:if test="${empty sessionScope.user}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login"><i class="bi bi-box-arrow-in-right me-2"></i>Login</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/register"><i class="bi bi-person-plus-fill me-2"></i>Registration</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/forgot-password"><i class="bi bi-key-fill me-2"></i>Forgot Password</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.user}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/edit-profile"><i class="bi bi-pencil-square me-2"></i>Edit Profile</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/change-password"><i class="bi bi-shield-lock-fill me-2"></i>Change Password</a></li>
                                <c:if test="${sessionScope.user.admin == true}">
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/admin/video"><i class="bi bi-speedometer2 me-2"></i>Admin Dashboard</a></li>
                                </c:if>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logoff"><i class="bi bi-box-arrow-left me-2"></i>Logoff</a></li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4 min-vh-custom flex-grow-1">
        <c:if test="${not empty sessionScope.message}">
            <div class="alert fw-bold border-0" style="background-color: #2b2b2b; color: #4ade80; border-left: 4px solid #4ade80 !important;" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i> ${sessionScope.message}
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert"></button>
            </div>
            <c:remove var="message" scope="session" />
        </c:if>

        <c:if test="${not empty sessionScope.error}">
            <div class="alert fw-bold border-0" style="background-color: #2b2b2b; color: #f87171; border-left: 4px solid #e50914 !important;" role="alert">
                <i class="bi bi-exclamation-octagon-fill me-2"></i> ${sessionScope.error}
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert"></button>
            </div>
            <c:remove var="error" scope="session" />
        </c:if>

        <jsp:include page="${view}" />
    </div>

    <footer class="text-center py-4 mt-auto" style="background: #000000; border-top: 1px solid #272727;">
        <div class="container">
            <small class="text-secondary fw-bold">© 2026 Online Entertainment. Developed by SD.</small>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>