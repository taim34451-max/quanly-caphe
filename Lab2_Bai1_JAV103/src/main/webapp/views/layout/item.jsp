<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div style="border: 2px solid #ff9900; 
            border-radius: 4px; 
            margin: 5px; 
            box-sizing: border-box; 
            background: white; 
            overflow: hidden; 
            display: flex; 
            flex-direction: column; 
            justify-content: space-between; 
            height: 190px;">

    <div style="padding: 10px; 
                text-align: center; 
                flex-grow: 1; 
                display: flex; 
                align-items: center; 
                justify-content: center;">
        <a href="${pageContext.request.contextPath}/detail.jsp">
            <img src="${param.image}" 
                 alt="${param.name}" 
                 style="max-width: 100%; max-height: 110px; object-fit: contain;">
        </a>
    </div>

    <div style="background-color: #5e35b1; 
                color: white; 
                padding: 8px 5px; 
                text-align: center; 
                font-weight: bold; 
                font-size: 13px; 
                white-space: nowrap; 
                overflow: hidden; 
                text-overflow: ellipsis;">
        ${param.name}
    </div>

</div>