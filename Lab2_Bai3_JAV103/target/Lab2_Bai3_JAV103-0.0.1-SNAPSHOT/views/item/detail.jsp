<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Detail</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 40px;">

    <div style="width: 320px; border: 2px solid #7ca82b; border-radius: 6px; overflow: hidden; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
        
        <div style="background-color: #fff; padding: 12px; text-align: center; font-weight: bold; font-size: 18px; border-bottom: 1px solid #ddd; color: #333;">
            ${item.name}
        </div>
        
        <div style="padding: 20px; text-align: center; background-color: #fff; border-bottom: 1px solid #ddd;">
            <img src="https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=150" alt="${item.name}" style="max-height: 150px; object-fit: contain;">
        </div>
        
        <div style="padding: 15px; background-color: #fff; font-size: 14px; line-height: 24px;">
            <ul style="list-style-type: disc; padding-left: 20px; margin: 0;">
                
                <li>Giá gốc: <span style="text-decoration: line-through; color: #888;">${item.price}$</span></li>
                
                <c:set var="newPrice" value="${item.price * (1 - item.discount)}" />
                <li>Giá mới: <span style="color: red; font-weight: bold;">${newPrice}$</span></li>
                
                <li>Mức giá: 
                    <span style="font-weight: bold;">
                        <c:choose>
                            <c:when test="${newPrice < 10}">
                                <span style="color: blue;">Giá thấp</span>
                            </c:when>
                            <c:when test="${newPrice > 100}">
                                <span style="color: orange;">Giá cao</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: green;">Bình thường</span>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </li>
            </ul>
        </div>
        
        <div style="background-color: #e3f2fd; padding: 10px; text-align: center; font-size: 14px; font-weight: bold; color: #1565c0;">
            Ngày: <fmt:formatDate value="${item.date}" pattern="dd - MM - yyyy" />
        </div>
    </div>

</body>
</html>