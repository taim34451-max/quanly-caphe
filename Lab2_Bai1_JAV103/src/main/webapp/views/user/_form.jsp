<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h3>User Edition</h3>
<h4 style="color:red">${message}</h4>
<form action="${pageContext.request.contextPath}/user.php" method="post">
    Username:
    <input name="username" value="${form.username}"><br><br>
    Password:
    <input name="password" value="${form.password}"><br><br>
    <input name="remember" type="checkbox" ${form.remember ? 'checked' : ''}> Remember me? <br><br>
    <button type="submit">Create</button>
</form>