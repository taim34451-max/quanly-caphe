<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
    /* Ép buộc ẩn nút chấm tròn */
    .radio-btn-wrapper input[type="radio"] {
        display: none !important;
    }
    
    /* Căn chỉnh lại icon và chữ thành khối block */
    .radio-btn-wrapper .radio-label {
        display: flex !important;
        align-items: center;
        justify-content: center;
        gap: 0.6rem !important; /* Tạo khoảng trống giữa icon và chữ */
        padding: 0.85rem 0.5rem;
        background-color: var(--bg-sidebar); 
        border: 1px solid var(--color-border);
        border-radius: 12px;
        cursor: pointer;
        color: var(--color-text-secondary);
        font-weight: 600;
        font-size: 0.9rem;
        transition: all 0.25s ease;
        width: 100%;
    }
    
    .radio-btn-wrapper .radio-label:hover {
        background-color: var(--bg-surface-hover);
        color: var(--color-text);
    }
    
    /* Đổi màu khi được chọn (Bắt buộc ghi đè) */
    .radio-btn-wrapper.pending input[type="radio"]:checked + .radio-label {
        background-color: rgba(245, 158, 11, 0.15) !important;
        color: var(--status-pending) !important;
        border-color: var(--status-pending) !important;
    }
    .radio-btn-wrapper.making input[type="radio"]:checked + .radio-label {
        background-color: rgba(14, 165, 233, 0.15) !important;
        color: var(--status-making) !important;
        border-color: var(--status-making) !important;
    }
    .radio-btn-wrapper.completed input[type="radio"]:checked + .radio-label {
        background-color: rgba(16, 185, 129, 0.15) !important;
        color: var(--status-completed) !important;
        border-color: var(--status-completed) !important;
    }
    .radio-btn-wrapper.cancelled input[type="radio"]:checked + .radio-label {
        background-color: rgba(239, 68, 68, 0.15) !important;
        color: var(--status-cancelled) !important;
        border-color: var(--status-cancelled) !important;
    }
</style>

<div class="form-card">
    <h3 style="font-size: 1.1rem; font-weight: 700; margin-bottom: 1.25rem;">
        <i class="fas fa-edit"></i> Cập Nhật Trạng Thái Đơn
    </h3>
    
    <!-- ĐÃ FIX LỖI: Điều hướng action về đúng orderdetailservlet -->
    <form action="${pageContext.request.contextPath}/barista/order-detail" method="POST">
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