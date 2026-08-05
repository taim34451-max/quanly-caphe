<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Expression Language Demo</title>
    <style>
        body { font-family: Arial, sans-serif; margin-left: 20px; font-size: 22px; }
        ul { list-style-type: square; }
        h3 { color: #2c3e50; border-bottom: 2px solid #ccc; padding-bottom: 5px; }
    </style>
</head>
<body>
    <h1>EL Thầy Duy 0363.46.46.46</h1>
    <h2>Expression Language (EL) & Scopes</h2>

    <h3>1. Truy xuất Biến Cơ Bản & Scope</h3>
    <ul>
        <li>requestScope.x: ${requestScope.x} = ${x}</li>
        <li>sessionScope.y: ${sessionScope.y} = ${y}</li>
        <li>applicationScope.z: ${applicationScope.z} = ${z}</li>
        <li>applicationScope.x: ${applicationScope.x} = ${x}</li>
        <li>Bean.month: ${now.month + 1}</li>
        <li>Bean.year: ${now.year + 1900}</li>
    </ul>

    <h3>2. Truy xuất Cấu Trúc Dữ Liệu</h3>
    <ul>
        <li><strong>Map (Điểm số):</strong>
            <ul>
                <li>map.toan: ${map.toan} = ${map['toan']}</li>
                <li>map.ly: ${map.ly} = ${map['ly']}</li>
            </ul>
        </li>
        <li><strong>List (Danh sách tên):</strong>
            <ul>
                <li>list[0]: ${list[0]}</li>
                <li>list[1]: ${list[1]}</li>
            </ul>
        </li>
    </ul>

</body>
</html>