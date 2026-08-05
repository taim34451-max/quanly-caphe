<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:url var="home" value="/home" scope="request"/>
<c:url var="student" value="/student" scope="request"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dự án 1 - FPT Polytechnic</title>
    
    <!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
    <main class="container-fluid">
        <header>
            <h1>FPT Polytechnic</h1>
        </header>
        <jsp:include page="menu.jsp"/>
        <div class="row">
            <article class="col-md-9">
                <jsp:include page="${view}"/>
            </article>
            <aside class="col-md-3">
                <jsp:include page="aside.jsp"/>
            </aside>
        </div>
        <footer>
            <hr>
            <p>&copy; 2026 by FPT Polytechnic. All rights resserved.</p>
        </footer>
    </main>
</body>

</html>