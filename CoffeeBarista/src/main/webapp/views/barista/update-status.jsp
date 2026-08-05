<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="form-card">
    <h3 style="font-size: 1.1rem; font-weight: 700; margin-bottom: 1.25rem;">
        <i class="fas fa-edit"></i> Cập Nhật Trạng Thái Đơn
    </h3>
    
    <form action="${pageContext.request.contextPath}/barista/update-status" method="POST">
        <!-- Hidden Order ID -->
        <input type="hidden" name="id" value="${order.id}">
        
        <!-- Status Radio Group -->
        <div class="form-group">
            <label class="form-label">Chọn Trạng Thái</label>
            <div class="radio-group">
                <div class="radio-btn-wrapper pending">
                    <input type="radio" id="status-pending" name="status" value="PENDING" 
                           ${order.status == 'PENDING' ? 'checked' : ''}>
                    <label class="radio-label" for="status-pending">
                        <i class="fas fa-hourglass-start"></i> Đơn Mới
                    </label>
                </div>
                
                <div class="radio-btn-wrapper making">
                    <input type="radio" id="status-making" name="status" value="MAKING" 
                           ${order.status == 'MAKING' ? 'checked' : ''}>
                    <label class="radio-label" for="status-making">
                        <i class="fas fa-sync"></i> Đang Pha
                    </label>
                </div>
                
                <div class="radio-btn-wrapper completed">
                    <input type="radio" id="status-completed" name="status" value="COMPLETED" 
                           ${order.status == 'COMPLETED' ? 'checked' : ''}>
                    <label class="radio-label" for="status-completed">
                        <i class="fas fa-check-circle"></i> Hoàn Thành
                    </label>
                </div>
                
                <div class="radio-btn-wrapper cancelled">
                    <input type="radio" id="status-cancelled" name="status" value="CANCELLED" 
                           ${order.status == 'CANCELLED' ? 'checked' : ''}>
                    <label class="radio-label" for="status-cancelled">
                        <i class="fas fa-times-circle"></i> Hủy Đơn
                    </label>
                </div>
            </div>
        </div>
        
        <!-- Update Reason / Note -->
        <div class="form-group">
            <label class="form-label" for="update-note">Ghi chú / Lý do cập nhật</label>
            <textarea class="form-input" id="update-note" name="note" rows="3" 
                      placeholder="Ghi chú pha chế hoặc lý do hủy đơn...">${order.note}</textarea>
        </div>
        
        <button type="submit" class="btn" style="width: 100%; justify-content: center;">
            <i class="fas fa-save"></i> Cập nhật trạng thái
        </button>
    </form>
    </div>