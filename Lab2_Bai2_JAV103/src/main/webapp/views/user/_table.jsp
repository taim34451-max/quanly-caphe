<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3 style="font-size: 20px; color: #555; margin-bottom: 15px;">User List</h3>
<table style="width: 60%; border-collapse: collapse; text-align: left; box-shadow: 0 1px 3px rgba(0,0,0,0.1);">
    <thead>
        <tr style="background-color: #f8f9fa; border-bottom: 2px solid #dee2e6;">
            <th style="padding: 12px; font-weight: bold; border: 1px solid #dee2e6;">Username</th>
            <th style="padding: 12px; font-weight: bold; border: 1px solid #dee2e6;">Password</th>
            <th style="padding: 12px; font-weight: bold; border: 1px solid #dee2e6;">Remember?</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="u" items="${items}">
            <tr style="border-bottom: 1px solid #dee2e6;">
                <td style="padding: 12px; border: 1px solid #dee2e6;">${u.username}</td>
                <td style="padding: 12px; border: 1px solid #dee2e6;">${u.password}</td>
                <td style="padding: 12px; border: 1px solid #dee2e6; color: ${u.remember ? 'green' : 'red'}; font-weight: bold;">
                    ${u.remember ? 'Yes' : 'No'}
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>