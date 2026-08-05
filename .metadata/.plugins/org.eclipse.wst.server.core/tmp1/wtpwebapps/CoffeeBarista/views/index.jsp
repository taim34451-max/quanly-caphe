<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Tự động đá người dùng sang trang /login
    response.sendRedirect(request.getContextPath() + "/login");
%>