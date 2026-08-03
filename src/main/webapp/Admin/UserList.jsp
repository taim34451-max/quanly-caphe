<%@ include file="/NewFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="u" items="${list}">
${u.IDUser} ${u.userName} ${u.userPass}  ${u.userPhone} ${u.userEmail}
<br>
</c:forEach>
</body>
</html>