<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>

    <style>
        body {
            margin-left: 30px;
            font-family: Arial, sans-serif; /* Thêm font chữ cho dễ nhìn */
            font-size: 24px;
        }

        /* CHỈ áp dụng kích thước này cho ô nhập tài khoản và mật khẩu */
        input[type="text"], input[type="password"], select {
            width: 400px;
            height: 40px;
            font-size: 20px;
            padding: 5px;
            margin-top: 5px;
        }

        button {
            width: 120px;
            height: 45px;
            font-size: 20px;
            margin-top: 20px;
            margin-right: 10px;
            cursor: pointer;
        }

        .form-group {
            margin-bottom: 20px;
        }

        /* Chỉnh lại nút radio cho vừa vặn */
        .radio-group input[type="radio"] {
            width: 20px;
            height: 20px;
            margin-right: 5px;
            margin-left: 15px;
        }

        .message {
            color: red;
            font-size: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <h1>Login Thầy Duy 0363.46.46.46</h1>
    <div class="message">${message}</div>

    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">

        <div class="form-group">
            <label><strong>Username:</strong></label><br>
            <input type="text" name="username" placeholder="Input username" required>
        </div>

        <div class="form-group">
            <label><strong>Password:</strong></label><br>
            <input type="password" name="password" placeholder="Input password" required>
        </div>
        
        <div class="form-group radio-group">
            <label><strong>Giới tính:</strong></label>
            <input type="radio" id="nam" name="gender" value="Nam" checked>
            <label for="nam">Nam</label>
            
            <input type="radio" id="nu" name="gender" value="Nữ">
            <label for="nu">Nữ</label>
        </div>

        <div class="form-group">
            <label><strong>Thành phố:</strong></label><br>
            <select name="city">
                <option value="Hà Nội">Hà Nội</option>
                <option value="TP. Hồ Chí Minh">TP. Hồ Chí Minh</option>
                <option value="Hải Phòng">Hải Phòng</option>
                <option value="Đà Nẵng">Đà Nẵng</option>
                <option value="Cần Thơ">Cần Thơ</option>
                <option value="Huế">Huế</option>
                <option value="Đồng Nai">Đồng Nai</option>
            </select>
        </div>

        <button type="submit">Login</button>
        <button type="reset">Reset</button>

    </form>

</body>
</html>