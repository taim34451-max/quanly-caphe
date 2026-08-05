<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow border-0">
                <div class="card-header bg-primary text-white text-center py-3">
                    <h4 class="mb-0"><i class="bi bi-box-arrow-in-right"></i> ĐĂNG NHẬP</h4>
                </div>
                <div class="card-body p-4">
                    
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center fw-bold mb-4">
                            <i class="bi bi-exclamation-triangle-fill"></i> ${error}
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty message}">
                        <div class="alert alert-success text-center fw-bold mb-4">
                            <i class="bi bi-check-circle-fill"></i> ${message}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label class="form-label fw-bold">Tên đăng nhập</label>
                            <input type="text" name="username" value="${username}" class="form-control" placeholder="Nhập Username..." required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label fw-bold">Mật khẩu</label>
                            <input type="password" name="password" value="${password}" class="form-control" placeholder="Nhập Password..." required>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" name="remember" class="form-check-input" id="rememberMe" ${not empty username ? 'checked' : ''}>
                            <label class="form-check-label" for="rememberMe">Ghi nhớ tài khoản?</label>
                        </div>
                        <button type="submit" class="btn btn-primary w-100 py-2 fw-bold">ĐĂNG NHẬP</button>
                    </form>
                    
                </div>
                
                <div class="card-footer text-center bg-white py-3">
                    <a href="${pageContext.request.contextPath}/forgot-password" class="text-decoration-none fw-bold text-secondary">Quên mật khẩu?</a> <span class="mx-2">|</span> 
                    <a href="${pageContext.request.contextPath}/register" class="text-decoration-none fw-bold text-primary">Chưa có tài khoản? Đăng ký ngay</a>
                </div>
            </div>
        </div>
    </div>
</div>