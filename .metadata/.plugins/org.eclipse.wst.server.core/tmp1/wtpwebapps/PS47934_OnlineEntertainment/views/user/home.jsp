<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
    /* Thẻ phim phẳng, đen xám, không viền thừa */
    .card-cinematic {
        background-color: #181818 !important; 
        border: none !important;
        border-radius: 6px;
        transition: transform 0.2s ease, box-shadow 0.2s ease;
    }
    
    .card-cinematic:hover {
        transform: scale(1.03); /* Phóng to nhẹ toàn bộ thẻ */
        box-shadow: 0 10px 30px rgba(229, 9, 20, 0.15); /* Đổ bóng màu đỏ siêu mờ */
        z-index: 2;
    }

    .img-wrapper {
        border-radius: 6px 6px 0 0;
        overflow: hidden;
    }

    /* Nút Like (Đỏ Netflix) */
    .btn-red {
        background-color: #e50914;
        color: white;
        border-radius: 4px;
        border: none;
        transition: 0.2s;
    }
    .btn-red:hover { background-color: #b20710; color: white; }

    /* Nút Share (Xám đen ngầu) */
    .btn-dark-grey {
        background-color: #2b2b2b;
        color: #f1f1f1;
        border-radius: 4px;
        border: none;
        transition: 0.2s;
    }
    .btn-dark-grey:hover { background-color: #404040; color: white; }
    
    /* Phân trang tối giản */
    .page-link { background-color: #181818; border-color: #272727; color: #aaaaaa; border-radius: 4px; margin: 0 3px;}
    .page-link:hover { background-color: #e50914; color: white; border-color: #e50914; }
    .page-item.disabled .page-link { background-color: #0f0f0f; border-color: #272727; color: #444; }
</style>

<div class="container mb-5 mt-2">
    <h3 class="mb-4 text-uppercase fw-bold" style="letter-spacing: -0.5px;">
        <i class="bi bi-lightning-charge-fill text-danger me-2"></i> TRENDING NOW
    </h3>
    
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="v" items="${videos}">
            <div class="col">
                <div class="card h-100 card-cinematic p-0"> 
                    
                    <a href="${pageContext.request.contextPath}/detail?id=${v.id}" class="position-relative img-wrapper">
                        <img src="${v.poster}" class="card-img-top w-100" alt="${v.title}" style="height: 210px; object-fit: cover;">
                    </a>
                    
                    <div class="card-body px-3 py-3 d-flex flex-column">
                        <a href="${pageContext.request.contextPath}/detail?id=${v.id}" class="text-decoration-none">
                            <h6 class="card-title mb-2 text-white fw-bold lh-base" style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden;" title="${v.title}">
                                ${v.title}
                            </h6>
                        </a>
                        <p class="small mb-4" style="color: #aaaaaa;">
                            <i class="bi bi-bar-chart-fill me-1"></i> Views: ${v.views}
                        </p>
                        
                        <div class="d-flex gap-2 mt-auto">
                            <a href="${pageContext.request.contextPath}/like?id=${v.id}" class="btn btn-red w-50 fw-semibold text-center py-2">
                                <i class="bi bi-hand-thumbs-up-fill me-1"></i> Thích
                            </a>
                            <a href="${pageContext.request.contextPath}/share?id=${v.id}" class="btn btn-dark-grey w-50 fw-semibold text-center py-2">
                                <i class="bi bi-send-fill me-1"></i> Chia sẻ
                            </a>
                        </div>
                    </div>
                    
                </div>
            </div>
        </c:forEach>
    </div>

   <div class="d-flex justify-content-center mt-5">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link px-3" href="${pageContext.request.contextPath}/home?page=1"><i class="bi bi-chevron-double-left"></i></a>
                </li>
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link px-3" href="${pageContext.request.contextPath}/home?page=${currentPage - 1}"><i class="bi bi-chevron-left"></i></a>
                </li>
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link px-3" href="${pageContext.request.contextPath}/home?page=${currentPage + 1}"><i class="bi bi-chevron-right"></i></a>
                </li>
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link px-3" href="${pageContext.request.contextPath}/home?page=${totalPages}"><i class="bi bi-chevron-double-right"></i></a>
                </li>
            </ul>
        </nav>
    </div>
</div>