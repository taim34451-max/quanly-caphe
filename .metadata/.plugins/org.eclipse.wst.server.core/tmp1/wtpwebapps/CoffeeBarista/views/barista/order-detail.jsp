<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pha chế đơn #${order.id} | Barista Hub</title>
    <!-- FontAwesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/style.css">
    
    <!-- CSS Bọc lót trực tiếp -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&display=swap');
        :root { --bg-main: #100e0d; --bg-sidebar: #171412; --bg-surface: #1e1a17; --bg-surface-hover: #292420; --color-primary: #d4a373; --color-text: #f4eae1; --color-text-secondary: #9c9186; --color-border: rgba(212, 163, 115, 0.12); }
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Plus Jakarta Sans', sans-serif; }
        body { background-color: var(--bg-main); color: var(--color-text); min-height: 100vh; display: flex; }
        .app-container { display: flex; width: 100vw; min-height: 100vh; }
        .sidebar { width: 280px; background-color: var(--bg-sidebar); border-right: 1px solid var(--color-border); padding: 2rem 1.5rem; display: flex; flex-direction: column; justify-content: space-between; height: 100vh; position: fixed; left: 0; top: 0; z-index: 100; }
        .main-content { flex-grow: 1; margin-left: 280px; padding: 2.5rem; min-height: 100vh; }
        .detail-layout { display: grid; grid-template-columns: 1.6fr 1fr; gap: 2rem; }
        .detail-main { background: var(--bg-surface); border: 1px solid var(--color-border); border-radius: 20px; padding: 2rem; }
        .countdown-box { background: linear-gradient(135deg, rgba(212, 163, 115, 0.08) 0%, rgba(212, 163, 115, 0.02) 100%); border: 1.5px solid var(--color-primary); border-radius: 20px; padding: 1.5rem; text-align: center; margin-bottom: 2rem; }
        .countdown-timer { font-size: 3rem; font-weight: 800; color: var(--color-primary); }
        .form-card { background: var(--bg-surface); border: 1px solid var(--color-border); border-radius: 20px; padding: 1.75rem; }
        .form-group { margin-bottom: 1.5rem; }
        .form-label { display: block; font-size: 0.9rem; font-weight: 600; margin-bottom: 0.75rem; }
        .form-input { width: 100%; background-color: var(--bg-sidebar); border: 1px solid var(--color-border); border-radius: 12px; padding: 0.85rem 1rem; color: var(--color-text); font-size: 0.95rem; }
        .radio-group { display: grid; grid-template-columns: repeat(2, 1fr); gap: 0.75rem; }
        .radio-label { display: flex; align-items: center; justify-content: center; padding: 0.75rem; background: var(--bg-sidebar); border: 1px solid var(--color-border); border-radius: 12px; cursor: pointer; font-weight: 600; }
        .btn { background: linear-gradient(135deg, var(--color-primary) 0%, #be8a58 100%); color: var(--bg-main); border: none; padding: 0.65rem 1.2rem; border-radius: 10px; font-size: 0.85rem; font-weight: 700; cursor: pointer; display: inline-flex; align-items: center; gap: 0.5rem; text-decoration: none; }
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
                        <form action="${pageContext.request.contextPath}/barista/update-status" method="POST" style="margin-top: 2rem;">
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
                        <div class="countdown-timer" id="countdown-timer" data-created-at="${order.createdAt != null ? order.createdAt.time : 0}">10:00</div>
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
</body>
</html>