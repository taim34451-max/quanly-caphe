<%@ include file="page.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Online Shopping Center</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
	<main>
		<header class="mb-2">
			<h1>Online Shopping Center</h1>
			<jsp:include page="menu.jsp"/>
		</header>
		<article class="container">
			<jsp:include page="${view}"/>
		</article>
		<footer class="text-center">
			<hr>
			<p>&copy; 2026 by FPT Polytechnic. All rights reserved.</p>
		</footer>
	</main>
</body>
</html>