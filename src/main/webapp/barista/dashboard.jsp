<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Barista Dashboard | Barista Hub</title>
    <!-- FontAwesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    
    <!-- CSS Bọc lót trực tiếp bảo đảm 100% giao diện mượt mà (Đã đổi sang Blue Luxury) -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&display=swap');
        :root { --bg-main: #0B1120; --bg-sidebar: #0F172A; --bg-surface: #1E293B; --bg-surface-hover: #334155; --color-primary: #38BDF8; --color-text: #F8FAFC; --color-text-secondary: #94A3B8; --color-border: rgba(56, 189, 248, 0.15); --status-pending: #f59e0b; --status-pending-bg: rgba(245, 158, 11, 0.15); --status-making: #0ea5e9; --status-making-bg: rgba(14, 165, 233, 0.15); --status-completed: #10b981; --status-completed-bg: rgba(16, 185, 129, 0.15); --status-cancelled: #ef4444; --status-cancelled-bg: rgba(239, 68, 68, 0.15); }
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Plus Jakarta Sans', sans-serif; }
        body { background-color: var(--bg-main); color: var(--color-text); min-height: 100vh; display: flex; }
        .app-container { display: flex; width: 100vw; min-height: 100vh; }
        .sidebar { width: 280px; background-color: var(--bg-sidebar); border-right: 1px solid var(--color-border); padding: 2rem 1.5rem; display: flex; flex-direction: column; justify-content: space-between; height: 100vh; position: fixed; left: 0; top: 0; z-index: 100; }
        .brand { display: flex; align-items: center; gap: 0.75rem; padding-bottom: 2rem; border-bottom: 1px solid var(--color-border); }
        .brand-icon { font-size: 1.8rem; color: var(--color-primary); }
        .brand-name { font-size: 1.3rem; font-weight: 800; color: var(--color-primary); }
        .menu-list { list-style: none; margin-top: 2rem; display: flex; flex-direction: column; gap: 0.5rem; }
        .menu-item a { display: flex; align-items: center; gap: 1rem; padding: 0.85rem 1rem; color: var(--color-text-secondary); text-decoration: none; border-radius: 12px; font-weight: 500; }
        
        /* Cập nhật Gradient cho Menu Active */
        .menu-item.active a { color: #ffffff; background: linear-gradient(135deg, var(--color-primary) 0%, #0284C7 100%); font-weight: 700; }
        
        .menu-badge { margin-left: auto; background-color: var(--bg-sidebar); color: var(--color-primary); padding: 0.2rem 0.5rem; border-radius: 8px; font-size: 0.8rem; font-weight: 700; }
        .logout-btn { display: flex; align-items: center; gap: 1rem; padding: 0.85rem 1rem; color: #ef4444; text-decoration: none; border-radius: 12px; font-weight: 600; background-color: rgba(239, 68, 68, 0.05); }
        .main-content { flex-grow: 1; margin-left: 280px; padding: 2.5rem; min-height: 100vh; }
        .header-panel { display: flex; justify-content: space-between; align-items: center; margin-bottom: 2.5rem; }
        .header-title { font-size: 1.8rem; font-weight: 800; }
        .header-subtitle { font-size: 0.9rem; color: var(--color-text-secondary); margin-top: 0.25rem; }
        .user-info { display: flex; align-items: center; gap: 0.75rem; background: var(--bg-surface); padding: 0.5rem 1rem; border-radius: 12px; border: 1px solid var(--color-border); }
        .user-avatar { width: 32px; height: 32px; border-radius: 50%; background-color: var(--color-primary); color: var(--bg-main); display: flex; align-items: center; justify-content: center; font-weight: 700; }
        .stats-grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 1.5rem; margin-bottom: 2.5rem; }
        .stats-card { background: var(--bg-surface); border: 1px solid var(--color-border); border-radius: 16px; padding: 1.5rem; display: flex; align-items: center; gap: 1.25rem; }
        .stats-icon-wrapper { width: 48px; height: 48px; border-radius: 12px; display: flex; align-items: center; justify-content: center; font-size: 1.4rem; }
        .stats-card.pending .stats-icon-wrapper { color: var(--status-pending); background: var(--status-pending-bg); }
        .stats-card.making .stats-icon-wrapper { color: var(--status-making); background: var(--status-making-bg); }
        .stats-card.completed .stats-icon-wrapper { color: var(--status-completed); background: var(--status-completed-bg); }
        .stats-card.cancelled .stats-icon-wrapper { color: var(--status-cancelled); background: var(--status-cancelled-bg); }
        .stats-label { font-size: 0.8rem; font-weight: 600; color: var(--color-text-secondary); text-transform: uppercase; }
        .stats-value { font-size: 1.8rem; font-weight: 800; margin-top: 0.2rem; }
        .dashboard-sections { display: grid; grid-template-columns: 1fr 1fr; gap: 2rem; }
        
        /* Cập nhật tone màu nền cho Section */
        .section-panel { background: rgba(30, 41, 59, 0.6); border: 1px solid var(--color-border); border-radius: 20px; padding: 1.75rem; }
        
        .section-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem; padding-bottom: 0.75rem; border-bottom: 1px solid var(--color-border); }
        .section-title { font-size: 1.1rem; font-weight: 700; }
        .order-cards-list { display: flex; flex-direction: column; gap: 1.25rem; max-height: calc(100vh - 350px); overflow-y: auto; }
        .order-card { background: var(--bg-surface); border: 1px solid var(--color-border); border-radius: 16px; padding: 1.25rem; display: flex; flex-direction: column; gap: 1rem; }
        .order-card-header { display: flex; justify-content: space-between; }
        .order-id { font-size: 1rem; font-weight: 700; }
        .order-customer { font-size: 0.9rem; color: var(--color-text-secondary); }
        .order-items-list { list-style: none; border-top: 1px dashed var(--color-border); border-bottom: 1px dashed var(--color-border); padding: 0.75rem 0; display: flex; flex-direction: column; gap: 0.5rem; }
        .order-item-row { font-size: 0.9rem; display: flex; justify-content: space-between; }
        .item-qty { color: var(--color-primary); font-weight: 700; margin-right: 0.5rem; }
        .order-card-footer { display: flex; justify-content: space-between; align-items: center; }
        .order-price { font-size: 1.1rem; font-weight: 800; }
        
        /* Cập nhật Gradient cho Button */
        .btn { background: linear-gradient(135deg, var(--color-primary) 0%, #0284C7 100%); color: #ffffff; border: none; padding: 0.65rem 1.2rem; border-radius: 10px; font-size: 0.85rem; font-weight: 700; cursor: pointer; display: inline-flex; align-items: center; gap: 0.5rem; text-decoration: none; }
        
        .btn-secondary { background: var(--bg-surface-hover); color: var(--color-text); border: 1px solid var(--color-border); }
        .empty-state { text-align: center; padding: 3rem 1.5rem; color: var(--color-text-secondary); }
        .empty-icon { font-size: 2.5rem; margin-bottom: 1rem; }
    </style>
</head>
<body>
    <div class="app-container">
        <!-- Sidebar Component -->
        <jsp:include page="/common/sidebar.jsp" />
        
        <!-- Main Content Area -->
        <main class="main-content">
            <div class="header-panel">
                <div>
                    <h1 class="header-title"><i class="fas fa-desktop"></i> Dashboard</h1>
                    <p class="header-subtitle">Chào mừng Barista! Quản lý nhận đơn và điều phối pha chế đồ uống.</p>
                </div>
                <div class="user-info">
                    <div class="user-avatar">B</div>
                    <div>
                        <div style="font-weight: 700; font-size: 0.9rem;">Barista Hub</div>
                        <div style="font-size: 0.75rem; color: var(--color-text-secondary);">Nhân viên pha chế</div>
                    </div>
                </div>
            </div>

            <!-- Stats Grid -->
            <div class="stats-grid">
                <div class="stats-card pending">
                    <div class="stats-icon-wrapper"><i class="fas fa-hourglass-start"></i></div>
                    <div class="stats-info">
                        <span class="stats-label">Đơn Mới</span>
                        <span class="stats-value" id="stats-pending-count">${counts['PENDING'] != null ? counts['PENDING'] : 0}</span>
                    </div>
                </div>
                <div class="stats-card making">
                    <div class="stats-icon-wrapper"><i class="fas fa-sync fa-spin"></i></div>
                    <div class="stats-info">
                        <span class="stats-label">Đang Pha</span>
                        <span class="stats-value" id="stats-making-count">${counts['MAKING'] != null ? counts['MAKING'] : 0}</span>
                    </div>
                </div>
                <div class="stats-card completed">
                    <div class="stats-icon-wrapper"><i class="fas fa-check-double"></i></div>
                    <div class="stats-info">
                        <span class="stats-label">Hoàn Thành</span>
                        <span class="stats-value" id="stats-completed-count">${counts['COMPLETED'] != null ? counts['COMPLETED'] : 0}</span>
                    </div>
                </div>
                <div class="stats-card cancelled">
                    <div class="stats-icon-wrapper"><i class="fas fa-times-circle"></i></div>
                    <div class="stats-info">
                        <span class="stats-label">Đã Hủy</span>
                        <span class="stats-value" id="stats-cancelled-count">${counts['CANCELLED'] != null ? counts['CANCELLED'] : 0}</span>
                    </div>
                </div>
            </div>

            <!-- Dashboard Sections (2 Columns Layout) -->
            <div class="dashboard-sections">
                <!-- Column 1: PENDING ORDERS -->
                <div class="section-panel">
                    <div class="section-header">
                        <h2 class="section-title"><i class="fas fa-bell"></i> ĐƠN HÀNG MỚI CHỜ NHẬN</h2>
                    </div>
                    <div class="order-cards-list" id="pending-orders-list">
                        <c:choose>
                            <c:when test="${empty pendingOrders}">
                                <div class="empty-state">
                                    <div class="empty-icon"><i class="fas fa-coffee"></i></div>
                                    <p>Không có đơn hàng mới nào</p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="order" items="${pendingOrders}">
                                    <div class="order-card" data-order-id="${order.billId}">
                                        <div class="order-card-header">
                                            <div>
                                                <div class="order-id">Đơn #${order.billId} - ${order.tableNumber}</div>
                                                <div class="order-customer">Khách: ${order.user.userName}</div>
                                            </div>
                                        </div>
                                        <ul class="order-items-list">
                                            <c:forEach var="item" items="${order.items}">
                                                <li class="order-item-row">
                                                    <div>
                                                        <span class="item-qty">${item.quantity}x</span>
                                                        <span>${item.itemName}</span>
                                                    </div>
                                                    <span style="color: var(--color-text-secondary);">
                                                        <fmt:formatNumber value="${item.price * item.quantity}" pattern="#,###" /> VNĐ
                                                    </span>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                        <div class="order-card-footer">
                                            <span class="order-price"><fmt:formatNumber value="${order.totalPrice}" pattern="#,###" /> VNĐ</span>
                                            
                                            <!-- ĐÃ FIX: Điều hướng form về đúng /barista/order-detail -->
                                            <form action="${pageContext.request.contextPath}/barista/order-detail" method="POST" style="margin: 0;">
                                                <input type="hidden" name="id" value="${order.billId}">
                                                <input type="hidden" name="status" value="MAKING">
                                                <input type="hidden" name="note" value="Đã nhận đơn">
                                                <button type="submit" class="btn"><i class="fas fa-play"></i> Nhận đơn (Making)</button>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <!-- Column 2: MAKING ORDERS -->
                <div class="section-panel">
                    <div class="section-header">
                        <h2 class="section-title"><i class="fas fa-spinner"></i> ĐƠN ĐANG PHA CHẾ</h2>
                    </div>
                    <div class="order-cards-list" id="making-orders-list">
                        <c:choose>
                            <c:when test="${empty makingOrders}">
                                <div class="empty-state">
                                    <div class="empty-icon"><i class="fas fa-mug-hot"></i></div>
                                    <p>Không có đơn đang pha chế</p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="order" items="${makingOrders}">
                                    <div class="order-card" data-order-id="${order.billId}">
                                        <div class="order-card-header">
                                            <div>
                                                <div class="order-id">Đơn #${order.billId} - ${order.tableNumber}</div>
                                                <div class="order-customer">Khách: ${order.userName}</div>
                                            </div>
                                        </div>
                                        <ul class="order-items-list">
                                            <c:forEach var="item" items="${order.items}">
                                                <li class="order-item-row">
                                                    <div>
                                                        <span class="item-qty">${item.quantity}x</span>
                                                        <span>${item.itemName}</span>
                                                    </div>
                                                    <span style="color: var(--color-text-secondary);">
                                                        <fmt:formatNumber value="${item.price * item.quantity}" pattern="#,###" /> VNĐ
                                                    </span>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                        <div class="order-card-footer">
                                            <span class="order-price"><fmt:formatNumber value="${order.totalPrice}" pattern="#,###" /> VNĐ</span>
                                            <a href="${pageContext.request.contextPath}/barista/order-detail?id=${order.billId}" class="btn btn-secondary">
                                                <i class="fas fa-eye"></i> Pha chế & Chi tiết
                                            </a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <script>window.contextPath = '${pageContext.request.contextPath}';</script>
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>