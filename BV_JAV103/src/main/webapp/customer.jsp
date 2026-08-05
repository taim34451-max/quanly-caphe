<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>CRUD-JPA Customer</title>
    <style>
        /* CSS căn bản hỗ trợ Layout chuẩn theo ảnh đề bài */
        table, th, td { border: 1px solid gray; border-collapse: collapse; padding: 5px; }
        .form-group { margin-bottom: 8px; }
        label { display: inline-block; width: 80px; }
    </style>
</head>
<body>
    <h2>CRUD-JPA Customer</h2>

    <p style="color: red; font-weight: bold;">${errorMessage}</p>
    <p style="color: green; font-weight: bold;">${successMessage}</p>

    <form action="${pageContext.request.contextPath}/customer/index" method="POST">
        <div class="form-group">
            <label>Username:</label>
            <input type="text" name="username" value="${form.username}" ${not empty form.username ? 'readonly style="background-color: #e9ecef; cursor: not-allowed;"' : ''}>
        </div>
        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" value="${form.password}">
        </div>
        <div class="form-group">
            <label>FullName:</label>
            <input type="text" name="fullname" value="${form.fullname}">
        </div>
        <div class="form-group">
            <label>Gender:</label>
            <input type="radio" name="gender" value="true" ${form.gender == true ? 'checked' : ''}> Male
            <input type="radio" name="gender" value="false" ${form.gender == false ? 'checked' : ''}> Female
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="text" name="email" value="${form.email}">
        </div>
        
        <div class="form-group">
            <button type="submit" formaction="${pageContext.request.contextPath}/customer/create">Create</button>
            <button type="submit" formaction="${pageContext.request.contextPath}/customer/update">Update</button>
            <a href="${pageContext.request.contextPath}/customer/delete?username=${form.username}">
                <button type="button">Delete</button>
            </a>
            <a href="${pageContext.request.contextPath}/customer/index">
                <button type="button">Reset</button>
            </a>
        </div>
    </form>

    <br>

    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>Username</th>
                <th>Password</th>
                <th>FullName</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${list}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${item.username}</td>
                    <td>${item.password}</td>
                    <td>${item.fullname}</td>
                    <td>${item.gender ? 'Male' : 'Female'}</td>
                    <td>${item.email}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/customer/edit/${item.username}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>