<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-4 mb-5" style="min-height: 60vh;">
    <h3 class="mb-4 fw-bold text-danger"><i class="bi bi-heart-fill"></i> VIDEO YÊU THÍCH CỦA TÔI</h3>
    
    <c:if test="${empty favorites}">
        <div class="alert alert-warning">
            Bạn chưa thả tim video nào. Hãy ra <a href="${pageContext.request.contextPath}/home" class="fw-bold">Trang chủ</a> để khám phá nhé!
        </div>
    </c:if>

    <div class="row row-cols-1 row-cols-md-4 g-4">
        <c:forEach var="f" items="${favorites}">
            <div class="col">
                <div class="card h-100 shadow-sm border-0 bg-light">
                    <img src="${f.video.poster}" class="card-img-top rounded-top" alt="Poster" style="height: 180px; object-fit: cover;">
                    
                    <div class="card-body">
                        <h6 class="card-title text-truncate fw-bold" title="${f.video.title}">${f.video.title}</h6>
                        <p class="card-text text-muted small">
                            Đã thích vào: <fmt:formatDate value="${f.likeDate}" pattern="dd/MM/yyyy"/>
                        </p>
                    </div>
                    
                    <div class="card-footer bg-white border-0 text-center pb-3">
                        <a href="${pageContext.request.contextPath}/detail?id=${f.video.id}" class="btn btn-sm btn-primary w-100 mb-2">Xem lại</a>
                        <a href="${pageContext.request.contextPath}/unlike?id=${f.video.id}" class="btn btn-sm btn-outline-danger w-100">Bỏ thích</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />