<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-4 mb-5">
    <div class="row">
        <div class="col-lg-8">
            <div class="ratio ratio-16x9 mb-4">
                <iframe src="https://www.youtube.com/embed/${video.id}" title="${video.title}" allowfullscreen></iframe>
            </div>
            
            <h3 class="fw-bold">${video.title}</h3>
            <div class="d-flex justify-content-between align-items-center mb-4 pb-3 border-bottom">
                <span class="text-muted fs-5"><i class="bi bi-eye"></i> ${video.views} lượt xem</span>
                
                <div class="btn-group">
                    <a href="${pageContext.request.contextPath}/like?id=${video.id}" class="btn btn-outline-danger">
                        <i class="bi bi-heart-fill"></i> Like
                    </a>
                    <a href="${pageContext.request.contextPath}/unlike?id=${video.id}" class="btn btn-outline-secondary">
                        <i class="bi bi-heartbreak"></i> Unlike
                    </a>
                    <a href="${pageContext.request.contextPath}/share?id=${video.id}" class="btn btn-outline-primary">
                        <i class="bi bi-share"></i> Share
                    </a>
                </div>
            </div>

            <div class="card bg-light border-0">
                <div class="card-body">
                    <h5 class="fw-bold">Mô tả tiểu phẩm:</h5>
                    <p style="white-space: pre-line;">${video.description}</p>
                </div>
            </div>
        </div>
        
        <div class="col-lg-4 mt-4 mt-lg-0">
            <div class="card border-0 shadow-sm">
                <div class="card-header bg-dark text-white">
                    <h5 class="mb-0">Có thể bạn sẽ thích</h5>
                </div>
                <div class="card-body text-center text-muted">
                    <p><i class="bi bi-camera-reels" style="font-size: 3rem;"></i></p>
                    <p>Tính năng đang phát triển...</p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />