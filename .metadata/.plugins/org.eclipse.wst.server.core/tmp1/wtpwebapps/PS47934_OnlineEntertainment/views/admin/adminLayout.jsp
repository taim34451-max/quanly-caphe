<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    
    <style>
        body { background-color: #0f0f0f !important; color: #f1f1f1 !important; }
        
        /* Layout Admin */
        .admin-nav { 
            background-color: #000 !important; 
            border-bottom: 3px solid #e50914 !important; 
            padding: 10px 20px; 
        }
        
        /* Links & Menu */
        .navbar-brand { color: #fff !important; font-weight: 900; font-size: 1.5rem; text-decoration: none; }
        .nav-link { 
            color: #aaa !important; 
            font-weight: 700; 
            transition: 0.3s; 
            text-decoration: none !important;
        }
        .nav-link:hover { color: #fff !important; }
        
        /* Nút và Khoảng cách */
        .navbar-nav { gap: 40px; }
        .btn-red { background-color: #e50914; color: white; border: none; font-weight: bold; }
        .btn-red:hover { background-color: #b20710; color: white; }
    </style>
</head>
<body>

    <nav class="navbar navbar-expand-lg admin-nav">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">ADMINISTRATION TOOL</a>
            
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">HOME</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/video">VIDEOS</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/user">USERS</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/report">REPORTS</a></li>
                </ul>
            </div>

            <div class="d-flex ms-auto">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <div class="dropdown">
                            <button class="btn btn-outline-light btn-sm dropdown-toggle" data-bs-toggle="dropdown">
                                <i class="bi bi-person-circle"></i> ${sessionScope.user.fullname}
                            </button>
                            <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end mt-2">
                                <li><a class="dropdown-item text-danger fw-bold" href="${pageContext.request.contextPath}/logoff">Logoff</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-red btn-sm px-3" href="${pageContext.request.contextPath}/login">Login</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>

    <main class="container mt-4">
        <jsp:include page="${view}" />
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>