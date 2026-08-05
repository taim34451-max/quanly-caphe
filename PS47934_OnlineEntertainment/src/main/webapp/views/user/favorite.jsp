<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<style>
    /* Chỉnh sửa: Đổi .card-fav thành .card-cinematic để đồng bộ với trang Home */
    .card-cinematic {
        background-color: #181818 !important; 
        border: 1px solid #222 !important;
    }
    .card-cinematic h6 { color: #ffffff !important; }
    .card-cinematic p { color: #888888 !important; }
    
    /* Nút bấm */
    .btn-dark-grey { background-color: #222; color: #fff; border: none; }
    .btn-dark-grey:hover { background-color: #333; color: #fff; }
    .btn-red { background-color: #e50914; color: white; border: none; }
    .btn-red:hover { background-color: #b20710; color: white; }
</style>

<div class="container mt-4 mb-5">
    <h3 class="mb-4 text-uppercase fw-bold" style="letter-spacing: -0.5px; color: #ffffff;">
        <i class="bi bi-bookmark-heart-fill text-danger me-2"></i> VIDEO YÊU THÍCH
    </h3>
    
    <c:if test="${empty favorites}">
        <div class="card card-cinematic p-5 text-center border-0 shadow-sm">
            <i class="bi bi-heart text-danger fs-1 mb-3"></i>
            <h5 class="text-white">Bạn chưa thả tim video nào.</h5>
            <p class="text-secondary">Hãy ra <a href="${pageContext.request.contextPath}/home" class="text-danger fw-bold text-decoration-none">Trang chủ</a> để khám phá nhé!</p>
        </div>
    </c:if>

    <div class="row row-cols-1 row-cols-md-4 g-4">
        <c:forEach var="f" items="${favorites}">
            <div class="col">
                <!-- Giữ nguyên class card-cinematic, giờ CSS đã nhận diện đúng -->
                <div class="card h-100 card-cinematic p-0">
                    <a href="${pageContext.request.contextPath}/detail?id=${f.video.id}" class="img-wrapper">
                        <img src="${f.video.poster}" class="card-img-top w-100" alt="Poster" style="height: 180px; object-fit: cover;">
                    </a>
                    
                    <div class="card-body px-3 py-3 d-flex flex-column">
                        <h6 class="card-title fw-bold text-white mb-2 text-truncate" title="${f.video.title}">${f.video.title}</h6>
                        <p class="small mb-4" style="color: #aaaaaa;">
                            <i class="bi bi-calendar-event me-1"></i> Ngày thích: <fmt:formatDate value="${f.likeDate}" pattern="dd/MM/yyyy"/>
                        </p>
                        
                        <div class="d-flex gap-2 mt-auto">
                            <a href="${pageContext.request.contextPath}/detail?id=${f.video.id}" class="btn btn-dark-grey w-50 fw-semibold py-2">
                                <i class="bi bi-play-fill"></i> Xem
                            </a>
                            <a href="${pageContext.request.contextPath}/unlike?id=${f.video.id}" class="btn btn-red w-50 fw-semibold py-2">
                                <i class="bi bi-trash-fill"></i> Bỏ thích
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>