<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Shopping Mall</title>
</head>
<body style="width: 1000px; margin: 20px auto; font-family: Arial, sans-serif; background-color: #fff;">

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
        <h1 style="color: #7ca82b; margin: 0; font-size: 36px; font-weight: bold;">Online Shopping Mall</h1>
        <div style="border: 1px solid #7ca82b; padding: 12px 25px; font-weight: bold; color: #7ca82b; border-radius: 4px; background: #fafafa;">Logo</div>
    </div>

    <%@ include file="menu.jsp" %>

    <div style="display: flex; justify-content: space-between; margin-top: 15px;">
        
        <div style="width: 72%; display: flex; flex-wrap: wrap; align-content: flex-start;">
            <div style="width: 33.33%;"><jsp:include page="item.jsp"><jsp:param name="name" value="Hình sản phẩm 1"/><jsp:param name="image" value="https://via.placeholder.com/120"/></jsp:include></div>
            <div style="width: 33.33%;"><jsp:include page="item.jsp"><jsp:param name="name" value="Hình sản phẩm 2"/><jsp:param name="image" value="https://via.placeholder.com/120"/></jsp:include></div>
            <div style="width: 33.33%;"><jsp:include page="item.jsp"><jsp:param name="name" value="Hình sản phẩm 3"/><jsp:param name="image" value="https://via.placeholder.com/120"/></jsp:include></div>
            <div style="width: 33.33%;"><jsp:include page="item.jsp"><jsp:param name="name" value="Hình sản phẩm 4"/><jsp:param name="image" value="https://via.placeholder.com/120"/></jsp:include></div>
            <div style="width: 33.33%;"><jsp:include page="item.jsp"><jsp:param name="name" value="Hình sản phẩm 5"/><jsp:param name="image" value="https://via.placeholder.com/120"/></jsp:include></div>
            <div style="width: 33.33%;"><jsp:include page="item.jsp"><jsp:param name="name" value="Hình sản phẩm 6"/><jsp:param name="image" value="https://via.placeholder.com/120"/></jsp:include></div>
        </div>

        <div style="width: 26%;">
            <%@ include file="login.jsp" %>
            <%@ include file="category.jsp" %>
        </div>
    </div>

    <div style="background-color: #eaeaea; text-align: center; padding: 12px; margin-top: 25px; font-size: 13px; color: #555; border-radius: 4px; border: 1px solid #ccc;">
        FPT Polytechnic @2020. All rights reserved.
    </div>

</body>
</html>