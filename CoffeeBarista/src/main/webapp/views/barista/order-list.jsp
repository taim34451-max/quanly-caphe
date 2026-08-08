<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Đơn hàng | Barista Hub</title>
    <!-- FontAwesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/style.css">
    
    <!-- CSS Bọc lót trực tiếp (Đã đổi sang Blue Luxury) -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&display=swap');
        
        /* 1. BẢNG MÀU GỐC BLUE LUXURY */
        :root { --bg-main: #0B1120; --bg-sidebar: #0F172A; --bg-surface: #1E293B; --bg-surface-hover: #334155; --color-primary: #38BDF8; --color-text: #F8FAFC; --color-text-secondary: #94A3B8; --color-border: rgba(56, 189, 248, 0.15); --status-pending: #f59e0b; --status-making: #0ea5e9; --status-completed: #10b981; --status-cancelled: #ef4444; }
        
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Plus Jakarta Sans', sans-serif; }
        body { background-color: var(--bg-main); color: var(--color-text); min-height: 100vh; display: flex; }
        .app-container { display: flex; width: 100vw; min-height: 100vh; }
        .sidebar { width: 280px; background-color: var(--bg-sidebar); border-right: 1px solid var(--color-border); padding: 2rem 1.5rem; display: flex; flex-direction: column; justify-content: space-between; height: 100vh; position: fixed; left: 0; top: 0; z-index: 100; }
        .brand-name { font-size: 1.3rem; font-weight: 800; color: var(--color-primary); }
        .brand { display: flex; align-items: center; gap: 0.75rem; padding-bottom: 2rem; border-bottom: 1px solid var(--color-border); }
        .brand-icon { font-size: 1.8rem; color: var(--color-primary); }
       
        .menu-list { list-style: none; margin-top: 2rem; display: flex; flex-direction: column; gap: 0.5rem; }
        .menu-item a { display: flex; align-items: center; gap: 1rem; padding: 0.85rem 1rem; color: var(--color-text-secondary); text-decoration: none; border-radius: 12px; font-weight: 500; }
        
        /* CẬP NHẬT GRADIENT CHO MENU ACTIVE */
        .menu-item.active a { color: #ffffff; background: linear-gradient(135deg, var(--color-primary) 0%, #0284C7 100%); font-weight: 700; }
        
        .logout-btn { display: flex; align-items: center; gap: 1rem; padding: 0.85rem 1rem; color: #ef4444; text-decoration: none; border-radius: 12px; font-weight: 600; background-color: rgba(239, 68, 68, 0.05); }
        .main-content { flex-grow: 1; margin-left: 280px; padding: 2.5rem; min-height: 100vh; }
        .header-title { font-size: 1.8rem; font-weight: 800; margin-bottom: 1.5rem; }
        .table-panel { background: var(--bg-surface); border: 1px solid var(--color-border); border-radius: 20px; padding: 1.5rem; }
        .table-filter-bar { display: flex; gap: 0.5rem; margin-bottom: 1.5rem; overflow-x: auto; }
        .filter-tab { padding: 0.6rem 1.2rem; background: var(--bg-sidebar); border: 1px solid var(--color-border); color: var(--color-text-secondary); text-decoration: none; border-radius: 10px; font-size: 0.85rem; font-weight: 600; }
        .filter-tab.active { color: var(--bg-main); background: var(--color-primary); border-color: var(--color-primary); font-weight: 700; }
        .custom-table { width: 100%; border-collapse: collapse; }
        .custom-table th { text-align: left; padding: 1rem; border-bottom: 2px solid var(--color-border); color: var(--color-text-secondary); font-size: 0.8rem; font-weight: 700; text-transform: uppercase; }
        .custom-table td { padding: 1rem; border-bottom: 1px solid var(--color-border); font-size: 0.95rem; }
        .status-badge { display: inline-flex; align-items: center; gap: 0.4rem; padding: 0.35rem 0.75rem; border-radius: 9999px; font-size: 0.75rem; font-weight: 700; text-transform: uppercase; }
        .status-badge.pending { color: var(--status-pending); background: rgba(245, 158, 11, 0.15); }
        .status-badge.making { color: var(--status-making); background: rgba(14, 165, 233, 0.15); }
        .status-badge.completed { color: var(--status-completed); background: rgba(16, 185, 129, 0.15); }
        .status-badge.cancelled { color: var(--status-cancelled); background: rgba(239, 68, 68, 0.15); }
        
        /* CẬP NHẬT GRADIENT CHO NÚT BẤM */
        .btn { background: linear-gradient(135deg, var(--color-primary) 0%, #0284C7 100%); color: #ffffff; border: none; padding: 0.65rem 1.2rem; border-radius: 10px; font-size: 0.85rem; font-weight: 700; cursor: pointer; display: inline-flex; align-items: center; gap: 0.5rem; text-decoration: none; }
        
        .btn-secondary { background: var(--bg-surface-hover); color: var(--color-text); border: 1px solid var(--color-border); }
    </style>
</head>
<body>
    <div class="app-container">
        <jsp:include page="/views/common/sidebar.jsp" />
        <main class="main-content">
            <h1 class="header-title"><i class="fas fa-list-alt"></i> Danh Sách Đơn Hàng</h1>
            
            <div class="table-panel">
                <div class="table-filter-bar">
                    <a href="${pageContext.request.contextPath}/barista/orders?status=ALL" class="filter-tab ${currentStatus == 'ALL' ? 'active' : ''}"><i class="fas fa-border-all"></i> Tất cả</a>
                    <a href="${pageContext.request.contextPath}/barista/orders?status=PENDING" class="filter-tab ${currentStatus == 'PENDING' ? 'active' : ''}"><i class="fas fa-hourglass-start"></i> Đơn Mới</a>
                    <a href="${pageContext.request.contextPath}/barista/orders?status=MAKING" class="filter-tab ${currentStatus == 'MAKING' ? 'active' : ''}"><i class="fas fa-sync"></i> Đang Pha</a>
                    <a href="${pageContext.request.contextPath}/barista/orders?status=COMPLETED" class="filter-tab ${currentStatus == 'COMPLETED' ? 'active' : ''}"><i class="fas fa-check-circle"></i> Hoàn Thành</a>
                    <a href="${pageContext.request.contextPath}/barista/orders?status=CANCELLED" class="filter-tab ${currentStatus == 'CANCELLED' ? 'active' : ''}"><i class="fas fa-times-circle"></i> Đã Hủy</a>
                </div>

                <table class="custom-table">
                    <thead>
                        <tr>
                            <th>Mã Đơn</th>
                            <th>Khách Hàng</th>
                            <th>Số Bàn</th>
                            <th>Trạng Thái</th>
                            <th>Thời Gian Đặt</th>
                            <th>Tổng Tiền</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td style="font-weight: 700; color: var(--color-primary);">#${order.id}</td>
                                <td style="font-weight: 600;">${order.customerName}</td>
                                <td><strong>${order.tableNumber}</strong></td>
                                <td><span class="status-badge ${order.status.toLowerCase()}">${order.status}</span></td>
                                <td style="color: var(--color-text-secondary);">${order.formattedCreatedAt}</td>
                                <td style="font-weight: 700;"><fmt:formatNumber value="${order.totalPrice}" pattern="#,###" /> VNĐ</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/barista/order-detail?id=${order.id}" class="btn btn-secondary" style="padding: 0.4rem 0.8rem; font-size: 0.8rem;">
                                        <i class="fas fa-eye"></i> Chi tiết
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>