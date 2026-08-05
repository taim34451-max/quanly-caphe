<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab 6 - Tổng hợp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
    <ul class="nav nav-tabs">
        <li class="nav-item"><a class="nav-link active" data-bs-toggle="tab" href="#bai2">Bài 2</a></li>
        <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#bai3">Bài 3</a></li>
        <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#bai4">Bài 4</a></li>
    </ul>

    <div class="tab-content mt-3">
        <div class="tab-pane fade show active" id="bai2">
            <form action="${pageContext.request.contextPath}/lab6/bai2" method="GET">
                <input type="text" name="username" placeholder="Nhập Username...">
                <button type="submit">Search</button>
            </form>
            <table class="table">
                <c:forEach var="fav" items="${favorites}">
                    <tr><td>${fav.video.title}</td></tr>
                </c:forEach>
            </table>
        </div>

        <div class="tab-pane fade" id="bai3">
            <form action="${pageContext.request.contextPath}/lab6/bai3" method="GET">
                <input type="text" name="keyword" placeholder="Keyword...">
                <button type="submit">Search</button>
            </form>
            <table class="table">
                <c:forEach var="v" items="${videos}">
                    <tr><td>${v.title}</td></tr>
                </c:forEach>
            </table>
        </div>

        <div class="tab-pane fade" id="bai4">
            <form action="${pageContext.request.contextPath}/lab6/bai4" method="GET">
                <input type="number" name="year" placeholder="Year...">
                <button type="submit">Search</button>
            </form>
            <table class="table">
                <c:forEach var="s" items="${stats}">
                    <tr><td>${s[0]} - ${s[1]}</td></tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
    // Tự động chuyển về tab dựa trên URL nếu có tham số
    document.addEventListener("DOMContentLoaded", function() {
        var path = window.location.pathname;
        var triggerTab;
        
        if (path.includes("bai3")) {
            triggerTab = document.querySelector('a[href="#bai3"]');
        } else if (path.includes("bai4")) {
            triggerTab = document.querySelector('a[href="#bai4"]');
        } else {
            triggerTab = document.querySelector('a[href="#bai2"]');
        }
        
        if (triggerTab) {
            var tab = new bootstrap.Tab(triggerTab);
            tab.show();
        }
    });
  </script>
</body>
</html>