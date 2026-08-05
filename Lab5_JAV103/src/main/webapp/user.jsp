<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý User</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 800px; margin: auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        th { background-color: #007bff; color: white; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: inline-block; width: 120px; font-weight: bold; }
        .form-group input[type="text"], .form-group input[type="password"] { width: 300px; padding: 5px; }
        
        .btn {
            padding: 8px 20px;
            border: 1px solid #7cb335;
            border-radius: 5px;
            font-size: 16px;
            color: white;
            cursor: pointer;
            margin-right: 8px;
            background: linear-gradient(to bottom, #bce067 0%, #90c641 100%);
            box-shadow: 2px 2px 4px rgba(0,0,0,0.3), inset 0 1px 0 rgba(255,255,255,0.6);
            text-shadow: 1px 1px 1px rgba(0,0,0,0.2);
            transition: all 0.1s ease;
        }
        .btn:hover {
            background: linear-gradient(to bottom, #aed45b 0%, #85bb3c 100%);
        }
        .btn:active {
            transform: translateY(2px);
            box-shadow: 1px 1px 2px rgba(0,0,0,0.3), inset 0 2px 3px rgba(0,0,0,0.2);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>List of Users CRUD Duy 0363,46,46,46</h1>
        <h2>CẬP NHẬT USER</h2>
        <form method="post">
            <div class="form-group">
                <label>Username (ID):</label>
                <input type="text" name="id" value="${form.id}" required>
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" value="${form.password}" required>
            </div>
            <div class="form-group">
                <label>Fullname:</label>
                <input type="text" name="fullname" value="${form.fullname}" required>
            </div>
            <div class="form-group">
                <label>Email Address:</label>
                <input type="text" name="email" value="${form.email}" required>
            </div>
            <div class="form-group">
                <label>Role:</label>
                <input type="radio" name="admin" value="true" ${form.admin ? 'checked' : ''}> Admin
                <input type="radio" name="admin" value="false" ${!form.admin ? 'checked' : 'checked'}> User
            </div>
            
            <div>
                <button type="submit" class="btn" formaction="${pageContext.request.contextPath}/user/create">Create</button>
                <button type="submit" class="btn" formaction="${pageContext.request.contextPath}/user/update">Update</button>
                <button type="submit" class="btn" formaction="${pageContext.request.contextPath}/user/delete">Delete</button>
                <button type="submit" class="btn" formaction="${pageContext.request.contextPath}/user/index">Reset</button>
            </div>
        </form>

        <hr>

        <h2>DANH SÁCH USER</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>PASSWORD</th>
                    <th>FULLNAME</th>
                    <th>EMAIL</th>
                    <th>ROLE</th>
                    <th>ACTION</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.password}</td>
                        <td>${item.fullname}</td>
                        <td>${item.email}</td>
                        <td>${item.admin ? 'Admin' : 'User'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/edit?id=${item.id}">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>