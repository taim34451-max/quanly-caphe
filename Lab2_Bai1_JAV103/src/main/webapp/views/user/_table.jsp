<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table border="1" style="width:50%; text-align:center;">
    <thead>
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Remember?</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="u" items="${items}">
            <tr>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td>${u.remember ? 'Yes' : 'No'}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>