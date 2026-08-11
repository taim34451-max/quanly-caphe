<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pha chế đơn #${order.id} | Barista Hub</title>
    <!-- FontAwesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/style.css">
    
    <!-- CSS Bọc lót trực tiếp (Đã đổi sang Blue Luxury) -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&display=swap');
        
        /* 1. BẢNG MÀU GỐC BLUE LUXURY */
        :root { --bg-main: #0B1120; --bg-sidebar: #0F172A; --bg-surface: #1E293B; --bg-surface-hover: #334155; --color-primary: #38BDF8; --color-text: #F8FAFC; --color-text-secondary: #94A3B8; --color-border: rgba(56, 189, 248, 0.15); }
        
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Plus Jakarta Sans', sans-serif; }
        body { background-color: var(--bg-main); color: var(--color-text); min-height: 100vh; display: flex; }
        .app-container { display: flex; width: 100vw; min-height: 100vh; }
        
        /* SIDEBAR */
        .sidebar { width: 280px; background-color: var(--bg-sidebar); border-right: 1px solid var(--color-border); padding: 2rem 1.5rem; display: flex; flex-direction: column; justify-content: space-between; height: 100vh; position: fixed; left: 0; top: 0; z-index: 100; }
        .main-content { flex-grow: 1; margin-left: 280px; padding: 2.5rem; min-height: 100vh; }
       
        /* Bổ sung các class bị thiếu cho Sidebar */
        .brand { display: flex; align-items: center; gap: 0.75rem; padding-bottom: 2rem; border-bottom: 1px solid var(--color-border); }
        .brand-icon { font-size: 1.8rem; color: var(--color-primary); }
        .brand-name { font-size: 1.3rem; font-weight: 800; color: var(--color-primary); }
        .menu-list { list-style: none; margin-top: 2rem; display: flex; flex-direction: column; gap: 0.5rem; }
        .menu-item a { display: flex; align-items: center; gap: 1rem; padding: 0.85rem 1rem; color: var(--color-text-secondary); text-decoration: none; border-radius: 12px; font-weight: 500; }
        
        .menu-item.active a { color: #ffffff; background: linear-gradient(135deg, var(--color-primary) 0%, #0284C7 100%); font-weight: 700; }
        
        .menu-badge { margin-left: auto; background-color: var(--bg-sidebar); color: var(--color-primary); padding: 0.2rem 0.5rem; border-radius: 8px; font-size: 0.8rem; font-weight: 700; }
        .logout-btn { display: flex; align-items: center; gap: 1rem; padding: 0.85rem 1rem; color: #ef4444; text-decoration: none; border-radius: 12px; font-weight: 600; background-color: rgba(239, 68, 68, 0.05); }	
        
        /* DETAIL LAYOUT */
        .detail-layout { display: grid; grid-template-columns: 1.6fr 1fr; gap: 2rem; }
        .detail-main { background: var(--bg-surface); border: 1px solid var(--color-border); border-radius: 20px; padding: 2rem; }
        
        .countdown-box { background: linear-gradient(135deg, rgba(56, 189, 248, 0.08) 0%, rgba(56, 189, 248, 0.02) 100%); border: 1.5px solid var(--color-primary); border-radius: 20px; padding: 1.5rem; text-align: center; margin-bottom: 2rem; }
        
        .countdown-timer { font-size: 3rem; font-weight: 800; color: var(--color-primary); }
        .form-card { background: var(--bg-surface); border: 1px solid var(--color-border); border-radius: 20px; padding: 1.75rem; }
        .form-group { margin-bottom: 1.5rem; }
        .form-label { display: block; font-size: 0.9rem; font-weight: 600; margin-bottom: 0.75rem; }
        .form-input { width: 100%; background-color: var(--bg-sidebar); border: 1px solid var(--color-border); border-radius: 12px; padding: 0.85rem 1rem; color: var(--color-text); font-size: 0.95rem; }
        .radio-group { display: grid; grid-template-columns: repeat(2, 1fr); gap: 0.75rem; }
        .radio-label { display: flex; align-items: center; justify-content: center; padding: 0.75rem; background: var(--bg-sidebar); border: 1px solid var(--color-border); border-radius: 12px; cursor: pointer; font-weight: 600; }
        
        .btn { background: linear-gradient(135deg, var(--color-primary) 0%, #0284C7 100%); color: #ffffff; border: none; padding: 0.65rem 1.2rem; border-radius: 10px; font-size: 0.85rem; font-weight: 700; cursor: pointer; display: inline-flex; align-items: center; gap: 0.5rem; text-decoration: none; }
        
        .btn-success { background: linear-gradient(135deg, #10b981 0%, #059669 100%); color: white; }
        .btn-secondary { background: var(--bg-surface-hover); color: var(--color-text); border: 1px solid var(--color-border); }
        .detail-items-table { width: 100%; border-collapse: collapse; margin: 2rem 0; }
        .detail-items-table th { text-align: left; padding: 0.75rem 1rem; border-bottom: 2px solid var(--color-border); color: var(--color-text-secondary); }
        .detail-items-table td { padding: 1.25rem 1rem; border-bottom: 1px solid var(--color-border); }
    </style>
</head>
<body>
    <div class="app-container">
        <jsp:include page="/views/common/sidebar.jsp" />
        <main class="main-content">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
                <h1 style="font-size: 1.8rem; font-weight: 800;">Chi Tiết Pha Chế - Đơn #${order.id}</h1>
                <a href="${pageContext.request.contextPath}/barista/dashboard" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Quay lại Dashboard
                </a>
            </div>

            <div class="detail-layout">
                <div class="detail-main">
                    <h2>Món Cần Pha Chế</h2>
                    <table class="detail-items-table">
                        <thead>
                            <tr>
                                <th>Tên Món</th>
                                <th style="text-align: center;">Số Lượng</th>
                                <th style="text-align: right;">Đơn Giá</th>
                                <th style="text-align: right;">Thành Tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${order.items}">
                                <tr>
                                    <td><strong>${item.itemName}</strong></td>
                                    <td style="text-align: center; font-weight: 700; color: var(--color-primary);">${item.quantity}x</td>
                                    <td style="text-align: right;"><fmt:formatNumber value="${item.price}" pattern="#,###" /> VNĐ</td>
                                    <td style="text-align: right; font-weight: 700;"><fmt:formatNumber value="${item.price * item.quantity}" pattern="#,###" /> VNĐ</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${order.status == 'MAKING' || order.status == 'PENDING'}">
                        <form action="${pageContext.request.contextPath}/barista/order-detail" method="POST" style="margin-top: 2rem;">
                            <input type="hidden" name="id" value="${order.id}">
                            <input type="hidden" name="status" value="COMPLETED">
                            <button type="submit" class="btn btn-success" style="width: 100%; padding: 1rem; justify-content: center;">
                                <i class="fas fa-check-double"></i> HOÀN THÀNH PHA CHẾ
                            </button>
                        </form>
                    </c:if>
                </div>

                <div>
                    <div class="countdown-box">
                        <div style="font-size: 0.85rem; font-weight: 600; text-transform: uppercase;">Thời Gian Pha Chế</div>
                        <div class="countdown-timer" id="countdown-timer">10:00</div>
                    </div>

                    <div class="form-card">
                        <jsp:include page="update-status.jsp" />
                    </div>
                </div>
            </div>
        </main>
    </div>

       <script>window.contextPath = '${pageContext.request.contextPath}';</script>
    <script src="${pageContext.request.contextPath}/views/assets/js/main.js"></script>
    
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        const timerElement = document.getElementById("countdown-timer");
        const countdownBox = document.querySelector(".countdown-box");
        if (!timerElement) return;

        const orderId = "${order.id}";
        const orderStatus = "${order.status}";
        const storageKey = "barista_timer_target_order_" + orderId;

        // 1. Nếu đơn đã Hoàn Thành hoặc Hủy Đơn -> Hiển thị 00:00 & dọn dẹp bộ nhớ
        if (orderStatus === 'COMPLETED' || orderStatus === 'CANCELLED' || orderStatus === 'Hoàn Thành' || orderStatus === 'Hủy Đơn') {
            timerElement.textContent = "00:00";
            localStorage.removeItem(storageKey);
            return;
        }

        // 2. Nếu là Đơn Mới (PENDING) -> Đứng yên 10:00, chưa kích hoạt đếm ngược
        if (orderStatus === 'PENDING' || orderStatus === 'Đơn Mới') {
            timerElement.textContent = "10:00";
            localStorage.removeItem(storageKey);
            return;
        }

        // 3. Đã bấm ĐANG PHA (MAKING) -> Kích hoạt đếm ngược 10 phút liên tục
        let serverRemainingSeconds = ${empty remainingSeconds ? 600 : remainingSeconds};
        let targetTime = localStorage.getItem(storageKey);

        if (!targetTime || parseInt(targetTime) <= Date.now()) {
            targetTime = Date.now() + (serverRemainingSeconds * 1000);
            localStorage.setItem(storageKey, targetTime);
        } else {
            targetTime = parseInt(targetTime);
        }

        function updateTimer() {
            const now = Date.now();
            const diff = targetTime - now;

            if (diff <= 0) {
                timerElement.textContent = "00:00";
                if (countdownBox) countdownBox.classList.add("overdue");
                return;
            }

            const minutes = Math.floor(diff / 60000);
            const seconds = Math.floor((diff % 60000) / 1000);

            const displayMinutes = minutes < 10 ? "0" + minutes : minutes;
            const displaySeconds = seconds < 10 ? "0" + seconds : seconds;

            timerElement.textContent = displayMinutes + ":" + displaySeconds;

            if (diff < 2 * 60 * 1000 && countdownBox) {
                countdownBox.style.borderColor = '#fbbf24';
            }
        }

        updateTimer();
        setInterval(updateTimer, 1000);
    });
    </script>
</body>
</html>