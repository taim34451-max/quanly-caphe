<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />

<jsp:include page="/common/menu.jsp" />

<div class="container">
    <h3 class="mb-4">🔥 6 VIDEO HOT NHẤT</h3>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="v" items="${videos}">
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="${v.poster}" class="card-img-top" alt="${v.title}" style="height: 220px; object-fit: cover;">
                    
                    <div class="card-body">
                        <h5 class="card-title text-truncate" title="${v.title}">${v.title}</h5>
                        <p class="card-text text-muted">
                            <i class="bi bi-eye"></i> Lượt xem: ${v.views}
                        </p>
                    </div>
                    
                    <div class="card-footer bg-white text-center">
                        <a href="${pageContext.request.contextPath}/detail?id=${v.id}" class="btn btn-primary w-100">
                            <i class="bi bi-play-circle"></i> Xem ngay
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />