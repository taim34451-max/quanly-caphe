<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.login-container,
.register-container{
    width:450px;
    margin:50px auto;

    background:white;

    border-radius:12px;

    box-shadow:0 4px 20px rgba(0,0,0,.08);
}

.login-header,
.register-header{
    background:#4a6fa5;
    color:white;

    padding:18px;

    font-size:22px;
    font-weight:bold;
    text-align:center;
}

.login-body,
.register-body{
    padding:25px;
}

label{
    display:block;
    margin-top:15px;
    margin-bottom:5px;
}

input{
    width:100%;
    padding:12px;

    border:1px solid #ddd;
    border-radius:8px;
}

input:focus{
    outline:none;
    border-color:#4a6fa5;
}

.login-btn,
.signup-btn{
    width:100%;
    margin-top:20px;

    background:#4a6fa5;
    color:white;

    border:none;
    border-radius:8px;

    padding:12px;
    cursor:pointer;
}

.login-btn:hover,
.signup-btn:hover{
    background:#365784;
}

.remember{
	display: flex;
	flex: left;
}
</style>
</head>
<body>

		<div class="login-container">
    <div class="login-header">LOGIN</div>
    <div class="login-body">
		<form action="${pageContext.request.contextPath}/dang_nhap" method="post">
      	<p>${error}</p>
        <label for="username">USERNAME?</label>
        <input type="text" id="username" name="username" placeholder="Nhập tên đăng nhập...">

        <label for="password">PASSWORD?</label>
        <input type="password" id="password" name="password" placeholder="Nhập mật khẩu...">

        <div class="remember">Remember me?
          <input type="checkbox" id="remember" name="remember">
        </div>

        <button type="submit" class="login-btn">Login</button>
    </div>
  </div>
</form>
</body>
</html>