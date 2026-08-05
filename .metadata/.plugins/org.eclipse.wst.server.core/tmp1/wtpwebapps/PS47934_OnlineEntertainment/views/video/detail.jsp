<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success alert-dismissible fade show" role="alert" style="margin-top: 20px;">
        <strong>Thành công!</strong> ${sessionScope.message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <c:remove var="message" scope="session" />
</c:if>

<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="margin-top: 20px;">
        <strong>Lỗi:</strong> ${sessionScope.error}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <c:remove var="error" scope="session" />
</c:if>

<div class="container mt-4 mb-5">
    <div class="row">
        <div class="col-lg-8">
            <div class="ratio ratio-16x9 mb-4 shadow-sm rounded overflow-hidden">
                <iframe src="https://www.youtube.com/embed/${video.id}" width="100%" height="450" title="${video.title}" frameborder="0" allowfullscreen></iframe>
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

            <div class="card bg-light border-0 shadow-sm">
                <div class="card-body">
                    <h5 class="fw-bold">Mô tả tiểu phẩm:</h5>
                    <p style="white-space: pre-line;" class="mb-0">${video.description}</p>
                </div>
            </div>
        </div>
        
        <div class="col-lg-4 mt-4 mt-lg-0">
            <div class="card border-0 shadow-sm">
                <div class="card-header bg-dark text-white">
                    <h5 class="mb-0"><i class="bi bi-clock-history"></i> Video đã xem</h5>
                </div>
                <div class="card-body p-0">
                    <div class="list-group list-group-flush">
                        <c:choose>
                            <%-- Kiểm tra xem list lịch sử có dữ liệu không --%>
                            <c:when test="${not empty historyList}">
                                <c:forEach var="hv" items="${historyList}">
                                    <a href="${pageContext.request.contextPath}/detail?id=${hv.id}" class="list-group-item list-group-item-action d-flex align-items-center p-3">
                                        <img src="${hv.poster}" alt="${hv.title}" class="rounded" style="width: 100px; height: 56px; object-fit: cover;">
                                        <div class="ms-3 flex-grow-1 overflow-hidden">
                                            <h6 class="mb-1 text-truncate" title="${hv.title}">${hv.title}</h6>
                                        </div>
                                    </a>
                                </c:forEach>
                            </c:when>
                            
                            <%-- Nếu mảng rỗng (người dùng chưa xem video nào hoặc bị xóa cookie) --%>
                            <c:otherwise>
                                <div class="text-center text-muted py-5">
                                    <i class="bi bi-camera-reels" style="font-size: 3rem;"></i>
                                    <p class="mt-2">Chưa có lịch sử xem.</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />