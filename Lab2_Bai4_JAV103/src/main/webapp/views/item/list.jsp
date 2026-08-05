<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4; }
        
        /* Thiết lập lưới Grid 3 cột cho danh sách */
        .product-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            max-width: 900px;
            margin: 0 auto;
        }

        /* Card sản phẩm bám sát thiết kế UI của đề bài */
        .product-card {
            background-color: #fff;
            border: 1px solid #7ca82b; /* Viền xanh lá mạ ở dưới */
            border-radius: 4px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .product-header {
            background-color: #7b659d; /* Màu tím nhạt */
            color: white;
            text-align: center;
            padding: 10px;
            font-weight: bold;
        }

        .product-image-container {
            padding: 15px;
            text-align: center;
            border: 2px solid #f39c12; /* Viền cam quanh ảnh */
            margin: 10px;
        }

        .product-image-container img {
            max-height: 120px;
            object-fit: contain;
            cursor: pointer;
            transition: transform 0.2s;
        }

        .product-image-container img:hover {
            transform: scale(1.05);
        }

        .product-price {
            padding: 10px 15px;
            font-size: 15px;
        }

        .old-price { text-decoration: line-through; color: #555; margin-right: 5px; }
        .new-price { color: blue; font-weight: bold; }
    </style>
</head>
<body>

    <div class="product-grid">
        <c:forEach var="item" items="${items}">
            <div class="product-card">
                
                <div class="product-header">${item.name}</div>
                
                <div class="product-image-container">
                    <a href="detail.php">
                        <img src="${item.image}" alt="${item.name}">
                    </a>
                </div>
                
                <c:set var="newPrice" value="${item.price * (1 - item.discount)}" />
                <div class="product-price">
                    <span class="old-price">$${item.price}</span> 
                    <span class="new-price">$${newPrice}</span>
                </div>
                
            </div>
        </c:forEach>
    </div>

</body>
</html>