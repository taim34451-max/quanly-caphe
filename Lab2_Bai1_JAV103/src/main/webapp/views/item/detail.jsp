<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head><title>Chi tiết sản phẩm</title></head>
<body>
    <h2>Tên sản phẩm: ${item.name}</h2>
    <p>Hình ảnh: ${item.image}</p>
    <p>Giá gốc: ${item.price}$</p>
    
    <c:set var="newPrice" value="${item.price * (1 - item.discount)}" />
    <p>Giá mới: ${newPrice}$</p>

    <p>Mức giá: 
        <c:choose>
            <c:when test="${newPrice < 10}">Giá thấp</c:when>
            <c:when test="${newPrice > 100}">Giá cao</c:when>
            <c:otherwise>Bình thường</c:otherwise>
        </c:choose>
    </p>
</body>
</html>