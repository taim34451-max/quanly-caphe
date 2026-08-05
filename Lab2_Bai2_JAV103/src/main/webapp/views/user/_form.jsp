<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h3 style="font-size: 20px; color: #555;">User Edition</h3>
<h4 style="color: red; margin: 10px 0; font-weight: normal;">${message}</h4>

<form action="${pageContext.request.contextPath}/user.php" method="post" style="line-height: 30px;">
    <span style="font-weight: bold;">Username:</span><br>
    <input name="username" value="${form.username}" style="padding: 6px; width: 280px; border: 1px solid #ccc; border-radius: 4px;"><br><br>
    
    <span style="font-weight: bold;">Password:</span><br>
    <input name="password" value="${form.password}" type="password" style="padding: 6px; width: 280px; border: 1px solid #ccc; border-radius: 4px;"><br><br>
    
    <label style="cursor: pointer; user-select: none;">
        <input name="remember" type="checkbox" ${form.remember ? 'checked' : ''}> Remember me?
    </label><br><br>
    
    <button type="submit" style="padding: 7px 20px; background-color: #f5f5f5; border: 1px solid #ccc; border-radius: 4px; font-weight: bold; cursor: pointer;">Create</button>
</form>