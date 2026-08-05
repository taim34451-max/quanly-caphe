<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div class="container mt-5 mb-5" style="min-height: 50vh;">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow border-0">
                <div class="card-header bg-primary text-white text-center py-3">
                    <h5 class="mb-0 fw-bold"><i class="bi bi-share-fill"></i> CHIA SẺ TIỂU PHẨM</h5>
                </div>
                <div class="card-body p-4">
                    
                    <form action="${pageContext.request.contextPath}/share" method="post">
                        <div class="mb-3">
                            <label class="form-label fw-bold">Mã Video (Youtube ID)</label>
                            <!-- Lấy ID từ trên thanh URL xuống thông qua đối tượng param.id của JSP -->
                            <input type="text" name="videoId" value="${param.id}" class="form-control bg-light" readonly>
                        </div>
                        <div class="mb-4">
                            <label class="form-label fw-bold">Email bạn bè (Người nhận)</label>
                            <input type="text" name="emails" class="form-control" placeholder="vd: nguyenvanA@gmail.com, tranB@yahoo.com" required>
                            <div class="form-text text-muted">Bạn có thể gửi cho nhiều người cùng lúc bằng cách dùng dấu phẩy ( , ) để ngăn cách.</div>
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary w-100 fw-bold"><i class="bi bi-send"></i> GỬI EMAIL NGAY</button>
                            <a href="${pageContext.request.contextPath}/detail?id=${param.id}" class="btn btn-secondary w-100 fw-bold">QUAY LẠI</a>
                        </div>
                    </form>
                    
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />