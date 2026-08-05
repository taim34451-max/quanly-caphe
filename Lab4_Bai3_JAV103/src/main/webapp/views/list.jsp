<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Danh sách nhân viên</title></head>
<body style="font-family: Arial; padding: 20px;">
    <h2>Danh sách nhân viên</h2>
    <a href="${pageContext.request.contextPath}/employees/create" style="margin-bottom:15px; display:inline-block;">+ Thêm mới</a>
    
    <table border="1" cellspacing="0" cellpadding="8" width="80%">
        <tr style="background:#f2f2f2;">
            <th>Code</th><th>Họ tên</th><th>Email</th><th>Actions</th>
        </tr>
        <c:forEach var="e" items="${empList}">
            <tr>
                <td>${e.empCode}</td>
                <td>${e.fullName}</td>
                <td>${e.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/employees/view?code=${e.empCode}">View</a> |
                    <a href="${pageContext.request.contextPath}/employees/edit?code=${e.empCode}">Edit</a> |
                    <form action="${pageContext.request.contextPath}/employees/delete" method="POST" style="display:inline;" onsubmit="return confirm('Bạn có chắc chắn muốn xóa nhân viên này?');">
                        <input type="hidden" name="code" value="${e.empCode}">
                        <button type="submit" style="color:red; border:none; background:none; cursor:pointer; text-decoration:underline;">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>