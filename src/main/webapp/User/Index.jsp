<%@ include file="//NewFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	.ListItem{
		display: grid;
		grid-template-columns: repeat(3,1fr);
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="ListItem">
<c:forEach var="u" items="${listItem}">
<div class="Items">
${u.drinkName} 
${u.drinkPrice}
${u.drinkIMG}
${u.drinkDescription}
</div>
</c:forEach>
</div>

</body>
</html>